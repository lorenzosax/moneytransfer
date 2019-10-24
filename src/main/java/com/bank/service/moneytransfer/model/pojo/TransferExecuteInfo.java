package com.bank.service.moneytransfer.model.pojo;

import java.io.Serializable;

public class TransferExecuteInfo implements Serializable {

    private String cro;

    public TransferExecuteInfo(String cro) {
        this.cro = cro;
    }

    public String getCro() {
        return cro;
    }

    public void setCro(String cro) {
        this.cro = cro;
    }
}