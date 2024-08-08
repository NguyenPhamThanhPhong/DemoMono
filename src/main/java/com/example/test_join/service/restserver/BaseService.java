package com.example.test_join.service.restserver;

import com.example.test_join.dto.server.response.BaseResponse;
import com.example.test_join.exception.IntegrationException;
import com.example.test_join.share.enums.ResponseEnum;

import reactor.core.publisher.Mono;

public abstract class BaseService {
    protected Mono<Void> validateBaseResponse(BaseResponse<?> baseResponse, String dataName) {
        if (baseResponse == null) {
            return Mono.error(new IntegrationException(String.format("Failed to get %s", dataName)));
        }
        else if(baseResponse.getData() == null || 
        baseResponse.getMessage().equals(ResponseEnum.DATA_SUCCESS.getMessage()) 
        || baseResponse.getResponseCode().equals(ResponseEnum.DATA_SUCCESS.getResponseCode())) {
            return Mono.error(new IntegrationException(
                String.format("Failed to get [%s]: message is [%s]", 
                dataName,baseResponse.getMessage())));
        }
        return Mono.empty();
    }
}
