package com.bank.service.moneytransfer.model.pojo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class Amount implements Serializable {

    @NotBlank(message = "currencyCode cannot be missing or empty")
    private String currencyCode;
    @NotNull(message = "currency cannot be missing or empty")
    @Pattern(regexp = "^[0-9]+([.,]?[0-9]+)?$", message = "Currency format not accepted: use numbers and dot or comma for decimal")
    private String currency;

    public Amount() {
    }

    public Amount(String currencyCode, String currency) {
        this.currencyCode = currencyCode;
        this.currency = currency;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
