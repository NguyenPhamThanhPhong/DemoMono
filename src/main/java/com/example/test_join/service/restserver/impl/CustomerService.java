package com.example.test_join.service.restserver.impl;

import com.example.test_join.dto.client.request.BranchClientRequest;
import com.example.test_join.dto.client.request.CountryClientRequest;
import com.example.test_join.dto.client.response.BranchClientResponse;
import com.example.test_join.dto.client.response.CountryClientResponse;
import com.example.test_join.service.restclient.BranchClient;
import com.example.test_join.service.restclient.CountryClient;

import static com.example.test_join.share.enums.ResponseEnum.DATA_SUCCESS;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test_join.dto.client.request.CustomerClientRequest;
import com.example.test_join.dto.client.response.CustomerClientResponse;
import com.example.test_join.dto.server.request.BaseRequest;
import com.example.test_join.dto.server.request.GetCustomerRequest;
import com.example.test_join.dto.server.response.BaseResponse;
import com.example.test_join.dto.server.response.CustomerResponseDTO;
import com.example.test_join.exception.ResourceNotFoundException;
import com.example.test_join.service.restclient.CustomerClient;
import com.example.test_join.service.restserver.ICustomerService;

import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerClient customerClient;
    @Autowired
    private BranchClient branchClient;
    @Autowired
    private CountryClient countryClient;

    @Override
    public Mono<BaseResponse<CustomerResponseDTO>> getCustomerInfo(BaseRequest<GetCustomerRequest> baseRequest) {
        GetCustomerRequest request = baseRequest.getData();
        return Mono.deferContextual(
                contextView -> {
                    CustomerClientRequest customerClientRequest = CustomerClientRequest.template(request.getClientNo());
                    BaseRequest<CustomerClientRequest> baseRequestCustomer = BaseRequest
                            .<CustomerClientRequest>builder()
                            .data(customerClientRequest)
                            .requestId(baseRequest.getRequestId())
                            .requestDate(baseRequest.getRequestDate())
                            .requestUser(baseRequest.getRequestUser())
                            .requestChannel(baseRequest.getRequestChannel())
                            .build();
                    return customerClient.getCustomerMono(baseRequestCustomer);
                }).flatMap(
                        res -> {
                            CustomerResponseDTO customerResponseDTO = CustomerResponseDTO
                                    .fromCustomerClientResponse(res.getData());
                            // PROMISE ALL
                            Mono<Tuple2<BaseResponse<BranchClientResponse>, BaseResponse<CountryClientResponse>>> monoBranchCountry = GetBranchAndCountry(
                                    baseRequest, res);
                            monoBranchCountry.subscribe(
                                    result -> {
                                        BaseResponse<BranchClientResponse> branchResponse = result.getT1();
                                        BaseResponse<CountryClientResponse> countryResponse = result.getT2();
                                        customerResponseDTO.setBranchName(branchResponse.getData().getBranchName());
                                        customerResponseDTO.setCountryDesc(countryResponse.getData().getCountryDesc());
                                    });
                            BaseResponse<CustomerResponseDTO> baseResponse = new BaseResponse<>();
                            baseResponse.setRequestId(baseRequest.getRequestId());
                            baseResponse.setResponseCode(DATA_SUCCESS.getCode());
                            baseResponse.setMessage(DATA_SUCCESS.getMessage());
                            baseResponse.setTimestamp(new Date());
                            baseResponse.setData(customerResponseDTO);
                            return Mono.just(baseResponse);
                        });
    }

    private Mono<Tuple2<BaseResponse<BranchClientResponse>, BaseResponse<CountryClientResponse>>> GetBranchAndCountry(
            BaseRequest<?> baseRequest, BaseResponse<CustomerClientResponse> res) {
        BranchClientRequest branchClientRequest = new BranchClientRequest(res.getData().getCtrlBranch());
        CountryClientRequest countryClientRequest = new CountryClientRequest(res.getData().getCountryCitizen());

        BaseRequest<BranchClientRequest> baseBranchClientRequest = BaseRequest
                .<BranchClientRequest>fromBaseRequest(baseRequest);
        baseBranchClientRequest.setData(branchClientRequest);
        BaseRequest<CountryClientRequest> baseCountryClientRequest = BaseRequest
                .<CountryClientRequest>fromBaseRequest(baseRequest);
        baseCountryClientRequest.setData(countryClientRequest);

        Mono<BaseResponse<BranchClientResponse>> branchResponsesMono = validateCustomerBranch(
                res.getData()).then(branchClient.getBranchMono(baseBranchClientRequest))
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Branch not found")));
        Mono<BaseResponse<CountryClientResponse>> countryResponseMono = validateCustomerCountry(
                res.getData())
                .then(countryClient.getCountries(baseCountryClientRequest))
                .switchIfEmpty(Mono.just(new BaseResponse<CountryClientResponse>()));
        return Mono.zip(branchResponsesMono, countryResponseMono);
    }

    private Mono<Void> validateCustomerBranch(CustomerClientResponse responseDTO) {
        if (responseDTO.getCtrlBranch() == null) {
            return Mono.error(new RuntimeException());
        }
        return Mono.empty();
    }

    private Mono<Void> validateCustomerCountry(CustomerClientResponse responseDTO) {
        if (responseDTO.getCountryCitizen() == null) {
            return Mono.error(new RuntimeException());
        }
        return Mono.empty();
    }

}
