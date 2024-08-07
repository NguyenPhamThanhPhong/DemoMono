package com.example.test_join.service.restclient;

import com.example.test_join.dto.client.request.BranchClientRequest;
import com.example.test_join.dto.client.response.BranchClientResponse;
import com.example.test_join.dto.server.response.BaseResponse;
import com.example.test_join.share.enums.ResponseEnum;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class BranchClient {
    public Mono<BaseResponse<BranchClientResponse>> getBranches(BranchClientRequest request) {
        BaseResponse<BranchClientResponse> result = BaseResponse.baseResponse(
                "b2cd72c0-61f7-4348-926d-8eefaca8e09f",
                ResponseEnum.SUCCESS);
            result.setData(BranchClientResponse.Default());
        return Mono.just(result);
    }
}
