package com.bank.service.moneytransfer.controller;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bank.service.moneytransfer.model.pojo.OutcomeEnum;
import com.bank.service.moneytransfer.utils.DateUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    private static Long customerId;
    private static String bankAccountNumber;
    private static Date now;

    @BeforeClass
    public static void beforeClass() {
        customerId = 123L;
        bankAccountNumber = "0001234566";
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
}
