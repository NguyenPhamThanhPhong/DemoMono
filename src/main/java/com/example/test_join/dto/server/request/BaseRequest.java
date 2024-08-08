package com.example.test_join.dto.server.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseRequest<T> {

    @JsonProperty("request_id")
    private String requestId;

    @JsonProperty("request_date")
    private String requestDate;

    @JsonProperty("request_user")
    private String requestUser;

    @JsonProperty("request_channel")
    private String requestChannel;

    @JsonProperty("data")
    private T data;
}
