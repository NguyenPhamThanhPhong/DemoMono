package com.example.test_join.service.restserver;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.test_join.dto.client.request.ExchangeRateClientRequest;
import com.example.test_join.dto.client.request.LoanDetailClientRequest;
import com.example.test_join.dto.server.request.BaseRequest;
import com.example.test_join.dto.server.response.BaseResponse;
import com.example.test_join.dto.server.response.ExchangeRateResponseDTO;
import com.example.test_join.dto.server.response.LoanDetailResponseDTO;

import reactor.core.publisher.Mono;
@Service
public interface IFinanceService {
    public Mono<BaseResponse<List<ExchangeRateResponseDTO>>> getExchangeRate(BaseRequest<ExchangeRateClientRequest> baseRequest);
    public Mono<BaseResponse<LoanDetailResponseDTO>> getLoanDetail(BaseRequest<LoanDetailClientRequest> baseRequest);
}
