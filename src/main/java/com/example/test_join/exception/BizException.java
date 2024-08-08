package com.example.test_join.exception;

import com.example.test_join.share.enums.ResponseEnum;

public class BizException extends BaseException {
    public BizException(String message) {
      super(message, ResponseEnum.BIZ_ERROR);
    }
  
    public BizException(String message, ResponseEnum errorInfo) {
      super(message, errorInfo);
    }
  
    public BizException(ResponseEnum errorInfo) {
      super(errorInfo);
    }
  
  }
  