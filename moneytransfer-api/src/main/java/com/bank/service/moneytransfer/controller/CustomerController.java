package com.bank.service.moneytransfer.controller;

import javax.validation.Valid;

import com.bank.service.moneytransfer.model.request.TransferVerifyRequest;
import com.bank.service.moneytransfer.model.response.TransferExecuteResponse;
import com.bank.service.moneytransfer.model.response.TransferPrepareResponse;
import com.bank.service.moneytransfer.model.response.TransferVerifyResponse;
import com.bank.service.moneytransfer.service.ICustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private/cliente/{customerId}/conto/{bankAccountNumber}")
public class CustomerController extends BaseApiController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Example of java doc.
     * @param   customerId          test
     * @param   bankAccountNumber   test
     * @return                      test
     */
    @GetMapping("/bonifico/prepare")
    public ResponseEntity transferPrepare(
            @PathVariable("customerId") Long customerId, @PathVariable("bankAccountNumber") String bankAccountNumber) {

        TransferPrepareResponse transferPrepareResponse = customerService
                .getTransferBasicInformation(customerId, bankAccountNumber);
        setRequestId(transferPrepareResponse);
        return ResponseEntity.ok().body(transferPrepareResponse);
    }

    /**
     * Example of java doc.
     * @param   customerId              test
     * @param   bankAccountNumber       test
     * @param   transferVerifyRequest   test
     * @return                          test
     * @see     TransferVerifyRequest
     */
    @PostMapping("/bonifico/verify")
    public ResponseEntity transferVerify(@PathVariable("customerId") Long customerId,
                                         @PathVariable("bankAccountNumber") String bankAccountNumber,
                                         @RequestBody @Valid TransferVerifyRequest transferVerifyRequest) {

        TransferVerifyResponse transferPrepareResponse = customerService
                .executeTransferVerify(customerId, bankAccountNumber, transferVerifyRequest.getData());
        setRequestId(transferPrepareResponse);
        return ResponseEntity.ok().body(transferPrepareResponse);
    }

    /**
     * Example of java doc.
     * @param   customerId              test
     * @param   bankAccountNumber       test
     * @param   transactionId           test
     * @return                          test
     */
    @PutMapping("/bonifico/{transactionId}/execute")
    public ResponseEntity transferExecute(@PathVariable("customerId") Long customerId,
                                          @PathVariable("bankAccountNumber") String bankAccountNumber,
                                          @PathVariable("transactionId") String transactionId) {

        TransferExecuteResponse transferExecuteResponse = customerService
                .executeTransferUltimate(customerId, bankAccountNumber, transactionId);
        setRequestId(transferExecuteResponse);
        return ResponseEntity.ok().body(transferExecuteResponse);
    }
}
