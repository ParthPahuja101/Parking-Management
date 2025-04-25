package com.example.user1.exceptions;

public class VehicleAlreadyCheckedOutException  extends RuntimeException{
    public VehicleAlreadyCheckedOutException(String message) {
        super(message);
    }
}