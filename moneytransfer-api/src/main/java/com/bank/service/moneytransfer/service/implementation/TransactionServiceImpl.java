package com.bank.service.moneytransfer.service.implementation;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import com.bank.service.moneytransfer.controller.CustomerController;
import com.bank.service.moneytransfer.model.entity.BankAccount;
import com.bank.service.moneytransfer.model.entity.Customer;
import com.bank.service.moneytransfer.model.entity.Transaction;
import com.bank.service.moneytransfer.model.pojo.BankOperationEnum;
import com.bank.service.moneytransfer.model.pojo.BankTransferData;
import com.bank.service.moneytransfer.model.pojo.CustomerBankAccount;
import com.bank.service.moneytransfer.repository.ITransactionRepository;
import com.bank.service.moneytransfer.service.IBankAccountService;
import com.bank.service.moneytransfer.service.ITransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class TransactionServiceImpl implements ITransactionService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private ITransactionRepository transactionRepository;

    private IBankAccountService bankAccountService;

    @Autowired
    public TransactionServiceImpl(ITransactionRepository transactionRepository,
                                  IBankAccountService bankAccountService) {
        this.transactionRepository = transactionRepository;
        this.bankAccountService = bankAccountService;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Transaction generateTransactionFromVerify(CustomerBankAccount customerBankAccount,
                                                     BankTransferData bankTransferData) {
        Transaction transaction = new Transaction();
        transaction.setAccreditationDate(bankTransferData.getExecutionDate());
        transaction.setAmount(new BigDecimal(bankTransferData.getAmount().getCurrency()));
        transaction.setBeneficiaryIban(bankTransferData.getBeneficiaryIban());
        transaction.setBeneficiaryName(bankTransferData.getBeneficiaryName());
        transaction.setCurrencyCode(customerBankAccount.getBankAccount().getCurrencyCode());
        transaction.setBankAccount(customerBankAccount.getBankAccount());
        transaction.setCustomer(customerBankAccount.getCustomer());
        transaction.setTrxId(UUID.randomUUID().toString());
        transaction.setPaymentReason(bankTransferData.getPaymentReason());

        return transactionRepository.save(transaction);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Transaction updateAndTerminateTransaction(String trxId, Customer customer, BankAccount bankAccount) {
        Transaction trx = null;
        Optional<Transaction> transaction =
                transactionRepository.findTransactionByTrxIdAndCustomerAndBankAccount(trxId, customer, bankAccount);
        if (transaction.isPresent() && transaction.get().getCro() == null) {
            trx = transaction.get();
            trx.setExecutedAt(new Date());
            trx.setCro(UUID.randomUUID().toString().replace("-", ""));
            transactionRepository.save(trx);
            bankAccountService.updateAvailableBalance(
                    bankAccount,
                    BankOperationEnum.SEND_MONEY,
                    trx.getAmount());
        } else {
            logger.error("Transaction with trxId=" + trxId + " not found");
        }
        return trx;
    }
}
