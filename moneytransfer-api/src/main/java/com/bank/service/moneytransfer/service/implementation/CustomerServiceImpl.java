package com.bank.service.moneytransfer.service.implementation;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.bank.service.moneytransfer.controller.CustomerController;
import com.bank.service.moneytransfer.model.entity.BankAccount;
import com.bank.service.moneytransfer.model.entity.Customer;
import com.bank.service.moneytransfer.model.entity.Transaction;
import com.bank.service.moneytransfer.model.pojo.Amount;
import com.bank.service.moneytransfer.model.pojo.BankTransferData;
import com.bank.service.moneytransfer.model.pojo.Commissions;
import com.bank.service.moneytransfer.model.pojo.CustomerBankAccount;
import com.bank.service.moneytransfer.model.pojo.TransferExecuteInfo;
import com.bank.service.moneytransfer.model.pojo.TransferPrepareInfo;
import com.bank.service.moneytransfer.model.pojo.TransferVerifyInfo;
import com.bank.service.moneytransfer.model.response.TransferExecuteResponse;
import com.bank.service.moneytransfer.model.response.TransferPrepareResponse;
import com.bank.service.moneytransfer.model.response.TransferVerifyResponse;
import com.bank.service.moneytransfer.repository.ICustomerRepository;
import com.bank.service.moneytransfer.service.ICustomerService;
import com.bank.service.moneytransfer.service.ITransactionService;
import com.bank.service.moneytransfer.utils.DateUtils;
import org.iban4j.IbanFormatException;
import org.iban4j.IbanUtil;
import org.iban4j.InvalidCheckDigitException;
import org.iban4j.UnsupportedCountryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class CustomerServiceImpl implements ICustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private ICustomerRepository customerRepository;

    private ITransactionService transactionService;

    @Autowired
    public CustomerServiceImpl(ICustomerRepository customerRepository, ITransactionService transactionService) {
        this.customerRepository = customerRepository;
        this.transactionService = transactionService;
    }

    @Override
    public TransferPrepareResponse getTransferBasicInformation(Long customerId, String bankAccountNumber) {
        TransferPrepareResponse transferPrepareResponse = new TransferPrepareResponse();
        CustomerBankAccount customerBankAccount = getCustomerBankAccount(customerId, bankAccountNumber);
        if (customerBankAccount != null) {
            Date today = new Date();
            TransferPrepareInfo transferPrepareInfo = new TransferPrepareInfo();
            transferPrepareInfo.setFullName(customerBankAccount.getCustomer().getName()
                    + " " + customerBankAccount.getCustomer().getLastname());
            transferPrepareInfo.setAccountNumber(customerBankAccount.getBankAccount().getAccountNumber());
            transferPrepareInfo.setTodayDate(DateUtils.getFormattedDate(today));
            transferPrepareInfo.setTransferLimitDate(DateUtils.addDaysAndGetFormattedDate(new Date(), 30));
            transferPrepareResponse.setData(transferPrepareInfo);
        } else {
            transferPrepareResponse.setErrorResponse();
        }

        return transferPrepareResponse;
    }

    @Override
    public TransferVerifyResponse executeTransferVerify(Long customerId, String bankAccountNumber,
                                                        BankTransferData bankTransferData) {
        TransferVerifyResponse transferVerifyResponse = new TransferVerifyResponse();
        CustomerBankAccount customerBankAccount = getCustomerBankAccount(customerId, bankAccountNumber);
        try {
            if (customerBankAccount != null) {
                BankAccount bankAccount = customerBankAccount.getBankAccount();
                BigDecimal amountToTransfer = new BigDecimal(bankTransferData.getAmount().getCurrency());
                BigDecimal availableBalance = bankAccount.getAvailableBalance();
                BigDecimal transferLimit = bankAccount.getTransferLimit();
                IbanUtil.validate(bankTransferData.getBeneficiaryIban());
                if (amountToTransfer.compareTo(availableBalance) <= 0
                        && amountToTransfer.compareTo(transferLimit) <= 0
                        && amountToTransfer.compareTo(BigDecimal.ONE) >= 0) {
                    Transaction transaction =
                            transactionService.generateTransactionFromVerify(customerBankAccount, bankTransferData);
                    TransferVerifyInfo transferVerifyInfo = new TransferVerifyInfo();
                    transferVerifyInfo.setAmount(
                            new Amount(bankAccount.getCurrencyCode(), amountToTransfer.toString()));
                    transferVerifyInfo.setCommissions(
                            new Commissions(bankAccount.getCurrencyCode(), bankAccount.getCommissions()));
                    transferVerifyResponse.setTransaction(
                            new com.bank.service.moneytransfer.model.pojo.Transaction(transaction.getTrxId()));
                    transferVerifyResponse.setData(transferVerifyInfo);
                } else if (amountToTransfer.compareTo(BigDecimal.ONE) < 0) {
                    logger.error("Amount to transfer cannot be less than 1");
                    transferVerifyResponse.setWarningResponse();
                    transferVerifyResponse.addMessages("L'importo deve essere superiore a 1 "
                            + bankAccount.getCurrencyCode());
                } else if (amountToTransfer.compareTo(availableBalance) > 0) {
                    logger.error("Insufficient balance to transfer money: amountToTransfer="
                            + amountToTransfer + " availableBalance=" + availableBalance);
                    transferVerifyResponse.setErrorResponse();
                    transferVerifyResponse.addMessages("Saldo insufficiente per completare l'operazione.");
                } else {
                    logger.error("The transfer amount reaches the limit: amountToTransfer="
                            + amountToTransfer + " transferLimit=" + transferLimit);
                    transferVerifyResponse.setWarningResponse();
                    transferVerifyResponse.addMessages("Per disporre un bonifico superiore a "
                            + transferLimit + " " + bankAccount.getCurrencyCode() + ", contatta il servizio clienti.");
                }
            } else {
                logger.info("CustomerBankAccount obj not found for customerId="
                        + customerId + " bankAccountNumber=" + bankAccountNumber);
                transferVerifyResponse.setErrorResponse();
            }
        } catch (IbanFormatException
                | InvalidCheckDigitException
                | UnsupportedCountryException e) {
            logger.error("Invalid iban parameter " + bankTransferData.getBeneficiaryIban());
            transferVerifyResponse.setErrorResponse();
            transferVerifyResponse.addMessages("IBAN non valido!");
        }
        return transferVerifyResponse;
    }

    @Override
    public TransferExecuteResponse executeTransferUltimate(Long customerId, String bankAccountNumber,
                                                           String transactionId) {
        TransferExecuteResponse transferExecuteResponse = new TransferExecuteResponse();
        CustomerBankAccount customerBankAccount = getCustomerBankAccount(customerId, bankAccountNumber);
        if (customerBankAccount != null) {
            Transaction transaction = transactionService
                    .updateAndTerminateTransaction(
                            transactionId,
                            customerBankAccount.getCustomer(),
                            customerBankAccount.getBankAccount());

            if (transaction != null) {
                transferExecuteResponse.setData(new TransferExecuteInfo(transaction.getCro()));
                transferExecuteResponse.addMessages(
                        "Hai effettuato un bonifico a " + transaction.getBeneficiaryName() + "!",
                        "Una volta prodotta, troverai la contabilità delle tue operazioni tra i documenti!");
            } else {
                transferExecuteResponse.setErrorResponse();
                transferExecuteResponse.addMessages("Errore durante il trasferimento di denaro!");
            }
        } else {
            transferExecuteResponse.setErrorResponse();
            transferExecuteResponse.addMessages("Servizio momentaneamente non disponibile, riprovare più tardi.");
        }
        return transferExecuteResponse;
    }

    private CustomerBankAccount getCustomerBankAccount(Long customerId, String bankAccountNumber) {
        CustomerBankAccount customerBankAccount = null;
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        Customer customer;
        BankAccount bankAccount;
        if (customerOptional.isPresent()) {
            customer = customerOptional.get();
            bankAccount = getBankAccountByAccountNumberFromList(customer.getBankAccountList(), bankAccountNumber);
            if (bankAccount != null) {
                customerBankAccount = new CustomerBankAccount(customer, bankAccount);
            } else {
                logger.error("Bank account with accountNumber=" + bankAccountNumber + " not found");
            }
        } else {
            logger.error("Customer with id=" + customerId + " not found");
        }
        return customerBankAccount;
    }

    private BankAccount getBankAccountByAccountNumberFromList(List<BankAccount> bankAccountList,
                                                              String bankAccountNumber) {
        BankAccount bankAccount = null;
        for (BankAccount b : bankAccountList) {
            if (b.getAccountNumber().equals(bankAccountNumber)) {
                bankAccount = b;
            }
        }
        return bankAccount;
    }
}
