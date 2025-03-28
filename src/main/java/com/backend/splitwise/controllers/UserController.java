package com.backend.splitwise.controllers;

import com.backend.splitwise.dtos.authentication.*;
import com.backend.splitwise.models.User;
import com.backend.splitwise.services.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public SignupResponseDTO signup(SignupRequestDTO signupRequestDTO){
        SignupResponseDTO signupResponseDTO = new SignupResponseDTO();
        try{
            User user = userService.signup(signupRequestDTO.getName(),signupRequestDTO.getPhone(),signupRequestDTO.getPassword());
            signupResponseDTO.setUserId(user.getId());
            signupResponseDTO.setStatus(ResponseStatus.SUCCESS);
            signupResponseDTO.setMessage("User Registered Successfully!!");
        }catch (Exception e){
            signupResponseDTO.setStatus(ResponseStatus.FAILURE);
            signupResponseDTO.setMessage(e.getMessage());
        }
        return signupResponseDTO;
    }


    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO){
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        try{
            User user = userService.login(loginRequestDTO.getPhone(),loginRequestDTO.getPassword());
            loginResponseDTO.setUserId(user.getId());
            loginResponseDTO.setStatus(ResponseStatus.SUCCESS);
            loginResponseDTO.setMessage("User Logged In Successfully!!");
        }catch (Exception e){
            loginResponseDTO.setStatus(ResponseStatus.FAILURE);
            loginResponseDTO.setMessage(e.getMessage());
        }
        return loginResponseDTO;
    }

}
