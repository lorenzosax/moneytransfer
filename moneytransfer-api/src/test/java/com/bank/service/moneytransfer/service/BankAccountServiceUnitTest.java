package com.bank.service.moneytransfer.service;

import java.math.BigDecimal;
import javax.transaction.Transactional;

import com.bank.service.moneytransfer.model.entity.BankAccount;
import com.bank.service.moneytransfer.model.pojo.BankOperationEnum;
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
public class BankAccountServiceUnitTest {

    @Autowired
    private IBankAccountService bankAccountService;
    @Autowired
    private ICustomerService customerService;
    private static Long customerId;
    private static String bankAccountNumber;
    private static BigDecimal amountToTransfer;

    @BeforeClass
    public static void beforeClass() {
        customerId = 333L;
        bankAccountNumber = "0001004599";
        amountToTransfer = new BigDecimal(100.00);
    }

    @Test
    public void updateAvailableBalance() {
        // First test (Black box)
        BankAccount bankAccount = customerService
                .getCustomerBankAccount(customerId, bankAccountNumber).getBankAccount();
        BigDecimal amountBeforeOperation = bankAccount.getAvailableBalance();
        BankAccount bankAccountAfterOperation = bankAccountService
                .updateAvailableBalance(bankAccount, BankOperationEnum.SEND_MONEY, amountToTransfer);
        Assert.assertEquals("Amount after SEND_MONEY should be less than amountBeforeOperation",
                0,
                amountBeforeOperation
                        .subtract(bankAccountAfterOperation.getAvailableBalance()).compareTo(amountToTransfer));

        // Second test (Black box)
        bankAccount = customerService
                .getCustomerBankAccount(customerId, bankAccountNumber).getBankAccount();
        amountBeforeOperation = bankAccount.getAvailableBalance();
        bankAccountAfterOperation = bankAccountService
                .updateAvailableBalance(bankAccount, BankOperationEnum.RECEIVE_MONEY, amountToTransfer);
        Assert.assertEquals("Amount after RECEIVE_MONEY should be more than amountBeforeOperation",
                0,
                bankAccountAfterOperation.getAvailableBalance().compareTo(amountBeforeOperation.add(amountToTransfer)));
    }
}