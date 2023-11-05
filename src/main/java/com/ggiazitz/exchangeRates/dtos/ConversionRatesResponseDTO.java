package com.ggiazitz.exchangeRates.dtos;

import java.util.List;

public record ConversionRatesResponseDTO(Long timestamp, Double amount, List<ConversionRatesDTO> conversionQuotes) {
}
