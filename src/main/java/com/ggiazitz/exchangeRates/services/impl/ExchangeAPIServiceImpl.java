package com.ggiazitz.exchangeRates.services.impl;

import com.ggiazitz.exchangeRates.dtos.ExchangeRatesDTO;
import com.ggiazitz.exchangeRates.services.ExchangeAPIService;
import org.springframework.stereotype.Service;

@Service
public class ExchangeAPIServiceImpl implements ExchangeAPIService {

    @Override
    public ExchangeRatesDTO exchange(String source, String currencies) {
        return null;
    }
}
