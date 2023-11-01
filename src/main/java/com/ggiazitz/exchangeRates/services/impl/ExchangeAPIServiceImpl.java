package com.ggiazitz.exchangeRates.services.impl;

import com.ggiazitz.exchangeRates.dtos.ExchangeRatesDTO;
import com.ggiazitz.exchangeRates.models.CurrencyCode;
import com.ggiazitz.exchangeRates.services.ExchangeAPIService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeAPIServiceImpl implements ExchangeAPIService {

    @Override
    public ExchangeRatesDTO exchange(CurrencyCode source, List<CurrencyCode> currencies) {
        return null;
    }
}
