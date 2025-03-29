package com.backend.splitwise;

import com.backend.splitwise.controllers.ExpenseController;
import com.backend.splitwise.controllers.GroupController;
import com.backend.splitwise.controllers.UserController;
import com.backend.splitwise.dtos.authentication.LoginRequestDTO;
import com.backend.splitwise.dtos.authentication.LoginResponseDTO;
import com.backend.splitwise.dtos.authentication.SignupRequestDTO;
import com.backend.splitwise.dtos.authentication.SignupResponseDTO;
import com.backend.splitwise.dtos.expenses.CreateExpenseRequestDTO;
import com.backend.splitwise.dtos.expenses.CreateExpenseResponseDTO;
import com.backend.splitwise.dtos.group.CreateGroupRequestDTO;
import com.backend.splitwise.dtos.group.CreateGroupResponseDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableJpaAuditing
public class SplitwiseApplication implements CommandLineRunner {
	private final UserController userController;
	private final ExpenseController expenseController;
	private final GroupController groupController;

    public SplitwiseApplication(UserController userController, ExpenseController expenseController, GroupController groupController) {
        this.userController = userController;
        this.expenseController = expenseController;
        this.groupController = groupController;
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

		SignupRequestDTO signupRequestDTO2 = new SignupRequestDTO();
		signupRequestDTO2.setName("AK");
		signupRequestDTO2.setPassword("0987654771");
		signupRequestDTO2.setPhone("1234569990");

		SignupResponseDTO signupResponseDTO2 = userController.signup(signupRequestDTO2);
		System.out.println(signupResponseDTO2);

		LoginRequestDTO loginRequestDTO  = new LoginRequestDTO();
		loginRequestDTO.setPhone("1234567890");
		loginRequestDTO.setPassword("1234567890");

		LoginResponseDTO loginResponseDTO = userController.login(loginRequestDTO);

		System.out.println(loginResponseDTO);

		CreateGroupRequestDTO createGroupRequestDTO = new CreateGroupRequestDTO();
		createGroupRequestDTO.setName("Goa Group");
		createGroupRequestDTO.setExpenses(new ArrayList<>());
		createGroupRequestDTO.setMembers(new ArrayList<>(Arrays.asList(1L,2L)));
		createGroupRequestDTO.setCreateById(1L);
		CreateGroupResponseDTO createGroupResponseDTO  = groupController.createGroup(createGroupRequestDTO);
		System.out.println(createGroupResponseDTO);


		CreateExpenseRequestDTO createExpenseRequestDTO = new CreateExpenseRequestDTO();
		createExpenseRequestDTO.setAmount(100);
		createExpenseRequestDTO.setDescription("Tender Coconut");
		createExpenseRequestDTO.setGroupId(1L);
		Map<Long,Double> payers = new HashMap<>();
		payers.put(1L,100.0);
		Map<Long,Double> owers = new HashMap<>();
		payers.put(1L,50.0);
		payers.put(2L,50.0);
		createExpenseRequestDTO.setPayers(payers);
		createExpenseRequestDTO.setOwers(owers);
		createExpenseRequestDTO.setCreatedBy(1L);

		CreateExpenseResponseDTO createExpenseResponseDTO = expenseController.createExpense(createExpenseRequestDTO);
		System.out.println(createExpenseResponseDTO);

		CreateExpenseRequestDTO createExpenseRequestDTO1 = new CreateExpenseRequestDTO();
		createExpenseRequestDTO1.setAmount(100);
		createExpenseRequestDTO1.setDescription("Tender Coconut");
		createExpenseRequestDTO1.setGroupId(1L);
		Map<Long,Double> payers1 = new HashMap<>();
		payers.put(1L,100.0);
		Map<Long,Double> owers1 = new HashMap<>();
		payers1.put(1L,50.0);
		payers1.put(2L,50.0);
		createExpenseRequestDTO1.setPayers(payers1);
		createExpenseRequestDTO1.setOwers(owers1);
		createExpenseRequestDTO1.setCreatedBy(1L);

		CreateExpenseResponseDTO createExpenseResponseDTO1 = expenseController.createExpense(createExpenseRequestDTO1);
		System.out.println(createExpenseResponseDTO1);

	}
}
