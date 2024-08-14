package com.example.test_join.controller;

import com.example.test_join.dto.client.request.AccountClientRequest;
import com.example.test_join.dto.client.request.CustomerClientRequest;
import com.example.test_join.dto.client.request.CustomerGlobalClientRequest;
import com.example.test_join.dto.server.request.BaseRequest;
import com.example.test_join.dto.server.request.GetAccountRequest;
import com.example.test_join.dto.server.request.GetCustomerGlobalRequest;
import com.example.test_join.dto.server.request.GetCustomerRequest;
import com.example.test_join.dto.server.response.AccountResponseDTO;
import com.example.test_join.dto.server.response.BaseResponse;
import com.example.test_join.dto.server.response.CustomerResponseDTO;
import com.example.test_join.service.restserver.IAccountService;
import com.example.test_join.service.restserver.ICustomerService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
@Slf4j
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IAccountService accountService;

    @PostMapping("/profile")
    public Mono<BaseResponse<CustomerResponseDTO>> getCustomer(@RequestBody BaseRequest<GetCustomerRequest> baseRequest) {
        BaseRequest<CustomerClientRequest> customerClientRequest = BaseRequest.fromBaseRequest(baseRequest);
        customerClientRequest.setData(CustomerClientRequest.template(baseRequest.getData().getClientNo()));
        return customerService.getCustomerInfo(customerClientRequest);
    }


    @PostMapping("/account")
    public Mono<BaseResponse<AccountResponseDTO>> getAccount(@RequestBody BaseRequest<GetAccountRequest> baseRequest) {
        GetAccountRequest getAccountRequest = baseRequest.getData();
        BaseRequest<AccountClientRequest> accountClientRequest = BaseRequest.fromBaseRequest(baseRequest);
        accountClientRequest.setData(
                AccountClientRequest.template(getAccountRequest.getClientNo(), getAccountRequest.getCtrlBranch()));
        return accountService.getAccountInfo(accountClientRequest);
    }

    @PostMapping("/global/profile")
    public Mono<BaseResponse<CustomerResponseDTO>> getCustomerByGlobalId(
            @RequestBody BaseRequest<GetCustomerGlobalRequest> baseRequest) {
        BaseRequest<CustomerGlobalClientRequest> customerGlobalClientRequest = BaseRequest.fromBaseRequest(baseRequest);
        customerGlobalClientRequest.setData(CustomerGlobalClientRequest.template(baseRequest.getData().getGlobalId()));
        return customerService.getCustomerGlobalInfo(customerGlobalClientRequest);
    }

}
