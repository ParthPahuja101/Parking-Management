package com.example.user1.exceptions;

public class NoSlotAvailableException extends RuntimeException{
    public NoSlotAvailableException(String message) {
        super(message);
    }
}
