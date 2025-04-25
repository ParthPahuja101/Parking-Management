package com.example.user1.exceptions;

public class CustomErrorResponse {
    private int status;
    private String error;
    private String timestamp;
    private String path;

    public CustomErrorResponse(int status, String error, String timestamp, String path) {
        this.status = status;
        this.error = error;
        this.timestamp = timestamp;
        this.path = path;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getPath() {
        return path;
    }
}