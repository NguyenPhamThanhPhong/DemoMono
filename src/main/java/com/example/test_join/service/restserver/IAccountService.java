package com.example.test_join.service.restserver;

import org.springframework.stereotype.Service;

import com.example.test_join.dto.client.request.AccountClientRequest;
import com.example.test_join.dto.server.request.BaseRequest;
import com.example.test_join.dto.server.response.AccountResponseDTO;
import com.example.test_join.dto.server.response.BaseResponse;

import reactor.core.publisher.Mono;
@Service
public interface IAccountService {
        Mono<BaseResponse<AccountResponseDTO>> getAccountInfo(BaseRequest<AccountClientRequest> baseRequest);
}
