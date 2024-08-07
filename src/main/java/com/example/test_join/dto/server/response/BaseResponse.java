package com.example.test_join.dto.server.response;


import java.util.Date;

import com.example.test_join.share.enums.ResponseEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
  @JsonProperty("response_code")
  private String responseCode;
  @JsonProperty("request_id")
  private String requestId;
  @JsonProperty("message")
  private String message;
  @JsonProperty("times_tamp")
  private Date timestamp = new Date();
  @JsonProperty("data")
  private T data;

  public static <O> BaseResponse<O> baseResponse(String requestId, ResponseEnum responseEnum) {
    BaseResponse<O> res = new BaseResponse<>();
    res.setMessage(responseEnum.getMessage());
    res.setResponseCode(responseEnum.getResponseCode());
    res.setRequestId(requestId);
    return res;
  }
}
