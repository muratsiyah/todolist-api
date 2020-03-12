package com.murat.siyah.todolistapi.contract.exception.model;

import java.util.List;

public class ErrorDto {

    private int statusCode;

    private List<ErrorMessage> messages;

    public ErrorDto() {
    }

    public ErrorDto(int statusCode, List<ErrorMessage> messages) {
        this.statusCode = statusCode;
        this.messages = messages;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public List<ErrorMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ErrorMessage> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "ErrorDto{" +
                "statusCode=" + statusCode +
                ", messages=" + messages +
                '}';
    }

}
