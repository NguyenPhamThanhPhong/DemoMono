package com.example.test_join.dto.server.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExchangeRateResponseDTO {
    private String ccy;
    private String ccyRate;
}
