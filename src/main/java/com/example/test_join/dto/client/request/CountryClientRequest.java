package com.example.test_join.dto.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountryClientRequest {
    @JsonProperty("country")
    private String Country;
}
