package com.example.test_join.dto.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CustomerClientRequest {

    @JsonProperty("client_no")
    private String clientNo;
    @JsonProperty("client_type_1")
    private String contactType1;
    @JsonProperty("client_type_2")
    private String contactType2;
    @JsonProperty("client_type_3")
    private String contactType3;
    public static CustomerClientRequest template (String clientNo) {
        CustomerClientRequest request = new CustomerClientRequest();
        request.setClientNo(clientNo);
        request.setContactType1("PO1");
        request.setContactType2("PO2");
        request.setContactType3("PO3");
        return request;
    }

}
