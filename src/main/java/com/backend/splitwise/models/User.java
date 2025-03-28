package com.backend.splitwise.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User extends BaseModel{
    private String name;
    @Column(unique = true, nullable = false)
    private String phone;
    private String password;

}
