package com.example.test_join.dto.server.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetCustomerRequest {
    @JsonProperty("client_no")
    private String clientNo;
}
