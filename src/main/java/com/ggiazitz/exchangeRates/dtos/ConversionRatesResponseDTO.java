package com.ggiazitz.exchangeRates.dtos;

import java.io.Serializable;
import java.util.List;

public record ConversionRatesResponseDTO(Long timestamp, Double amount,
                                         List<ConversionRatesDTO> conversionQuotes) implements Serializable {
}
