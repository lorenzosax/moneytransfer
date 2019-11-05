package com.bank.service.moneytransfer.model.response;

import com.bank.service.moneytransfer.model.pojo.OutcomeEnum;
import com.bank.service.moneytransfer.model.pojo.Result;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse implements Serializable {

    private Result result;

    public BaseResponse() {
        this.result = new Result();
    }

    public BaseResponse(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public void setRequestId(String requestId) {
        this.result.setRequestId(requestId);
    }

    public void setSuccessResponse() {
        this.result.setOutcome(OutcomeEnum.SUCCESS);
    }

    public void setWarningResponse() {
        this.result.setOutcome(OutcomeEnum.WARNING);
    }

    public void setErrorResponse() {
        this.result.setOutcome(OutcomeEnum.ERROR);
    }

    public void addMessages(String... args) {
        Collection<String> messages = new ArrayList<>(Arrays.asList(args));
        this.result.addMessages(messages);
    }

    @JsonIgnore
    public boolean isError() {
        return this.result.getOutcome().equals(OutcomeEnum.ERROR);
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "result=" + result +
                '}';
    }
}
