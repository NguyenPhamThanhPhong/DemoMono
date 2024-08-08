package com.example.test_join.exception;

import com.example.test_join.share.enums.ResponseEnum;

public class ResourceNotFoundException extends BaseException {
    public ResourceNotFoundException(String message) {
      super(message, ResponseEnum.RESOURCE_NOT_FOUND);
    }
  
    public ResourceNotFoundException(String message, ResponseEnum errorInfo) {
      super(message, errorInfo);
    }
  
    public ResourceNotFoundException(ResponseEnum errorInfo) {
      super(errorInfo);
    }
  }
  