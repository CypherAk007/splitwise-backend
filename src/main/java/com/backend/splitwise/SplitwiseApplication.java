package com.backend.splitwise;

import com.backend.splitwise.controllers.UserController;
import com.backend.splitwise.dtos.authentication.LoginRequestDTO;
import com.backend.splitwise.dtos.authentication.LoginResponseDTO;
import com.backend.splitwise.dtos.authentication.SignupRequestDTO;
import com.backend.splitwise.dtos.authentication.SignupResponseDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SplitwiseApplication implements CommandLineRunner {
	private final UserController userController;

    public SplitwiseApplication(UserController userController) {
        this.userController = userController;
    }

    public static void main(String[] args) {
		SpringApplication.run(SplitwiseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		SignupRequestDTO signupRequestDTO = new SignupRequestDTO();
		signupRequestDTO.setName("Abhishek");
		signupRequestDTO.setPassword("1234567890");
		signupRequestDTO.setPhone("1234567890");

		SignupResponseDTO signupResponseDTO = userController.signup(signupRequestDTO);
		System.out.println(signupResponseDTO);


		LoginRequestDTO loginRequestDTO  = new LoginRequestDTO();
		loginRequestDTO.setPhone("1234567890");
		loginRequestDTO.setPassword("1234567890");

		LoginResponseDTO loginResponseDTO = userController.login(loginRequestDTO);

		System.out.println(loginResponseDTO);
	}
}
