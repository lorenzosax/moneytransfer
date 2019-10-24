package com.bank.service.moneytransfer.model.request;

import com.bank.service.moneytransfer.model.pojo.BankTransferData;

import javax.validation.Valid;
import java.io.Serializable;

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
