package com.demus.model.controller;

import com.demus.model.service.ServiceResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ControllerResponse {
    private boolean isSuccess;
    private Integer StatusCode;
    private String message;

    public void constructResponse(boolean isSuccess, Integer statusCode, String message) {
        this.isSuccess = isSuccess;
        StatusCode = statusCode;
        this.message = message;
    }

    public void constructResponse(ServiceResponse serviceResponse) {
        this.isSuccess = serviceResponse.isSuccess();
        StatusCode = serviceResponse.getStatusCode();
        this.message = "Service message: " + serviceResponse.getMessage();
    }

    public void constructControllerError(Exception exception) {
        this.message = "Internal Controller Error: " + exception.getMessage().toString();
        this.isSuccess = false;
        this.StatusCode = 500;
    }
}
