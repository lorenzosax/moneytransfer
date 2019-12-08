package com.bank.service.moneytransfer.service;

import java.util.Date;

import com.bank.service.moneytransfer.model.pojo.Amount;
import com.bank.service.moneytransfer.model.pojo.BankTransferData;
import com.bank.service.moneytransfer.model.pojo.OutcomeEnum;
import com.bank.service.moneytransfer.model.pojo.TransferPrepareInfo;
import com.bank.service.moneytransfer.model.response.TransferExecuteResponse;
import com.bank.service.moneytransfer.model.response.TransferPrepareResponse;
import com.bank.service.moneytransfer.model.response.TransferVerifyResponse;
import com.bank.service.moneytransfer.utils.DateUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceUnitTest {

    @Autowired
    private ICustomerService customerService;
    private static Long customerId;
    private static Long customerId2;
    private static String bankAccountNumber;
    private static String bankAccountNumber2;
    private static String todayDate;
    private static String transferLimitDate;

    @BeforeClass
    public static void beforeClass() {
        customerId = 123L;
        customerId2 = 222L;
        bankAccountNumber = "0001234566";
        bankAccountNumber2 = "0001234577";
        todayDate = DateUtils.getFormattedDate(new Date());
        transferLimitDate = DateUtils.addDaysAndGetFormattedDate(new Date(), 30);
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    @Test
    public void getTransferBasicInformation() {
        // First test (Black box)
        TransferPrepareResponse transferBasicInformation =
                customerService.getTransferBasicInformation(customerId, bankAccountNumber);
        TransferPrepareInfo transferPrepareInfo = transferBasicInformation != null
                && transferBasicInformation.getResult().getOutcome() == OutcomeEnum.SUCCESS
                && transferBasicInformation.getData() != null ? transferBasicInformation.getData() : null;
        Assert.assertNotNull("User not found", transferPrepareInfo);
        Assert.assertEquals("Full name not match", "Lorenzo Gagliani",
                transferPrepareInfo.getFullName());
        Assert.assertEquals("Today date not match", todayDate, transferPrepareInfo.getTodayDate());
        Assert.assertEquals("Transfer limit date not match", transferLimitDate,
                transferPrepareInfo.getTransferLimitDate());

        // Second test (White box)
        TransferPrepareResponse transferBasicInformationNotExist =
                customerService.getTransferBasicInformation(99999L, bankAccountNumber);
        Assert.assertEquals("User found", OutcomeEnum.ERROR,
                transferBasicInformationNotExist.getResult().getOutcome());

        // Third test (White Box)
        TransferPrepareResponse transferBasicInformationNotExist2 =
                customerService.getTransferBasicInformation(customerId, "xxxxxxxxx");
        Assert.assertEquals("User found", OutcomeEnum.ERROR,
                transferBasicInformationNotExist2.getResult().getOutcome());
    }

    @Test
    public void executeTransferVerify() {
        BankTransferData bankTransferData = new BankTransferData();
        setBankTransferDataOk(bankTransferData);

        // First test (Black box)
        TransferVerifyResponse transferVerifyResponse =
                customerService.executeTransferVerify(customerId, bankAccountNumber, bankTransferData);
        Assert.assertEquals("Should be success response", OutcomeEnum.SUCCESS,
                transferVerifyResponse.getResult().getOutcome());
        Assert.assertEquals("Commission amount not matched", "0",
                transferVerifyResponse.getData().getCommissions().getAmount());
        Assert.assertEquals("Commission currencyCode not matched",
                bankTransferData.getAmount().getCurrencyCode(),
                transferVerifyResponse.getData().getCommissions().getCurrencyCode());
        Assert.assertEquals("Currency not matched", bankTransferData.getAmount().getCurrency(),
                transferVerifyResponse.getData().getAmount().getCurrency());
        Assert.assertEquals("CurrencyCode not matched", bankTransferData.getAmount().getCurrencyCode(),
                transferVerifyResponse.getData().getAmount().getCurrencyCode());
        Assert.assertNotNull("Transaction id is null", transferVerifyResponse.getTransaction().getId());
        Assert.assertTrue("Transaction id is in a wrong format",
                transferVerifyResponse.getTransaction().getId().matches("^(?!-)[0-9a-z\\-]+$"));

        // Second test (Black box)
        bankTransferData.getAmount().setCurrency("4000");
        TransferVerifyResponse transferVerifyResponse2 =
                customerService.executeTransferVerify(customerId, bankAccountNumber, bankTransferData);
        Assert.assertEquals("Should be fail with warning because exceeded transfer limit", OutcomeEnum.WARNING,
                transferVerifyResponse2.getResult().getOutcome());

        // Third test (Black box)
        bankTransferData.getAmount().setCurrency("1000");
        TransferVerifyResponse transferVerifyResponse3 =
                customerService.executeTransferVerify(customerId2, bankAccountNumber2, bankTransferData);
        Assert.assertEquals("Should be fail with error because user has insufficient credit",
                OutcomeEnum.ERROR,
                transferVerifyResponse3.getResult().getOutcome());

        // Fourth test (Black box)
        setBankTransferDataOk(bankTransferData);
        bankTransferData.setBeneficiaryIban("PROVAIBANFAKE");
        TransferVerifyResponse transferVerifyResponse4 =
                customerService.executeTransferVerify(customerId, bankAccountNumber, bankTransferData);
        Assert.assertEquals("wrong iban didn't generate error", OutcomeEnum.ERROR,
                transferVerifyResponse4.getResult().getOutcome());

        // Fifth test (White box)
        setBankTransferDataOk(bankTransferData);
        bankTransferData.getAmount().setCurrency("-10");
        TransferVerifyResponse transferVerifyResponse5 =
                customerService.executeTransferVerify(customerId2, bankAccountNumber2, bankTransferData);
        Assert.assertEquals("Should be fail with warning because send negative credit",
                OutcomeEnum.WARNING,
                transferVerifyResponse5.getResult().getOutcome());

        // Sixth test (White box)
        TransferVerifyResponse transferVerifyResponse6 =
                customerService.executeTransferVerify(customerId2, "fake", bankTransferData);
        Assert.assertEquals("Should be fail because fake account number",
                OutcomeEnum.ERROR,
                transferVerifyResponse6.getResult().getOutcome());
    }

    @Test
    public void executeTransferUltimate() {
        // First test (Black box)
        BankTransferData bankTransferData = new BankTransferData();
        setBankTransferDataOk(bankTransferData);
        TransferVerifyResponse transferVerifyResponse =
                customerService.executeTransferVerify(customerId, bankAccountNumber, bankTransferData);
        String transactionId = transferVerifyResponse.getTransaction().getId();
        TransferExecuteResponse transferExecuteResponse =
                customerService.executeTransferUltimate(customerId, bankAccountNumber, transactionId);
        Assert.assertNotNull("No CRO code found", transferExecuteResponse.getData().getCro());
        Assert.assertEquals("Should be success response", OutcomeEnum.SUCCESS,
                transferExecuteResponse.getResult().getOutcome());

        // Second test (Black box) --> with this test I found and fixed issue #2
        TransferExecuteResponse transferExecuteResponse2 =
                customerService.executeTransferUltimate(customerId, bankAccountNumber, transactionId);
        Assert.assertEquals("Should be an error response", OutcomeEnum.ERROR,
                transferExecuteResponse2.getResult().getOutcome());
        Assert.assertNull("Data should be null", transferExecuteResponse2.getData());

        // Third test (White box)
        TransferExecuteResponse transferExecuteResponse3 =
                customerService.executeTransferUltimate(customerId, "fake", transactionId);
        Assert.assertEquals("Should be fail because fake account number", OutcomeEnum.ERROR,
                transferExecuteResponse3.getResult().getOutcome());
    }

    private void setBankTransferDataOk(BankTransferData bankTransferData) {
        bankTransferData.setBeneficiaryName("Mario Rossi");
        bankTransferData.setBeneficiaryIban("IT44Q0300203280779186981788");
        bankTransferData.setAmount(new Amount("EUR", "123.50"));
        bankTransferData.setExecutionDate(DateUtils.addDaysAndGetFormattedDate(new Date(), 15));
        bankTransferData.setPaymentReason("test reason");
    }
}