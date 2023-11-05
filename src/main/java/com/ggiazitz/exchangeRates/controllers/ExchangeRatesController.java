package com.ggiazitz.exchangeRates.controllers;

import com.ggiazitz.exchangeRates.dtos.ConversionRatesResponseDTO;
import com.ggiazitz.exchangeRates.dtos.ExchangeRatesDTO;
import com.ggiazitz.exchangeRates.models.CurrencyCode;
import com.ggiazitz.exchangeRates.services.ExchangeAPIService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
//TODO change to reactive
//TODO implement hibernate validator for user input
public class ExchangeRatesController {

    private final ExchangeAPIService exchangeAPIService;

    public ExchangeRatesController(ExchangeAPIService exchangeAPIService) {
        this.exchangeAPIService = exchangeAPIService;
    }

    @GetMapping("/exchange")
    public ExchangeRatesDTO exchange(@RequestParam CurrencyCode source, @RequestParam(required = false) List<CurrencyCode> currencies) {
        return exchangeAPIService.exchange(source, currencies);
    }

    @GetMapping("/convert")
    public ConversionRatesResponseDTO convert(@RequestParam CurrencyCode from, @RequestParam List<CurrencyCode> to, @RequestParam Double amount) {
        return exchangeAPIService.convert(from, to, amount);
    }
}
