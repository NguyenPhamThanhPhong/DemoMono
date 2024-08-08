package com.example.test_join.dto.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AccountClientResponse {
    @JsonProperty("acct_no")
    private String accountNo;
    @JsonProperty("branch")
    private String branch;

    public static AccountClientResponse Default() {
        AccountClientResponse response = new AccountClientResponse();
        response.setAccountNo("DEFAULT ACCOUNT");
        response.setBranch("DEFAULT BRANCH");
        return response;
    }
}
