package com.bank.service.moneytransfer.model.pojo;

public class Transaction {

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
