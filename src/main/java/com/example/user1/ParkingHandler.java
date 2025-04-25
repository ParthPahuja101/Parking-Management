package com.example.user1;

import com.example.user1.models.ParkingSlot;
import com.example.user1.models.User;
import com.example.user1.request.CheckoutVehicleRequest;
import com.example.user1.request.ParkVehicleRequest;
import com.example.user1.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ParkingHandler {

    @Autowired
    ParkingService parkingService;

    public Mono<ServerResponse> createParkingSlots(ServerRequest request) {
        return request.bodyToMono(ParkingSlot.class)
                .flatMap(parkingService::createParkingSlot)
                .flatMap(
                        user -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(user)
                );
    }

    public Mono<ServerResponse> findFreeParkingSlot(ServerRequest request) {
        String type = request.headers().firstHeader("type");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(parkingService.getFreeParkingSlot(type), ParkingSlot.class);
    }

    public Mono<ServerResponse> checkin(ServerRequest request) {
        return request.bodyToMono(ParkVehicleRequest.class).flatMap(
                parkingService::checkIn
                ).flatMap(
                        slot -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(slot)
                );

    }

    public Mono<ServerResponse> checkout(ServerRequest request) {
        return request.bodyToMono(CheckoutVehicleRequest.class).flatMap(
                parkingService::checkout
        ).flatMap(
                slot -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(slot)
        );

    }
}
