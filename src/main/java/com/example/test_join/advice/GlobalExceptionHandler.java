package com.example.test_join.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.test_join.dto.server.response.BaseResponse;
import com.example.test_join.exception.BadRequestException;
import com.example.test_join.exception.BizException;
import com.example.test_join.exception.IntegrationException;
import com.example.test_join.exception.ResourceNotFoundException;
import com.example.test_join.share.constant.ApplicationConstants;
import com.example.test_join.share.enums.ResponseEnum;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
  private static final String COMMON_ERROR_MESSAGE_TEMPLATE = "Got error: [%s], with Message: [%s]";

  private String buildErrorMessage(Exception ex) {
    return String.format(COMMON_ERROR_MESSAGE_TEMPLATE, ex.getClass().getName(), ex.getMessage());
  }

  private Mono<BaseResponse<String>> buildCommonResponse(ResponseEnum errorInfo, String errorData) {
    BaseResponse<String> response = new BaseResponse<>();
    response.setResponseCode(errorInfo.getResponseCode());
    response.setMessage(errorInfo.getMessage());
    response.setData(errorData);
    return Mono.deferContextual(ctx -> {
      response.setRequestId(getReqId(ctx));
      return Mono.just(response);
    });
  }

  private String getReqId(reactor.util.context.ContextView ctx) {
    return ctx.getOrDefault(ApplicationConstants.INTERNAL_REQUEST_TRACE_ID, "");
  }

  @ExceptionHandler(value = { Exception.class })
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Mono<BaseResponse<String>> handleUnCaughtException(Exception ex) {
    log.error(buildErrorMessage(ex));
    return buildCommonResponse(ResponseEnum.INTERNAL_ERROR, ex.getMessage());
  }

  @ExceptionHandler(BadRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Mono<BaseResponse<String>> handleBadRequestException(BadRequestException ex) {
    log.error(buildErrorMessage(ex));
    return buildCommonResponse(ex.getErrorInfo(), ex.getMessage());
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Mono<BaseResponse<String>> handleNotFoundException(ResourceNotFoundException ex) {
    log.error(buildErrorMessage(ex));
    return buildCommonResponse(ex.getErrorInfo(), ex.getMessage());
  }

  @ExceptionHandler({ IntegrationException.class })
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Mono<BaseResponse<String>> integrationException(IntegrationException ex) {
    log.error(buildErrorMessage(ex));
    return buildCommonResponse(ex.getErrorInfo(), ex.getMessage());
  }

  @ExceptionHandler({ BizException.class })
  @ResponseStatus(HttpStatus.OK)
  public Mono<BaseResponse<String>> bizException(BizException ex) {
    log.error(buildErrorMessage(ex));
    return buildCommonResponse(ex.getErrorInfo(), ex.getMessage());
  }

  @ExceptionHandler({ MethodArgumentNotValidException.class })
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Mono<BaseResponse<String>> invalidInputException(MethodArgumentNotValidException ex) {
    log.error(buildErrorMessage(ex));
    StringBuilder sb = new StringBuilder();
    ex.getFieldErrors().forEach(fieldError -> {
      sb.append(" ** ErrorField=").append(fieldError.getField()).append(", ");
      sb.append("ErrorCode=").append(fieldError.getCode()).append(", ");
      sb.append("ErrorMessage=").append(fieldError.getDefaultMessage()).append(".");
    });
    return buildCommonResponse(ResponseEnum.BAD_REQUEST, sb.toString());
  }

}