package com.example.test_join.service.restserver;

import com.example.test_join.dto.server.request.BaseRequest;
import com.example.test_join.dto.server.request.GetCustomerRequest;
import com.example.test_join.dto.server.response.BaseResponse;
import com.example.test_join.dto.server.response.CustomerResponseDTO;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface ICustomerService {
    Mono<BaseResponse<CustomerResponseDTO>> getCustomerInfo(BaseRequest<GetCustomerRequest> request);
}
