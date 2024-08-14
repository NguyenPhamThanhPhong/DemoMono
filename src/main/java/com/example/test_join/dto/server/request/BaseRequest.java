package com.example.test_join.dto.server.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    public static <T> BaseRequest<T> fromBaseRequest(BaseRequest<?> baseRequest) {
        return BaseRequest.<T>builder()
                .requestId(baseRequest.getRequestId())
                .requestDate(baseRequest.getRequestDate())
                .requestUser(baseRequest.getRequestUser())
                .requestChannel(baseRequest.getRequestChannel())
                .build();
    }
}
