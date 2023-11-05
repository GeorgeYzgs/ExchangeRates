package com.ggiazitz.exchangeRates.controllers;

import com.ggiazitz.exchangeRates.dtos.ConversionRatesResponseDTO;
import com.ggiazitz.exchangeRates.dtos.ExchangeRatesDTO;
import com.ggiazitz.exchangeRates.models.CurrencyCode;
import com.ggiazitz.exchangeRates.services.ExchangeAPIService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
            summary = "Exchanges currency",
            description = "Returns the conversion rate from a source currency to a list of currencies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid user input", content = @Content)
    })
    @GetMapping("/exchange")
    public ExchangeRatesDTO exchange(@RequestParam CurrencyCode source, @RequestParam(required = false) List<CurrencyCode> currencies) {
        return exchangeAPIService.exchange(source, currencies);
    }

    @Operation(
            summary = "Converts currency",
            description = "Returns the amount of currency you would get from convert a given amount of source currency to a list of currencies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid user input", content = @Content)
    })
    @GetMapping("/convert")
    public ConversionRatesResponseDTO convert(@RequestParam CurrencyCode from, @RequestParam List<CurrencyCode> to, @RequestParam Double amount) {
        return exchangeAPIService.convert(from, to, amount);
    }
}
