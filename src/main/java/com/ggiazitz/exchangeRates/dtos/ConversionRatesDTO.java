package com.ggiazitz.exchangeRates.dtos;

import com.ggiazitz.exchangeRates.models.CurrencyCode;

public record ConversionRatesDTO(CurrencyCode source, CurrencyCode currencies, Double amount, Double quote,
                                 Double result) {
}
