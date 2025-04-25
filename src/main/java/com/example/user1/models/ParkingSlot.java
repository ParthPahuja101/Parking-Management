package com.example.user1.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Table("parking_slots")
public class  ParkingSlot {
    public ParkingSlot(int id,VehicleType type, Boolean isOccupied) {
        this.id = id;
        this.type = type;
        this.isOccupied = isOccupied;
    }

    public void setOccupied(Boolean occupied) {
        isOccupied = occupied;
    }

    @Id
    public int id;
    public VehicleType type;
    public Boolean isOccupied;
}

enum VehicleType {
    CAR,
    BIKE
}
