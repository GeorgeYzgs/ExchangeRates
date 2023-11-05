package com.ggiazitz.exchangeRates.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ggiazitz.exchangeRates.dtos.ConversionRatesDTO;
import com.ggiazitz.exchangeRates.dtos.ConversionRatesResponseDTO;
import com.ggiazitz.exchangeRates.dtos.ExchangeRatesDTO;
import com.ggiazitz.exchangeRates.models.CurrencyCode;
import com.ggiazitz.exchangeRates.services.ExchangeAPIService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ExchangeRatesController.class)
public class ExchangeRatesControllerTest {


    @Autowired
    MockMvc mockMvc;

    @MockBean
    ExchangeAPIService exchangeAPIService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void givenValidRequest_whenRequestingExchangeRates_thenReturnExchangeRates() throws Exception {
        CurrencyCode source = CurrencyCode.EUR;

        RequestBuilder request = MockMvcRequestBuilders.get("/v1/exchange?source=" + source).accept(MediaType.APPLICATION_JSON);

        Map<String, Double> quotes = new HashMap<>();
        quotes.put("EURUSD", 1.054597);
        long timestamp = System.currentTimeMillis();
        ExchangeRatesDTO exchangeRatesDTO = new ExchangeRatesDTO(timestamp, source, quotes);

        when(exchangeAPIService.exchange(source, null)).thenReturn(exchangeRatesDTO);

        mockMvc.perform(request).andExpect(status().isOk())
                .andExpect(jsonPath("$.timestamp").value(timestamp))
                .andExpect(jsonPath("$.quotes", Matchers.hasEntry("EURUSD", 1.054597)));
    }

    @Test
    public void givenInvalidRequest_whenRequestingExchangeRates_thenReturnBadRequest() throws Exception {
        CurrencyCode source = null;

        RequestBuilder request = MockMvcRequestBuilders.get("/v1/exchange?source=" + source).accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request).andExpect(status().isBadRequest());
    }

    @Test
    public void givenValidRequest_whenRequestingCurrencyConversion_thenReturnCurrencyConversion() throws Exception {
        CurrencyCode from = CurrencyCode.USD;
        CurrencyCode to = CurrencyCode.EUR;
        String currencyPair = "USDEUR";
        Double amount = 10d;

        RequestBuilder request = MockMvcRequestBuilders.get("/v1/convert?from=" + from + "&to=" + to + "&amount=" + amount)
                .accept(MediaType.APPLICATION_JSON);

        long timestamp = System.currentTimeMillis();
        double quote = 0.93145;
        double result = amount * quote;
        ConversionRatesDTO conversionRatesDTO = new ConversionRatesDTO(currencyPair, quote, result);
        ConversionRatesResponseDTO exchangeRatesDTO = new ConversionRatesResponseDTO(timestamp, amount, List.of(conversionRatesDTO));

        String response = objectMapper.writeValueAsString(exchangeRatesDTO);

        when(exchangeAPIService.convert(from, List.of(to), amount)).thenReturn(exchangeRatesDTO);

        mockMvc.perform(request).andExpect(status().isOk())
                .andExpect(content().json(response));
    }
}