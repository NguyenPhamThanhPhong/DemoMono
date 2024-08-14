package com.example.test_join.service.restclient;

import com.example.test_join.dto.client.request.CustomerClientRequest;
import com.example.test_join.dto.client.request.CustomerGlobalClientRequest;
import com.example.test_join.dto.client.response.CustomerClientResponse;
import com.example.test_join.dto.server.request.BaseRequest;
import com.example.test_join.dto.server.response.BaseResponse;
import com.example.test_join.share.enums.ResponseEnum;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CustomerClient {
        public Mono<BaseResponse<CustomerClientResponse>> getCustomerMono(BaseRequest<CustomerClientRequest> request) {
                BaseResponse<CustomerClientResponse> result = BaseResponse.baseResponse(
                                "b2cd72c0-61f7-4348-926d-8eefaca8e09f",
                                ResponseEnum.SUCCESS);
                result.setData(CustomerClientResponse.Default());
                return Mono.just(result);
        }

        public Mono<BaseResponse<CustomerClientResponse>> getCientNo(BaseRequest<CustomerClientRequest> request) {
                BaseResponse<CustomerClientResponse> result = BaseResponse.baseResponse(
                                "b2cd72c0-61f7-4348-926d-8eefaca8e09f",
                                ResponseEnum.SUCCESS);
                CustomerClientResponse data = new CustomerClientResponse();
                data.setClientNo("DEFAULT CLIENT NO");
                data.setCtrlBranch("DEFAULT BRANCH");
                result.setData(data);
                return Mono.just(result);
        }

        public Mono<BaseResponse<CustomerClientResponse>> getCustomerGlobalMono(BaseRequest<CustomerGlobalClientRequest> request) {
                BaseResponse<CustomerClientResponse> result = BaseResponse.baseResponse(
                                "b2cd72c0-61f7-4348-926d-8eefaca8e09f",
                                ResponseEnum.SUCCESS);
                result.setData(CustomerClientResponse.Default());
                return Mono.just(result);
        }


}
