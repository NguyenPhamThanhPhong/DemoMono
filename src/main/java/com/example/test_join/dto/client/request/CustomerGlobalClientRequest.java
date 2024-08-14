package com.example.test_join.dto.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public class CustomerGlobalClientRequest {
    @JsonProperty("global_id")
    private String globalId;
    @JsonProperty("client_type_1")
    private String contactType1;
    @JsonProperty("client_type_2")
    private String contactType2;
    @JsonProperty("client_type_3")
    private String contactType3;

    public static CustomerGlobalClientRequest template(String globalId) {
        return CustomerGlobalClientRequest.builder()
                .globalId(globalId)
                .contactType1("PO1")
                .contactType2("PO2")
                .contactType3("PO3")
                .build();
    }
}
