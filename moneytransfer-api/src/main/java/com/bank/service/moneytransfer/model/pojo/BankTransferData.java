package com.bank.service.moneytransfer.model.pojo;

import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankTransferData implements Serializable {

    @NotNull(message = "amount is a required field")
    @Valid
    private Amount amount;
    @NotNull(message = "executionDate cannot be missing or empty")
    @Pattern(regexp = "^(([0-2]\\d|[3][0-1])/([0]\\d|[1][0-2])/[2][0]\\d{2})$",
            message = "Date format error: use dd/MM/yyyy")
    private String executionDate;
    @NotBlank(message = "beneficiaryName cannot be missing or empty")
    @Size(min = 2, max = 25, message = "Beneficiary Name must be between 2 and 25 chars")
    private String beneficiaryName;
    @NotNull(message = "beneficiaryIban cannot be missing or empty")
    @Size(min = 15, max = 32, message = "Invalid Iban")
    private String beneficiaryIban;
    @NotEmpty(message = "paymentReason cannot be missing or empty")
    @Size(min = 3, max = 150, message = "Payment Reason must be between 3 and 150 chars")
    private String paymentReason;

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public String getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(String executionDate) {
        this.executionDate = executionDate;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getBeneficiaryIban() {
        return beneficiaryIban;
    }

    public void setBeneficiaryIban(String beneficiaryIban) {
        this.beneficiaryIban = beneficiaryIban;
    }

    public String getPaymentReason() {
        return paymentReason;
    }

    public void setPaymentReason(String paymentReason) {
        this.paymentReason = paymentReason;
    }
}