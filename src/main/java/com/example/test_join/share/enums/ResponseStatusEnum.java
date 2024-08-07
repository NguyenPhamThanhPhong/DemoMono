package com.example.test_join.share.enums;

public enum ResponseStatusEnum {
    DATA_SUCCESS("success", "1930", 1930, "Getting data success."),
    DATA_NOT_FOUND("Decline", "1931", 1931, "No information found");


    private final String status;
    private final String code;
    private final Integer codeAsInt;
    private final String description;

    ResponseStatusEnum(String status, String code, Integer codeAsInt, String description) {
        this.status = status;
        this.code = code;
        this.codeAsInt = codeAsInt;
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public Integer getCodeAsInt() {
        return codeAsInt;
    }

    public String getDescription() {
        return description;
    }
}

