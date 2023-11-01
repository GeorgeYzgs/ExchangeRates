package com.ggiazitz.exchangeRates.services;

import com.ggiazitz.exchangeRates.dtos.ExchangeRatesDTO;

public interface ExchangeAPIService {

    ExchangeRatesDTO exchange(String source, String currencies);
}
