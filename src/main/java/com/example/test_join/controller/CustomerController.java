package com.example.test_join.controller;

import com.example.test_join.dto.server.request.GetCustomerRequest;
import com.example.test_join.dto.server.response.BaseResponse;
import com.example.test_join.dto.server.response.CustomerResponseDTO;
import com.example.test_join.service.restserver.CustomerService;
import com.example.test_join.service.restserver.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("staff-info")
@RequiredArgsConstructor
public class CustomerController {
    private final ICustomerService customerService;

    @GetMapping("{clientNo}")
    public Mono<BaseResponse<CustomerResponseDTO>> retrieveStaffInfo(@PathVariable("clientNo") String clientNo) {
        return customerService.getCustomerInfo(new GetCustomerRequest(clientNo));
    }
}
