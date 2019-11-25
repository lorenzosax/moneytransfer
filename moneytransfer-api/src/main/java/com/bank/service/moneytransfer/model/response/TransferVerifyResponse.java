package com.bank.service.moneytransfer.model.response;

import com.bank.service.moneytransfer.model.pojo.Transaction;
import com.bank.service.moneytransfer.model.pojo.TransferVerifyInfo;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Test.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransferVerifyResponse extends BaseResponse {

    private TransferVerifyInfo data;
    private Transaction transaction;

    public TransferVerifyResponse() {
        super();
    }

    // public TransferVerifyResponse(TransferVerifyInfo data, Transaction transaction) {
    //    super();
    //    this.data = data;
    //    this.transaction = transaction;
    // }

    public TransferVerifyInfo getData() {
        return data;
    }

    public void setData(TransferVerifyInfo data) {
        this.data = data;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
