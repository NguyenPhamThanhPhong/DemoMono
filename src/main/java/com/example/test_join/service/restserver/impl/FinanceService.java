package com.example.test_join.service.restserver.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test_join.dto.client.request.ExchangeRateClientRequest;
import com.example.test_join.dto.client.request.LoanDetailClientRequest;
import com.example.test_join.dto.server.request.BaseRequest;
import com.example.test_join.dto.server.response.BaseResponse;
import com.example.test_join.dto.server.response.ExchangeRateResponseDTO;
import com.example.test_join.dto.server.response.LoanDetailResponseDTO;
import com.example.test_join.mapper.IFinanceMapper;
import com.example.test_join.service.restclient.FinanceClient;
import com.example.test_join.service.restserver.IFinanceService;

import reactor.core.publisher.Mono;

@Service
public class FinanceService implements IFinanceService {

    @Autowired
    private FinanceClient financeClient;
    @Autowired
    private IFinanceMapper financeMapper;

    @Override
    public Mono<BaseResponse<List<ExchangeRateResponseDTO>>> getExchangeRate(
            BaseRequest<ExchangeRateClientRequest> baseRequest) {
        return financeClient.getExchangeRate(baseRequest)
                .flatMap(baseClientRes -> {
                    BaseResponse<List<ExchangeRateResponseDTO>> baseResponse = BaseResponse
                            .fromBaseResponse(baseClientRes);
                    baseResponse.setData(baseClientRes.getData()
                            .stream().map(financeMapper::fromExchangeRateResponseClientToDTO)
                            .toList());
                    
                    return Mono.just(baseResponse);
                });
    }

    @Override
    public Mono<BaseResponse<LoanDetailResponseDTO>> getLoanDetail(BaseRequest<LoanDetailClientRequest> baseRequest) {
        return financeClient.getLoanDetail(baseRequest)
                .flatMap(baseClientRes -> {
                    BaseResponse<LoanDetailResponseDTO> baseResponse = BaseResponse.fromBaseResponse(baseClientRes);
                    baseResponse.setData(financeMapper.fromLoanDetailResponseClientToDTO(baseClientRes.getData()));
                    return Mono.just(baseResponse);
                });
    }

}
