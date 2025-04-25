package com.example.user1.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
public class User {
    public User(int id,String name, String email){
        this.id = id;
        this.email = email;
        this.name = name;
    }

    @Id
    public int id;
    public String name;
    public String email;
}
