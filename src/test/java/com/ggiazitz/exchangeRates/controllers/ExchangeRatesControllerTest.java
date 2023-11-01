package com.ggiazitz.exchangeRates.controllers;

import com.ggiazitz.exchangeRates.services.ExchangeAPIService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExchangeRatesController.class)
public class ExchangeRatesControllerTest {


    @Autowired
    MockMvc mockMvc;

    @MockBean
    ExchangeAPIService exchangeAPIService;

    @Test
    public void givenValidRequest_whenRequestingExchangeRates_thenReturnExchangeRates() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/v1/exchange?source=EUR").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request).andExpect(status().isOk());
    }
}