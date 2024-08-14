package com.example.test_join.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.test_join.dto.server.request.BaseRequest;
import com.example.test_join.dto.server.response.BaseResponse;
import com.example.test_join.dto.server.response.DualResponseDTO;
import com.example.test_join.service.restserver.IDualService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/duals")
@RequiredArgsConstructor
public class DualController {
    
    @Autowired
    private IDualService dualService;

    @PostMapping("")
    public Mono<BaseResponse<DualResponseDTO>> getDualInfo(@RequestBody BaseRequest<?> baseRequest) {
        return dualService.getDualInfo(baseRequest);
    }

}
