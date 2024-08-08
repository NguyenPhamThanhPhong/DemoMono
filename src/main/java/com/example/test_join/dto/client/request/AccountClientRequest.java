package com.example.test_join.dto.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountClientRequest {
    @JsonProperty("client_no")
    private String clientNo;
    @JsonProperty("acct_status")
    private String accountStatus;
    @JsonProperty("deposit_type")
    private String depositType;
    @JsonProperty("ccy")
    private String currency;
    @JsonProperty("ctrl_branch")
    private String ctrlBranch;

    public static AccountClientRequest template(String clientNo, String ctrlBranch) {
        return AccountClientRequest.builder()
                .clientNo(clientNo)
                .accountStatus("C")
                .depositType("C")
                .currency("VND")
                .ctrlBranch(ctrlBranch)
                .build();
    }
}
