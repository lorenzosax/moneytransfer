package com.bank.service.moneytransfer.controller;

import java.util.Date;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bank.service.moneytransfer.model.pojo.Amount;
import com.bank.service.moneytransfer.model.pojo.BankTransferData;
import com.bank.service.moneytransfer.model.pojo.OutcomeEnum;
import com.bank.service.moneytransfer.model.request.TransferVerifyRequest;
import com.bank.service.moneytransfer.model.response.TransferVerifyResponse;
import com.bank.service.moneytransfer.utils.DateUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerIntegrationTest {

    private static final Logger logger = LoggerFactory.getLogger(CustomerControllerIntegrationTest.class);

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    private static Long customerId;
    private static String bankAccountNumber;
    private static String amountToTransfer;
    private static String currencyCode;
    private static Date now;

    @BeforeClass
    public static void beforeClass() {
        customerId = 123L;
        bankAccountNumber = "0001234566";
        amountToTransfer = "123.5";
        currencyCode = "EUR";
        now = new Date();
    }

    @Test
    public void transferPrepare() throws Exception {
        String today = DateUtils.getFormattedDate(now);
        String transferLimitDateFormatted = DateUtils.addDaysAndGetFormattedDate(now, 30);
        mvc.perform(get("/private/cliente/{customerId}/conto/{bankAccountNumber}/bonifico/prepare",
                customerId, bankAccountNumber))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.fullName", is("Lorenzo Gagliani")))
                .andExpect(jsonPath("$.data.transferLimitDate", is(transferLimitDateFormatted)))
                .andExpect(jsonPath("$.data.todayDate", is(today)))
                .andExpect(jsonPath("$.data.accountNumber", is(bankAccountNumber)))
                .andExpect(jsonPath("$.result.outcome", is(OutcomeEnum.SUCCESS.toString())));
    }

    @Test
    public void transferVerifyAndExecute() throws Exception {
        BankTransferData bankTransferData = new BankTransferData();
        bankTransferData.setBeneficiaryName("Mario Rossi");
        bankTransferData.setBeneficiaryIban("IT32D0300203280576893493775");
        bankTransferData.setPaymentReason("Test causale pagamento");
        bankTransferData.setExecutionDate(DateUtils.addDaysAndGetFormattedDate(now, 10));
        bankTransferData.setAmount(new Amount(currencyCode, amountToTransfer));

        TransferVerifyRequest request = new TransferVerifyRequest();
        request.setData(bankTransferData);

        ResultActions resultActions =
                mvc.perform(post("/private/cliente/{customerId}/conto/{bankAccountNumber}/bonifico/verify",
                        customerId, bankAccountNumber)
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.data.amount.currency", is(amountToTransfer)))
                        .andExpect(jsonPath("$.data.amount.currencyCode", is(currencyCode)))
                        .andExpect(jsonPath("$.data.commissions.amount", is("0")))
                        .andExpect(jsonPath("$.data.commissions.currencyCode", is(currencyCode)))
                        .andExpect(jsonPath("$.result.outcome", is(OutcomeEnum.SUCCESS.toString())));

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        TransferVerifyResponse response = objectMapper.readValue(contentAsString, TransferVerifyResponse.class);

        String transactionId = response.getTransaction().getId();
        logger.info("Transaction id: " + transactionId);

        mvc.perform(put("/private/cliente/{customerId}/conto/{bankAccountNumber}"
                + "/bonifico/{transactionId}/execute", customerId, bankAccountNumber, transactionId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result.messages", hasSize(2)))
                .andExpect(jsonPath("$.result.outcome", is(OutcomeEnum.SUCCESS.toString())));
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
