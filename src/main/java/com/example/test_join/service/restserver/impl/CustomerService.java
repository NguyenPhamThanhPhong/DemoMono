package com.example.test_join.service.restserver.impl;


import com.example.test_join.dto.client.request.BranchClientRequest;
import com.example.test_join.dto.client.request.CountryClientRequest;
import com.example.test_join.dto.client.response.BranchClientResponse;
import com.example.test_join.dto.client.response.CountryClientResponse;
import com.example.test_join.service.restclient.BranchClient;
import com.example.test_join.service.restclient.CountryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test_join.dto.client.request.CustomerClientRequest;
import com.example.test_join.dto.client.response.CustomerClientResponse;
import com.example.test_join.dto.server.request.GetCustomerRequest;
import com.example.test_join.dto.server.response.BaseResponse;
import com.example.test_join.dto.server.response.CustomerResponseDTO;
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
    public Mono<BaseResponse<CustomerResponseDTO>> getCustomerInfo(GetCustomerRequest request) {
        CustomerClientRequest customerClientRequest = CustomerClientRequest.template(request.getClientNo());
        return Mono.deferContextual(
            contextView ->
            {
                //transfer cai requestID
                return customerClient.getCustomerMono(customerClientRequest);
            }
        ).flatMap(
                res ->
                {
                    CustomerResponseDTO customerResponseDTO =
                            CustomerResponseDTO.fromCustomerClientResponse(res.getData());

                    BranchClientRequest branchClientRequest = new BranchClientRequest("DEFAULT BRANCH");
                    CountryClientRequest countryClientRequest = new CountryClientRequest("DEFAULT COUNTRY");
                    Mono<BaseResponse<BranchClientResponse>> branchResponsesMono =
                            validateCustomerBranch(res.getData()).then(
                                    branchClient.getBranchMono(branchClientRequest))
                                    .defaultIfEmpty(null);
                    // TODO: handle case when branch is null
                    Mono<BaseResponse<CountryClientResponse>> countryResponseMono =
                            validateCustomerCountry(res.getData())
                                    .then(countryClient.getCountries(countryClientRequest));
                    
                    Mono<Tuple2<BaseResponse<BranchClientResponse>, BaseResponse<CountryClientResponse>>> monoBranchCountry =
                            Mono.zip(branchResponsesMono,countryResponseMono);
                            //PROMISE ALL
                    monoBranchCountry.subscribe(
                            result -> {
                                BaseResponse<BranchClientResponse> branchResponse = result.getT1();
                                BaseResponse<CountryClientResponse> countryResponse = result.getT2();
                                customerResponseDTO.setBranchName(branchResponse.getData().getBranchName());
                                customerResponseDTO.setCountryDesc(countryResponse.getData().getCountryDesc());
                            }
                    );
                    BaseResponse<CustomerResponseDTO> baseResponse = new BaseResponse<>();
                    baseResponse.setData(customerResponseDTO);
                    return Mono.just(baseResponse);
                }
        );
    }

    private Mono<Void> validateCustomerBranch(CustomerClientResponse responseDTO) {
        if(responseDTO.getCtrlBranch()==null){
            return Mono.error(new RuntimeException());
        }
        return Mono.empty();
    }
    private Mono<Void> validateCustomerCountry(CustomerClientResponse responseDTO) {
        if(responseDTO.getCountryCitizen()==null){
            return Mono.error(new RuntimeException());
        }
        return Mono.empty();
    }

}
