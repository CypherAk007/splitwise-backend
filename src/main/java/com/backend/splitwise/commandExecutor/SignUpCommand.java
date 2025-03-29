package com.backend.splitwise.commandExecutor;

import com.backend.splitwise.controllers.UserController;
import com.backend.splitwise.dtos.authentication.SignupRequestDTO;
import com.backend.splitwise.dtos.authentication.SignupResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class SignUpCommand implements CommandExe{
    private final UserController userController;

    public SignUpCommand(UserController userController) {
        this.userController = userController;
    }


//    signup u name phone pwd
    @Override
    public boolean matches(String input) {
        String[] words  = input.split(" ");
        return words.length>0 && words[0].equals("signup");
    }

    @Override
    public void execute(String input) {
        String[] words  = input.split(" ");
        SignupRequestDTO signupRequestDTO = new SignupRequestDTO();
        signupRequestDTO.setName(words[1]);
        signupRequestDTO.setPhone(words[2]);
        signupRequestDTO.setPassword(words[3]);
        SignupResponseDTO responseDTO = userController.signup(signupRequestDTO);
        System.out.println("Response SignUp User : "+responseDTO);
    }
}
