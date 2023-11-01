package com.ggiazitz.exchangeRates.controllers;

import com.ggiazitz.exchangeRates.dtos.ConversionRatesDTO;
import com.ggiazitz.exchangeRates.dtos.ExchangeRatesDTO;
import com.ggiazitz.exchangeRates.services.ExchangeAPIService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class ExchangeRatesController {


    private final ExchangeAPIService exchangeAPIService;

    public ExchangeRatesController(ExchangeAPIService exchangeAPIService) {
        this.exchangeAPIService = exchangeAPIService;
    }

    @GetMapping("/exchange")
    public ExchangeRatesDTO exchange(@RequestParam String source, @RequestParam(required = false) String currencies){
        System.out.println("hello");
        return null;
    }

    @GetMapping("/convert")
    public ConversionRatesDTO convert(@RequestParam String from, @RequestParam List<String> to, @RequestParam Integer amount){
        return null;
    }
}
