package com.example.user1.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("parking_records")
public class ParkingRecord {
    public ParkingRecord(int id,String vehicleNumber, int parkingSlotId, String checkinTime, String checkoutTime){
        this.id = id;
        this.vehicleNumber = vehicleNumber;
        this.parkingSlotId = parkingSlotId;
        this.checkinTime = checkinTime;
        this.checkoutTime = checkoutTime;
    }

    public void setCheckoutTime(String checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    @Id
    public int id;
    public String vehicleNumber;
    public int parkingSlotId;
    public String checkinTime;
    public String checkoutTime;
}
