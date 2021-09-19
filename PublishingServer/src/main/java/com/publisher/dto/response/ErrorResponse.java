package com.publisher.dto.response;

public class ErrorResponse {

    private final String code;
    private final String message;

    public ErrorResponse(String code ,String msg){
        this.code = code;
        this.message = msg;
    }


    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
