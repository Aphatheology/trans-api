package com.aphatheology.transactionrestapi.exceptions;

public class BadRequestException extends RuntimeException {
    private final String status;
    private final String transactionId;

    public BadRequestException(String message, String transactionId, String status) {
        super(message);
        this.status = status;
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getStatus() {
        return status;
    }
}
