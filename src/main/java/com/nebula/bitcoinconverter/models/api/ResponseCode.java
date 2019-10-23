package com.nebula.bitcoinconverter.models.api;

public enum ResponseCode {
    A1(511, "API Server Down!"),
    A2(512, "Invalid API Response"),
    A3(513, "Could not connect to API"),
    ;

    private int code;

    private String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
