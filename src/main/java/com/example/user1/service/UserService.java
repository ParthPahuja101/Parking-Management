package com.example.user1.service;

import com.example.user1.Repository.UserRepository;
import com.example.user1.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public Flux<User> getUserFlux() {
        return userRepository.findAll();
    }



    @Transactional
    public Mono<User> createUser(User user) {
        return userRepository.save(user).flatMap(
                this::chargeUser
        );
    }

    public Mono<User> chargeUser(User user) {
//        System.out.println("Charging user: " + user.name);
        return Mono.error(new RuntimeException("User not found!")); //Mono.just(user);
    }

    public Mono<User> getUserById(int id) {
        return userRepository.findById(id);
    }

}
