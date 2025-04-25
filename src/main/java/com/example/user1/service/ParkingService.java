package com.example.user1.service;

import com.example.user1.Repository.ParkingRecordRepository;
import com.example.user1.Repository.ParkingSlotRepository;
import com.example.user1.exceptions.NoSlotAvailableException;
import com.example.user1.exceptions.SlotOccupiedException;
import com.example.user1.exceptions.VehicleAlreadyCheckedOutException;
import com.example.user1.models.ParkingRecord;
import com.example.user1.models.ParkingSlot;
import com.example.user1.models.User;
import com.example.user1.request.CheckoutVehicleRequest;
import com.example.user1.request.ParkVehicleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class ParkingService {

    @Autowired
    ParkingSlotRepository parkingSlotRepository;

    @Autowired
    ParkingRecordRepository parkingRecordRepository;


    public Mono<ParkingSlot> createParkingSlot(ParkingSlot parkingSlot) {
        return parkingSlotRepository.save(parkingSlot);
    }

    public Flux<ParkingSlot> getFreeParkingSlot(String type) {
        return parkingSlotRepository.findByIsOccupiedFalseAndType(type).switchIfEmpty(Flux.error(new NoSlotAvailableException("Oh no! Looks like we have no parking available for type- "+type)));
    }

    @Transactional
    public Mono<ParkingRecord> checkIn(ParkVehicleRequest request){
        return parkingSlotRepository.findById(request.parkingSlotId).flatMap(
                slot->{
                    if(slot.isOccupied)
                        return Mono.error(new SlotOccupiedException("Slot "+request.parkingSlotId+" is already occupied"));
                    else return Mono.just(slot);
                }
        ).flatMap(
                parkingSlot -> {
                    ParkingRecord parkingRecord = new ParkingRecord(0, request.vehicleNumber, parkingSlot.id, LocalDateTime.now().toString(),null);
                    parkingSlot.setOccupied(true);
                    return parkingSlotRepository.save(parkingSlot).flatMap(
                           x->  parkingRecordRepository.save(parkingRecord)
                    );
                }
        );

    }

    @Transactional
    public Mono<ParkingRecord> checkout(CheckoutVehicleRequest request){
        return parkingRecordRepository.findByVehicleNumber(request.vehicleNumber)
                .last()
                .switchIfEmpty(Mono.error(new RuntimeException("Vehicle not found")))
                .flatMap( parkingRecord -> {
                    if(parkingRecord.checkoutTime != null)
                        return Mono.error(new VehicleAlreadyCheckedOutException("Vehicle "+request.vehicleNumber+" is already checked out"));
                    parkingRecord.setCheckoutTime(LocalDateTime.now().toString());
                    return parkingRecordRepository.save(parkingRecord);
                }).flatMap(parkingRecord -> parkingSlotRepository.findById(parkingRecord.parkingSlotId).flatMap(
                        slot->{
                            slot.setOccupied(false);
                            return parkingSlotRepository.save(slot);
                        }
                ).flatMap(
                        x-> Mono.just(parkingRecord)
                ));

    }
}
