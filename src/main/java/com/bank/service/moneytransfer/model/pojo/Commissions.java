package com.bank.service.moneytransfer.model.pojo;

import java.io.Serializable;

public class Commissions implements Serializable {

    private String currencyCode;
    private String amount;

    public Commissions() {
    }

    public Commissions(String currencyCode, String amount) {
        this.currencyCode = currencyCode;
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
