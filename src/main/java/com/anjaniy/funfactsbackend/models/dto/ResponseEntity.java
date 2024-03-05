package com.anjaniy.funfactsbackend.models.dto;

import org.springframework.http.HttpStatus;

public class ResponseEntity {
    private Object responseBody;
    private int responseStatus;
    private String responseMessage;

    public ResponseEntity() {
    }

    public ResponseEntity(Object responseBody, Integer responseStatus, String responseMessage) {
        this.responseBody = responseBody;
        this.responseStatus = responseStatus;
        this.responseMessage = responseMessage;
    }

    public Object getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(Object responseBody) {
        this.responseBody = responseBody;
    }

    public int getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    @Override
    public String toString() {
        return "ResponseEntity{" +
            "responseBody=" + responseBody +
            ", responseStatus=" + responseStatus +
            ", responseMessage='" + responseMessage + '\'' +
            '}';
    }
}
