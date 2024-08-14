package com.example.test_join.dto.server.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetCustomerGlobalRequest {
    @JsonProperty("global_id")
    private String globalId;
}
