package com.aphatheology.transactionrestapi.exceptions;

import lombok.Builder;

import java.util.Date;
import java.util.List;

@Builder
public class ErrorMessage {
    private String message;
    private String status;
    private String transactionId;

    public ErrorMessage(String status, String message, String transactionId) {
        this.status = status;
        this.message = message;
        this.transactionId = transactionId;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getTransactionId() {
        return transactionId;
    }
}
