package com.example.test_join.service.restclient;

import com.example.test_join.dto.client.response.DualClientResponse;
import com.example.test_join.dto.server.request.BaseRequest;
import com.example.test_join.dto.server.response.BaseResponse;

import reactor.core.publisher.Mono;

public class DualClient {
    public Mono<BaseResponse<DualClientResponse>> getDualInfo(BaseRequest<?> baseRequest) {
        BaseResponse<DualClientResponse> baseResponse = BaseResponse.fromBaseRequest(baseRequest);
        baseResponse.setData(DualClientResponse.template());
        return Mono.just(baseResponse);
    }

}
