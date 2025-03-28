package com.backend.splitwise.dtos.authentication;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginRequestDTO {
    private String phone;
    private String password;
}
