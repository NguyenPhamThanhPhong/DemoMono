package com.example.test_join.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.test_join.dto.server.request.BaseRequest;
import com.example.test_join.dto.server.response.BaseResponse;
import com.example.test_join.dto.server.response.DualResponseDTO;
import com.example.test_join.service.restserver.IDualService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import reactor.core.publisher.Mono;

public class DualController {
    @Autowired
    private IDualService dualService;

    @PostMapping("")
    public Mono<BaseResponse<DualResponseDTO>> getDualInfo( @RequestBody BaseRequest<?> baseRequest) {
        return dualService.getDualInfo(baseRequest);
    }
    
}
