package com.example.user1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> productRoutes(UserHandler handler) {
        return RouterFunctions
                .route(GET("/users"), handler::getAllUsers).andRoute(POST("/users"), handler::createUser);
    }

    @Bean
    public RouterFunction<ServerResponse> productByIdRoutes(UserHandler handler) {
        return RouterFunctions
                .route(GET("/users/{id}"), handler::getUsersById);
    }

    @Bean
    public RouterFunction<ServerResponse> parkingRoutes(ParkingHandler handler) {
        return RouterFunctions
                .route(POST("/parking-slots"), handler::createParkingSlots)
                        .andRoute(GET("/parking-slots"), handler::findFreeParkingSlot)
                        .andRoute(POST("/checkin"), handler::checkin)
                        .andRoute(POST("/checkout"), handler::checkout);

    }

}