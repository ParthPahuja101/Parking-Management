package com.example.user1.Repository;

import com.example.user1.models.ParkingRecord;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ParkingRecordRepository extends ReactiveCrudRepository<ParkingRecord, Integer> {
    Flux<ParkingRecord> findByVehicleNumber(String vehicleNumber);
}
