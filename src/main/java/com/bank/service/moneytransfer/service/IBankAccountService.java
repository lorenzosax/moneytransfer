package com.bank.service.moneytransfer.service;

import com.bank.service.moneytransfer.model.entity.BankAccount;
import com.bank.service.moneytransfer.model.pojo.BankOperationEnum;

import java.math.BigDecimal;

public interface IBankAccountService {

    public BankAccount updateAvailableBalance(BankAccount bankAccount, BankOperationEnum bankOperation, BigDecimal amountToTransfer);
}
