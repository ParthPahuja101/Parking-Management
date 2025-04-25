package com.example.user1;

import com.example.user1.exceptions.UserNotFoundException;
import com.example.user1.models.User;
import com.example.user1.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {
    private final UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    public Mono<ServerResponse> getAllUsers(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.getUserFlux(), User.class);
    }

    public Mono<ServerResponse> createUser(ServerRequest request) {
        return request.bodyToMono(User.class)
                .flatMap(userService::createUser)
                .flatMap(
                        user -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(user)
                );
    }

    public Mono<ServerResponse> getUsersById(ServerRequest request) {
        String id = request.pathVariable("id");
        return userService.getUserById(Integer.parseInt(id))
                .flatMap(
                user -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(user)
        ).switchIfEmpty(Mono.error(new UserNotFoundException("User not found with id: " + id)));

    }

}
