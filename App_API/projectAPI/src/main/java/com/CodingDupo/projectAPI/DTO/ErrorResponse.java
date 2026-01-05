package com.CodingDupo.projectAPI.DTO;

public class ErrorResponse {
    private int statusCode;
    private String message;
    public ErrorResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
    public String getMessage() {
        return message;
    }public Integer getStatusCode() {
        return statusCode;
    }
}
