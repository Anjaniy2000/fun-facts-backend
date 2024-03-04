package com.anjaniy.funfactsbackend.models.dto;

public class JsonResponse {
    private Object responseBody;
    private Boolean responseStatus;
    private String responseMessage;

    public JsonResponse() {
    }

    public JsonResponse(Object responseBody, Boolean responseStatus, String responseMessage) {
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

    public Boolean getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(Boolean responseStatus) {
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
