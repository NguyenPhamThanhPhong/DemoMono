package com.example.test_join.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.test_join.dto.client.request.ExchangeRateClientRequest;
import com.example.test_join.dto.server.request.BaseRequest;
import com.example.test_join.dto.server.response.BaseResponse;
import com.example.test_join.dto.server.response.ExchangeRateResponseDTO;
import com.example.test_join.service.restserver.IFinanceService;

import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/finances")
public class FinanceController {
    @Autowired
    private IFinanceService financeService;

    @PostMapping("/exchange-rate")
    public Mono<BaseResponse<List<ExchangeRateResponseDTO>>> getExchangeRate(
            @RequestBody BaseRequest<ExchangeRateClientRequest> baseRequest) {
        return financeService.getExchangeRate(baseRequest);
    }
}
