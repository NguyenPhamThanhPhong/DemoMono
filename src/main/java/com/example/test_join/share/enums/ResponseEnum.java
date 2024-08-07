package com.example.test_join.share.enums;

import lombok.Getter;

@Getter
public enum ResponseEnum {
  SUCCESS("0000", "Success", ""),
  BAD_REQUEST("0400", "Bad request", "Invalid request"),
  INTERNAL_ERROR("0500", "Unknown error", "Unknown error"),
  BIZ_ERROR("0001", "Business error", "Business error"),
  RESOURCE_NOT_FOUND("0404", "Resource not found", "Resource not found"),
  DATA_SUCCESS("1930","success","Getting data success."),
  DATA_NOT_FOUND("1931", "Decline", "No information found");

  private final String responseCode;
  private final String message;
  private final String description;

  private ResponseEnum(final String responseCode, final String message, final String description) {
    this.responseCode = responseCode;
    this.message = message;
    this.description = description;

  }

  public String getCode() {
    return responseCode;
  }

  public String getDescription() {
    return description;
  }

}
