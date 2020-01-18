package com.bank.service.moneytransfer.service;

import java.math.BigDecimal;
import java.util.Date;
import javax.transaction.Transactional;

import com.bank.service.moneytransfer.model.entity.Transaction;
import com.bank.service.moneytransfer.model.pojo.Amount;
import com.bank.service.moneytransfer.model.pojo.BankTransferData;
import com.bank.service.moneytransfer.model.pojo.CustomerBankAccount;
import com.bank.service.moneytransfer.utils.DateUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class TransactionServiceUnitTest {

    @Autowired
    private ITransactionService transactionService;
    @Autowired
    private ICustomerService customerService;
    private static Long customerId;
    private static String bankAccountNumber;

    @BeforeClass
    public static void beforeClass() {
        customerId = 444L;
        bankAccountNumber = "0004004444";
    }

    @Test
    public void generateTransactionFromVerify() {
        // First test (Black box)
        CustomerBankAccount customerBankAccount = customerService.getCustomerBankAccount(customerId, bankAccountNumber);
        BankTransferData bankTransferData = new BankTransferData();
        setBankTransferDataOk(bankTransferData);
        Transaction transaction = transactionService
                .generateTransactionFromVerify(customerBankAccount, bankTransferData);
        Assert.assertNotNull("Transaction id should not be null", transaction.getTrxId());
        Assert.assertTrue("Transaction id is in a wrong format",
                transaction.getTrxId().matches("^(?!-)[0-9a-z\\-]+$"));
        Assert.assertEquals("Beneficiary iban should match",
                bankTransferData.getBeneficiaryIban(), transaction.getBeneficiaryIban());
        Assert.assertEquals("Beneficiary name should match",
                bankTransferData.getBeneficiaryName(), transaction.getBeneficiaryName());
        Assert.assertEquals("Payment reason should match",
                bankTransferData.getPaymentReason(), transaction.getPaymentReason());
        Assert.assertEquals("Currency code should match",
                bankTransferData.getAmount().getCurrencyCode(), transaction.getBankAccount().getCurrencyCode());
        Assert.assertEquals("Amount should match",
                new BigDecimal(bankTransferData.getAmount().getCurrency()), transaction.getAmount());
        Assert.assertEquals("Accreditation date should match",
                bankTransferData.getExecutionDate(),
                transaction.getAccreditationDate());
        Assert.assertNotNull("Created at should not be null", transaction.getCreatedAt());
        Assert.assertNull("Executed at should be null", transaction.getExecutedAt());
        Assert.assertNull("Cro code should be null", transaction.getCro());
    }

    @Test
    public void updateAndTerminateTransaction() {
        // First test (Black box)
        CustomerBankAccount customerBankAccount = customerService.getCustomerBankAccount(customerId, bankAccountNumber);
        BankTransferData bankTransferData = new BankTransferData();
        setBankTransferDataOk(bankTransferData);
        Transaction transactionAfterVerify = transactionService
                .generateTransactionFromVerify(customerBankAccount, bankTransferData);
        Transaction trxResult = transactionService.updateAndTerminateTransaction(transactionAfterVerify.getTrxId(),
                customerBankAccount.getCustomer(), customerBankAccount.getBankAccount());
        Assert.assertEquals("Transaction id should match",
                transactionAfterVerify.getTrxId(),
                trxResult.getTrxId());
        Assert.assertEquals("Beneficiary iban should match",
                transactionAfterVerify.getBeneficiaryIban(), trxResult.getBeneficiaryIban());
        Assert.assertEquals("Beneficiary name should match",
                transactionAfterVerify.getBeneficiaryName(), trxResult.getBeneficiaryName());
        Assert.assertEquals("Payment reason should match",
                transactionAfterVerify.getPaymentReason(), trxResult.getPaymentReason());
        Assert.assertEquals("Currency code should match",
                transactionAfterVerify.getCurrencyCode(), trxResult.getBankAccount().getCurrencyCode());
        Assert.assertEquals("Amount should match",
                0,
                transactionAfterVerify.getAmount().compareTo(trxResult.getAmount()));
        Assert.assertEquals("Accreditation date should match",
                transactionAfterVerify.getAccreditationDate(),
                trxResult.getAccreditationDate());
        Assert.assertNotNull("Created at should not be null", trxResult.getCreatedAt());
        Assert.assertEquals("Created at should match",
                transactionAfterVerify.getCreatedAt(),
                trxResult.getCreatedAt());
        Assert.assertNotNull("Executed at should not be null", trxResult.getExecutedAt());
        Assert.assertNotNull("Cro code should not be null", trxResult.getCro());

        // Second test (White box)
        String fakeTrxId = "fake_trx_id";
        trxResult = transactionService.updateAndTerminateTransaction(fakeTrxId, customerBankAccount.getCustomer(),
                customerBankAccount.getBankAccount());
        Assert.assertNull("Transaction should be null because trxId is fake", trxResult);
    }

    private void setBankTransferDataOk(BankTransferData bankTransferData) {
        bankTransferData.setBeneficiaryName("Mario Bianchi");
        bankTransferData.setBeneficiaryIban("IT44Q0300203280779186981788");
        bankTransferData.setAmount(new Amount("EUR", "222.00"));
        bankTransferData.setExecutionDate(DateUtils.addDaysAndGetFormattedDate(new Date(), 15));
        bankTransferData.setPaymentReason("test reason");
    }
}