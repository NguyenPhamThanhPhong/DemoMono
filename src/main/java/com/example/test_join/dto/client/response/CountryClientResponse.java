package com.example.test_join.dto.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CountryClientResponse {
    @JsonProperty("country_desc")
    private String countryDesc;
    public static CountryClientResponse Default() {
        CountryClientResponse countryInfoResponse = new CountryClientResponse();
        countryInfoResponse.setCountryDesc("Default Country");
        return countryInfoResponse;
    }
}
