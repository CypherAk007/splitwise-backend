package com.backend.splitwise.commandExecutor;

import com.backend.splitwise.controllers.UserController;
import com.backend.splitwise.dtos.authentication.LoginRequestDTO;
import com.backend.splitwise.dtos.authentication.LoginResponseDTO;
import com.backend.splitwise.dtos.authentication.SignupRequestDTO;
import com.backend.splitwise.dtos.authentication.SignupResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class LoginCommand implements CommandExe{
    private final UserController userController;

    public LoginCommand(UserController userController) {
        this.userController = userController;
    }


//    signup u name phone pwd
    @Override
    public boolean matches(String input) {
        String[] words  = input.split(" ");
        return words.length>0 && words[0].equals("login");
    }

    @Override
    public void execute(String input) {
        System.out.println("Command Login:");
        String[] words  = input.split(" ");
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setPhone(words[1]);
        loginRequestDTO.setPassword(words[2]);
        LoginResponseDTO responseDTO = userController.login(loginRequestDTO);
        System.out.println("Response Login User : "+responseDTO);
    }
}
