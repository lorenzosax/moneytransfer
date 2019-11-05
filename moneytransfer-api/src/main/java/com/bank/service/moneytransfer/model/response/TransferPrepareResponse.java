package com.bank.service.moneytransfer.model.response;

import com.bank.service.moneytransfer.model.pojo.TransferPrepareInfo;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransferPrepareResponse extends BaseResponse {

    private TransferPrepareInfo data;

    public TransferPrepareResponse() {
        super();
    }

    public TransferPrepareResponse(TransferPrepareInfo data) {
        super();
        this.data = data;
    }

    public TransferPrepareInfo getData() {
        return data;
    }

    public void setData(TransferPrepareInfo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TransferPrepareResponse{" +
                "data=" + data +
                '}';
    }
}
