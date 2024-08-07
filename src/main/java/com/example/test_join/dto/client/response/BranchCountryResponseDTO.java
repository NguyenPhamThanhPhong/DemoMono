package com.example.test_join.dto.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BranchCountryResponseDTO {
    @JsonProperty("branch_name")
    private String branchName;
    @JsonProperty("country_desc")
    private String countryDesc;
}
