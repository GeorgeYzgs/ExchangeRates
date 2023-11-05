package com.ggiazitz.exchangeRates.services.impl;

import com.ggiazitz.exchangeRates.dtos.ConversionRatesResponseDTO;
import com.ggiazitz.exchangeRates.dtos.ExchangeRatesDTO;
import com.ggiazitz.exchangeRates.models.CurrencyCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ExchangeAPIServiceImplTest {

    @InjectMocks
    ExchangeAPIServiceImpl exchangeAPIService;

    @Test
    public void givenValidRequest_whenConvertingCurrency_thenReturnCorrectCurrencyConversion() {

        Double amount = 10d;
        Map<String, Double> quotes = new HashMap<>();
        double quote = 1.054597;
        String fromTo = "EURUSD";
        quotes.put(fromTo, quote);
        double result = amount * quote;
        ExchangeRatesDTO response = new ExchangeRatesDTO(System.currentTimeMillis(), CurrencyCode.EUR, quotes);

        ConversionRatesResponseDTO conversionRatesResponseDTO = exchangeAPIService.mapConversionResult(amount, response);
        assertEquals(amount, conversionRatesResponseDTO.amount());
        assertEquals(quote, conversionRatesResponseDTO.conversionQuotes().get(0).quote());
        assertEquals(fromTo, conversionRatesResponseDTO.conversionQuotes().get(0).fromTo());
        assertEquals(result, conversionRatesResponseDTO.conversionQuotes().get(0).result());

    }
}