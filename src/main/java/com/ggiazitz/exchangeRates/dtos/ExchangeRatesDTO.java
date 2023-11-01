package com.ggiazitz.exchangeRates.dtos;

import com.ggiazitz.exchangeRates.models.CurrencyCode;

import java.util.Map;

public record ExchangeRatesDTO(Long timestamp, CurrencyCode source, Map<String, Double> quotes) {

}
