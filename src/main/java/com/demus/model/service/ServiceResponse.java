package com.demus.model.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@NoArgsConstructor
public class ServiceResponse {
    private boolean isSuccess;
    private Integer StatusCode;
    private String message;

    public void constructResponse(boolean isSuccess, Integer statusCode, String message) {
        this.isSuccess = isSuccess;
        StatusCode = statusCode;
        this.message = message;
    }

    public void constructSuccessResponse(Integer statusCode) {
        this.isSuccess = true;
        StatusCode = statusCode;
        this.message = "Successfull";
    }

    public void constructErrorResponse() {
        this.message = "Demus Internal Service Error";
        this.isSuccess = false;
        this.StatusCode = 500;
    }

    public void constructErrorResponse(Integer statusCode) {
        this.message = "Error from Spotify";
        this.isSuccess = false;
        this.StatusCode = statusCode;
    }
}
