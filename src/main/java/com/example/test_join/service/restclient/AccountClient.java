package com.example.test_join.service.restclient;

import org.springframework.stereotype.Component;

import com.example.test_join.dto.client.request.AccountClientRequest;
import com.example.test_join.dto.client.response.AccountClientResponse;
import com.example.test_join.dto.server.request.BaseRequest;
import com.example.test_join.dto.server.response.BaseResponse;
import com.example.test_join.share.enums.ResponseEnum;

import reactor.core.publisher.Mono;

@Component
public class AccountClient {
    public Mono<BaseResponse<AccountClientResponse>> getAccountMono(
            BaseRequest<AccountClientRequest> request) {
        BaseResponse<AccountClientResponse> result = BaseResponse.baseResponse(
                request.getRequestId(),
                ResponseEnum.DATA_SUCCESS);
        result.setMessage(ResponseEnum.DATA_SUCCESS.getMessage());
        result.setData(AccountClientResponse.Default());
        return Mono.just(result);
    }
}
