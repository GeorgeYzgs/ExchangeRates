package com.ggiazitz.exchangeRates.dtos;

import java.util.Map;

public record ExchangeRatesDTO(Long timestamp, String source, Map<String, Double> quotes) {

}
