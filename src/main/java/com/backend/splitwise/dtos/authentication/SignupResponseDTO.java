package com.backend.splitwise.dtos.authentication;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignupResponseDTO {
    public Long userId;
    public ResponseStatus status;
    public String message;
}
