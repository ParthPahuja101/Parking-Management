package com.example.user1.Repository;

import com.example.user1.models.ParkingSlot;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ParkingSlotRepository extends ReactiveCrudRepository<ParkingSlot, Integer> {

    Flux<ParkingSlot> findByIsOccupiedFalseAndType(String type);
}
