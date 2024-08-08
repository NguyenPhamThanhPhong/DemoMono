package com.example.test_join.service.restserver.impl;

import org.springframework.stereotype.Service;

import com.example.test_join.dto.client.request.AccountClientRequest;
import com.example.test_join.dto.client.request.CustomerClientRequest;
import com.example.test_join.dto.client.response.CustomerClientResponse;
import com.example.test_join.dto.server.request.BaseRequest;
import com.example.test_join.dto.server.request.GetAccountRequest;
import com.example.test_join.dto.server.response.AccountResponseDTO;
import com.example.test_join.dto.server.response.BaseResponse;
import com.example.test_join.exception.BadRequestException;
import com.example.test_join.exception.IntegrationException;
import com.example.test_join.exception.ResourceNotFoundException;
import com.example.test_join.service.restclient.AccountClient;
import com.example.test_join.service.restclient.CustomerClient;
import com.example.test_join.service.restserver.BaseService;
import com.example.test_join.service.restserver.IAccountService;
import java.util.Objects;

import static com.example.test_join.share.enums.ResponseEnum.DATA_NOT_FOUND;
import static com.example.test_join.share.enums.ResponseEnum.DATA_SUCCESS;

import reactor.core.publisher.Mono;

@Service
public class AccountService extends BaseService implements IAccountService {
    private AccountClient accountClient;
    private CustomerClient customerClient;

    public AccountService(AccountClient accountClient, CustomerClient customerClient) {
        this.accountClient = accountClient;
        this.customerClient = customerClient;
    }

    @Override
    public Mono<BaseResponse<AccountResponseDTO>> getAccountInfo(BaseRequest<GetAccountRequest> baseRequest) {
        GetAccountRequest request = baseRequest.getData();
        return validateRequest(request).then(
                Mono.defer(() -> {
                    CustomerClientRequest customerClientRequest = CustomerClientRequest.template(request.getClientNo());
                    return customerClient.getCientNo(customerClientRequest)
                            .flatMap(this::validateClientNo)
                            .flatMap(this::validateBranch)
                            .flatMap(baseCustomerClientResponse -> {
                                BaseRequest<AccountClientRequest> baseAccRequest = BaseRequest
                                        .<AccountClientRequest>builder()
                                        .requestId(baseRequest.getRequestId())
                                        .requestDate(baseRequest.getRequestDate())
                                        .requestUser(baseRequest.getRequestUser())
                                        .requestChannel(baseRequest.getRequestChannel())
                                        .data(AccountClientRequest.template(
                                                baseCustomerClientResponse.getData().getClientNo(),
                                                baseCustomerClientResponse.getData().getCtrlBranch()))
                                        .build();
                                return accountClient.getAccountMono(baseAccRequest);
                            }).flatMap(
                                    baseAccountClientRes -> {
                                        BaseResponse<AccountResponseDTO> baseResponse = BaseResponse.baseResponse(
                                                baseAccountClientRes.getRequestId(), DATA_SUCCESS);
                                        baseResponse.setData(
                                                new AccountResponseDTO(baseAccountClientRes.getData().getAccountNo(),
                                                        baseAccountClientRes.getData().getBranch()));
                                        return Mono.just(baseResponse);
                                    });
                }));
    }

    private Mono<Void> validateRequest(GetAccountRequest request) {
        if (request.getClientNo() == null || request.getClientNo().isEmpty()) {
            return Mono.error(new BadRequestException("Client number is required"));
        }
        return Mono.empty();
    }

    private Mono<BaseResponse<CustomerClientResponse>> validateClientNo(
            BaseResponse<CustomerClientResponse> baseResponse) {
        if (baseResponse == null) {
            return Mono.error(new IntegrationException("Failed to get client number"));
        } else if (baseResponse.getData() == null) {
            return Mono.error(new BadRequestException("Customer not found"));
        }
        BaseResponse<CustomerClientResponse> baseResponseCustomerClient = BaseResponse
                .baseResponse(baseResponse.getRequestId(), DATA_SUCCESS);
        baseResponseCustomerClient.setData(baseResponse.getData());
        return Mono.just(baseResponseCustomerClient);
    }

    private Mono<BaseResponse<AccountClientRequest>> validateBranch(BaseResponse<CustomerClientResponse> baseResponse) {
        if (baseResponse == null) {
            return Mono.error(new IntegrationException("Failed to get account info"));
        } else if (baseResponse.getData() == null ||
                !baseResponse.getMessage().equals(DATA_SUCCESS.getMessage()) ||
                !Objects.equals(baseResponse.getResponseCode(), DATA_SUCCESS.getCode())) {
            return Mono.error(new ResourceNotFoundException(DATA_NOT_FOUND.getMessage()));
        }
        BaseResponse<AccountClientRequest> baseResponseAccountClient = BaseResponse.baseResponse(baseResponse.getRequestId(), DATA_SUCCESS);
        AccountClientRequest accountClientRequest = AccountClientRequest.builder()
                .clientNo(baseResponse.getData().getClientNo())
                .ctrlBranch(baseResponse.getData().getCtrlBranch())
                .build();
        baseResponseAccountClient.setData(accountClientRequest);
        baseResponseAccountClient.setData(accountClientRequest);
        return Mono.just(baseResponseAccountClient);
    }
}
