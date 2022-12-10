package com.demus.model.httpResponse;

public class ControllerResponse {
    private boolean isSuccess;
    private String StatusCode;
    private String message;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(String statusCode) {
        StatusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void buildControllerError() {
        this.message = "Internal Controller Error";
        this.isSuccess = false;
        this.StatusCode = "101";
    }

    public void buildServiceError() {
        this.message = "Internal Service Error";
        this.isSuccess = false;
        this.StatusCode = "100";
    }
}
