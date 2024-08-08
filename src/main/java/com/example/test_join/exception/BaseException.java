package com.example.test_join.exception;

import com.example.test_join.share.enums.ResponseEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends RuntimeException {
  protected final ResponseEnum errorInfo;

  public BaseException(String message) {
    super(message);
    errorInfo = ResponseEnum.INTERNAL_ERROR;
  }

  public BaseException(String message, ResponseEnum errorInfo) {
    super(message);
    this.errorInfo = errorInfo;
  }

  public BaseException(ResponseEnum errorInfo) {
    super(errorInfo.getMessage());
    this.errorInfo = errorInfo;
  }
}
