package com.bank.service.moneytransfer.model.pojo;

import java.io.Serializable;

public class Transaction implements Serializable {

    private String id;

    public Transaction() {
    }

    public Transaction(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
