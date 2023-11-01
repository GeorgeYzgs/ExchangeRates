package com.ggiazitz.exchangeRates.services;

import com.ggiazitz.exchangeRates.dtos.ExchangeRatesDTO;
import com.ggiazitz.exchangeRates.models.CurrencyCode;

import java.util.List;

public interface ExchangeAPIService {

    ExchangeRatesDTO exchange(CurrencyCode source, List<CurrencyCode> currencies);
}
