package com.example.test_join.exception;

import com.example.test_join.share.enums.ResponseEnum;

public class IntegrationException extends BaseException {
    public IntegrationException(String message) {
      super(message);
    }
  
    public IntegrationException(String message, ResponseEnum errorInfo) {
      super(message, errorInfo);
    }
  
    public IntegrationException(ResponseEnum errorInfo) {
      super(errorInfo);
    }
  }
  