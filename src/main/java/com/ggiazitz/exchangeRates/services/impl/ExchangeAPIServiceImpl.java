package com.ggiazitz.exchangeRates.services.impl;

import com.ggiazitz.exchangeRates.dtos.ExchangeRatesDTO;
import com.ggiazitz.exchangeRates.models.CurrencyCode;
import com.ggiazitz.exchangeRates.services.ExchangeAPIService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@PropertySource(value = "classpath:application.properties")
public class ExchangeAPIServiceImpl implements ExchangeAPIService {


    @Value("${exchangeRates.access_key}")
    private String accessKey;

    @Value("${exchangeRates.url}")
    private String apiUrl;
    private final WebClient webClient;

    public ExchangeAPIServiceImpl(@Value("${exchangeRates.access_key}") String accessKey, @Value("${exchangeRates.url}") String apiUrl) {
        this.accessKey = accessKey;
        this.apiUrl = apiUrl;
        this.webClient = WebClient.builder().baseUrl(apiUrl).build();

    }

    @Override
    public ExchangeRatesDTO exchange(CurrencyCode source, List<CurrencyCode> currencies) {

        return null;
    }
}
