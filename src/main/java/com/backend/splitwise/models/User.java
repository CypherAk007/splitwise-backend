package com.backend.splitwise.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User extends BaseModel{
    private String name;
    @Column(unique = true, nullable = false)
    private String phone;
    private String password;

}
