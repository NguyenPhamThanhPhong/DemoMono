package com.example.test_join.service.restclient;

import com.example.test_join.dto.client.request.CountryClientRequest;
import com.example.test_join.dto.client.response.CountryClientResponse;
import com.example.test_join.dto.server.request.BaseRequest;
import com.example.test_join.dto.server.response.BaseResponse;
import com.example.test_join.share.enums.ResponseEnum;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component

public class CountryClient {
    
    public Mono<BaseResponse<CountryClientResponse>> getCountries(BaseRequest<CountryClientRequest> request) {
        BaseResponse<CountryClientResponse> result = BaseResponse.baseResponse(
                "b2cd72c0-61f7-4348-926d-8eefaca8e09f",
                ResponseEnum.SUCCESS);
        result.setData(CountryClientResponse.Default());
        return Mono.just(result);
    }
    
}
