package com.example.test_join.exception;

import com.example.test_join.share.enums.ResponseEnum;

public class BadRequestException extends BaseException {
    public BadRequestException(String message) {
      super(message, ResponseEnum.BAD_REQUEST);
    }
  
    public BadRequestException(String message, ResponseEnum errorInfo) {
      super(message, errorInfo);
    }
  
    public BadRequestException(ResponseEnum errorInfo) {
      super(errorInfo);
    }
  }
  