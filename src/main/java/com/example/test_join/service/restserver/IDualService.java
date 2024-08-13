package com.example.test_join.service.restserver;

import org.springframework.stereotype.Service;

import com.example.test_join.dto.server.request.BaseRequest;
import com.example.test_join.dto.server.response.BaseResponse;
import com.example.test_join.dto.server.response.DualResponseDTO;

import reactor.core.publisher.Mono;

@Service
public interface IDualService {
    public Mono<BaseResponse<DualResponseDTO>> getDualInfo(BaseRequest<?> baseRequest);
}
