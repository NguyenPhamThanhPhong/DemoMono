package com.example.test_join.dto.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BranchClientResponse {
    @JsonProperty("branch_name")
    private String branchName;
    public static BranchClientResponse Default() {
        BranchClientResponse branchInfoResponse = new BranchClientResponse();
        branchInfoResponse.setBranchName("Default Branch");
        return branchInfoResponse;
    }
}
