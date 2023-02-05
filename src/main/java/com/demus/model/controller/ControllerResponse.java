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

    public void buildResponse(boolean isSuccess, Integer statusCode, String message) {
        this.isSuccess = isSuccess;
        StatusCode = statusCode;
        this.message = message;
    }

    public void buildResponse(ServiceResponse serviceResponse) {
        this.isSuccess = serviceResponse.isSuccess();
        StatusCode = serviceResponse.getStatusCode();
        this.message = serviceResponse.getMessage();
    }

    public void buildControllerError(Exception exception) {
        this.message = "Internal Controller Error: " + exception.getMessage().toString();
        this.isSuccess = false;
        this.StatusCode = 500;
    }
}
