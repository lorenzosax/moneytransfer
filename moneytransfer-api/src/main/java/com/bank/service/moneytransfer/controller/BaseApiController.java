package com.bank.service.moneytransfer.controller;

import com.bank.service.moneytransfer.model.response.BaseResponse;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
public class BaseApiController {

    private static final String REQUEST_ID = "RequestId";

    /**
     * Set a request id to BaseResponse object pass as parameter.
     * @param baseResponse  base response used to append unique identifier
     */
    public void setRequestId(BaseResponse baseResponse) {
        if (baseResponse != null) {
            baseResponse.setRequestId(MDC.get(REQUEST_ID));
        }
    }
}
