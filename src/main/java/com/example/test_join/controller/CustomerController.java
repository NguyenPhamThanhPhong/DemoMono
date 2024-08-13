package com.example.test_join.controller;

import com.example.test_join.dto.server.request.BaseRequest;
import com.example.test_join.dto.server.request.GetAccountRequest;
import com.example.test_join.dto.server.request.GetCustomerRequest;
import com.example.test_join.dto.server.response.AccountResponseDTO;
import com.example.test_join.dto.server.response.BaseResponse;
import com.example.test_join.dto.server.response.CustomerResponseDTO;
import com.example.test_join.service.restserver.IAccountService;
import com.example.test_join.service.restserver.ICustomerService;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
public class CustomerController {
    private final ICustomerService customerService;
    private final IAccountService accountService;

    @GetMapping("{clientNo}/profile/{requestId}")
    public Mono<BaseResponse<CustomerResponseDTO>> retrieveStaffInfo(
            @PathVariable("clientNo") String clientNo,
            @PathVariable String requestId) {
        BaseRequest<GetCustomerRequest> baseRequest = BaseRequest
                .<GetCustomerRequest>builder()
                .requestId(requestId).data(new GetCustomerRequest(clientNo)).build();
        return customerService.getCustomerInfo(baseRequest);
    }

    @GetMapping("{clientNo}/account/{requestId}")
    public Mono<BaseResponse<AccountResponseDTO>> getMethodName(@PathVariable("clientNo") String clientNo,
            @PathVariable String requestId) {
        // Xử lý mono
        GetAccountRequest getAccountRequest = new GetAccountRequest(clientNo);
        BaseRequest<GetAccountRequest> baseRequest = BaseRequest.<GetAccountRequest>builder()
                .data(getAccountRequest)
                .requestId(requestId).build();
        baseRequest.setData(new GetAccountRequest(clientNo));
        return accountService.getAccountInfo(baseRequest);
    }

}
