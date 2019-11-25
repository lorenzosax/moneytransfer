package com.bank.service.moneytransfer.service;

import com.bank.service.moneytransfer.model.entity.BankAccount;
import com.bank.service.moneytransfer.model.entity.Customer;
import com.bank.service.moneytransfer.model.entity.Transaction;
import com.bank.service.moneytransfer.model.pojo.BankTransferData;
import com.bank.service.moneytransfer.model.pojo.CustomerBankAccount;

public interface ITransactionService {

    public Transaction generateTransactionFromVerify(CustomerBankAccount customerBankAccount,
                                                     BankTransferData bankTransferData);

    public Transaction updateAndTerminateTransaction(String trxId, Customer customer, BankAccount bankAccount);
}
