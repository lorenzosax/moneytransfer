package com.bank.service.moneytransfer.model.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransferPrepareInfo implements Serializable {

    private String fullName;
    private String accountNumber;
    private String todayDate;
    private String transferLimitDate;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTodayDate() {
        return todayDate;
    }

    public void setTodayDate(String todayDate) {
        this.todayDate = todayDate;
    }

    public String getTransferLimitDate() {
        return transferLimitDate;
    }

    public void setTransferLimitDate(String transferLimitDate) {
        this.transferLimitDate = transferLimitDate;
    }
}