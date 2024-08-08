package com.example.test_join.service.restserver;

import com.example.test_join.dto.server.request.BaseRequest;
import com.example.test_join.dto.server.request.GetAccountRequest;
import com.example.test_join.dto.server.response.AccountResponseDTO;
import com.example.test_join.dto.server.response.BaseResponse;

import reactor.core.publisher.Mono;

public interface IAccountService {
        Mono<BaseResponse<AccountResponseDTO>> getAccountInfo(BaseRequest<GetAccountRequest> baseRequest);
}
