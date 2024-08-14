package com.example.test_join.dto.client.response;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ExchangeRateClientResponse {
    private String ccy;
    private String ccyRate;

    public static ExchangeRateClientResponse defaultResult() {
        return ExchangeRateClientResponse.builder()
                .ccy("USD")
                .ccyRate("1.0")
                .build();
    }

}
