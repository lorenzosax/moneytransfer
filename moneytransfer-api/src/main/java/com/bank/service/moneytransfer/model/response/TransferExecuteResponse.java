package com.bank.service.moneytransfer.model.response;

import com.bank.service.moneytransfer.model.pojo.TransferExecuteInfo;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransferExecuteResponse extends BaseResponse {

    private TransferExecuteInfo data;

    public TransferExecuteInfo getData() {
        return data;
    }

    public void setData(TransferExecuteInfo data) {
        this.data = data;
    }
}
