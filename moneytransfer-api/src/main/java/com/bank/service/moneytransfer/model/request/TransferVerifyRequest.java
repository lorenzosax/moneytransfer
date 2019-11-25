package com.bank.service.moneytransfer.model.request;

import java.io.Serializable;
import javax.validation.Valid;

import com.bank.service.moneytransfer.model.pojo.BankTransferData;

public class TransferVerifyRequest implements Serializable {

    @Valid
    private BankTransferData data;

    public BankTransferData getData() {
        return data;
    }

    public void setData(BankTransferData data) {
        this.data = data;
    }
}
