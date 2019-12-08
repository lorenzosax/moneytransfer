package com.bank.service.moneytransfer.service;

import com.bank.service.moneytransfer.model.pojo.BankTransferData;
import com.bank.service.moneytransfer.model.pojo.CustomerBankAccount;
import com.bank.service.moneytransfer.model.response.TransferExecuteResponse;
import com.bank.service.moneytransfer.model.response.TransferPrepareResponse;
import com.bank.service.moneytransfer.model.response.TransferVerifyResponse;

public interface ICustomerService {

    public TransferPrepareResponse getTransferBasicInformation(Long customerId, String bankAccountNumber);

    public TransferVerifyResponse executeTransferVerify(Long customerId, String bankAccountNumber,
                                                        BankTransferData bankTransferData);

    public TransferExecuteResponse executeTransferUltimate(Long customerId, String bankAccountNumber,
                                                           String transactionId);

    public CustomerBankAccount getCustomerBankAccount(Long customerId, String bankAccountNumber);
}
