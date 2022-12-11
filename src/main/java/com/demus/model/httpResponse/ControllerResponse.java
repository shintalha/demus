package com.demus.model.httpResponse;

public class ControllerResponse {
    private boolean isSuccess;
    private Integer StatusCode;
    private String message;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public Integer getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(Integer statusCode) {
        StatusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void buildResponse(boolean isSuccess, Integer statusCode, String message) {
        this.isSuccess = isSuccess;
        StatusCode = statusCode;
        this.message = message;
    }

    public void buildControllerError() {
        this.message = "Internal Controller Error";
        this.isSuccess = false;
        this.StatusCode = 500;
    }

    public void buildServiceError() {
        this.message = "Internal Service Error";
        this.isSuccess = false;
        this.StatusCode = 500;
    }
}
