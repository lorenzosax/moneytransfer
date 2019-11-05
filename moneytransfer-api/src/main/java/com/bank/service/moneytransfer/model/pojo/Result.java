package com.bank.service.moneytransfer.model.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result implements Serializable {

    private List<String> messages;
    private OutcomeEnum outcome;
    private String requestId;

    public Result() {
        this.messages = new ArrayList<>();
        this.outcome = OutcomeEnum.SUCCESS;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public void addMessages(Collection<String> messages) {
        this.messages.addAll(messages);
    }

    public OutcomeEnum getOutcome() {
        return outcome;
    }

    public void setOutcome(OutcomeEnum outcome) {
        this.outcome = outcome;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}