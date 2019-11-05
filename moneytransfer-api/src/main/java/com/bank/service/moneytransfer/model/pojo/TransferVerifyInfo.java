package com.bank.service.moneytransfer.model.pojo;

import java.io.Serializable;

public class TransferVerifyInfo implements Serializable {

    private Commissions commissions;
    private Amount amount;

    public TransferVerifyInfo() {
        this.commissions = new Commissions();
        this.amount = new Amount();
    }

    public Commissions getCommissions() {
        return commissions;
    }

    public void setCommissions(Commissions commissions) {
        this.commissions = commissions;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }
}