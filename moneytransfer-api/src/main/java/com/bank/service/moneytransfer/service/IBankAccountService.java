package com.bank.service.moneytransfer.service;

import java.math.BigDecimal;

import com.bank.service.moneytransfer.model.entity.BankAccount;
import com.bank.service.moneytransfer.model.pojo.BankOperationEnum;

public interface IBankAccountService {

    public BankAccount updateAvailableBalance(BankAccount bankAccount, BankOperationEnum bankOperation,
                                              BigDecimal amountToTransfer);
}
