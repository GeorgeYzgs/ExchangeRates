package com.ggiazitz.exchangeRates.services.impl;

import com.ggiazitz.exchangeRates.dtos.ConversionRatesDTO;
import com.ggiazitz.exchangeRates.dtos.ConversionRatesResponseDTO;
import com.ggiazitz.exchangeRates.dtos.ExchangeRatesDTO;
import com.ggiazitz.exchangeRates.models.CurrencyCode;
import com.ggiazitz.exchangeRates.services.ExchangeAPIService;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        this.webClient = WebClient.builder().baseUrl(this.apiUrl).build();
    }

    @Override
    @Cacheable("Currencies")
    public ExchangeRatesDTO exchange(CurrencyCode source, List<CurrencyCode> currencies) {
        if (currencies == null) {
            return exchangeAllCurrencies(source);
        }
        return exchangeCurrencies(source, currencies);
    }

    @Override
    public ConversionRatesResponseDTO convert(CurrencyCode from, List<CurrencyCode> to, Double amount) {
        ExchangeRatesDTO response = webClient.get().uri(uriBuilder -> uriBuilder.path("/live")
                        .queryParam("source", from)
                        .queryParam("currencies", StringUtils.join(to.stream().map(Object::toString).collect(Collectors.toList()), ','))
                        .queryParam("access_key", accessKey).build())
                .retrieve().bodyToMono(ExchangeRatesDTO.class).block();
        return mapConversionResult(amount, response);
    }

    public ConversionRatesResponseDTO mapConversionResult(Double amount, ExchangeRatesDTO response) {
        List<ConversionRatesDTO> currencyConversion = new ArrayList<>();
        for (var mapEntry : response.quotes().entrySet()) {
            String currencyPair = mapEntry.getKey();
            Double quote = mapEntry.getValue();
            Double result = quote * amount;
            ConversionRatesDTO entry = new ConversionRatesDTO(currencyPair, quote, result);
            currencyConversion.add(entry);
        }
        return new ConversionRatesResponseDTO(System.currentTimeMillis(), amount, currencyConversion);
    }

    public ExchangeRatesDTO exchangeCurrencies(CurrencyCode source, List<CurrencyCode> currencies) {
        return webClient.get().uri(uriBuilder -> uriBuilder.path("/live")
                        .queryParam("source", source)
                        .queryParam("currencies", StringUtils.join(currencies.stream().map(Object::toString).collect(Collectors.toList()), ','))
                        .queryParam("access_key", accessKey).build())
                .retrieve().bodyToMono(ExchangeRatesDTO.class).block();
    }

    public ExchangeRatesDTO exchangeAllCurrencies(CurrencyCode source) {
        return webClient.get().uri(uriBuilder -> uriBuilder.path("/live")
                        .queryParam("source", source)
                        .queryParam("access_key", accessKey).build())
                .retrieve().bodyToMono(ExchangeRatesDTO.class).block();
    }
}
