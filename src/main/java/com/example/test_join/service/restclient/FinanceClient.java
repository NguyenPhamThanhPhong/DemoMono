package com.example.test_join.service.restclient;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.test_join.dto.client.request.ExchangeRateClientRequest;
import com.example.test_join.dto.client.request.LoanDetailClientRequest;
import com.example.test_join.dto.client.response.ExchangeRateClientResponse;
import com.example.test_join.dto.client.response.LoanDetailClientResponse;
import com.example.test_join.dto.server.request.BaseRequest;
import com.example.test_join.dto.server.response.BaseResponse;

import reactor.core.publisher.Mono;

@Component
public class FinanceClient {
    public Mono<BaseResponse<List<ExchangeRateClientResponse>>> getExchangeRate(BaseRequest<ExchangeRateClientRequest> baseRequest) {
        BaseResponse<List<ExchangeRateClientResponse>> baseResponse = BaseResponse.fromBaseRequest(baseRequest);
        baseResponse.setData(Arrays.asList(
            ExchangeRateClientResponse.defaultResult(),
            ExchangeRateClientResponse.defaultResult(),
            ExchangeRateClientResponse.defaultResult(),
            ExchangeRateClientResponse.defaultResult()
        ));
        return Mono.just(baseResponse);
    }
    public Mono<BaseResponse<LoanDetailClientResponse>> getLoanDetail(BaseRequest<LoanDetailClientRequest> baseRequest) {
        BaseResponse<LoanDetailClientResponse> baseResponse = BaseResponse.fromBaseRequest(baseRequest);
        baseResponse.setData(LoanDetailClientResponse.template());
        return Mono.just(baseResponse);
    }
}
