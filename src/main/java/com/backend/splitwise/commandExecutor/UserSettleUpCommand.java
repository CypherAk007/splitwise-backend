package com.backend.splitwise.commandExecutor;

import com.backend.splitwise.controllers.ExpenseController;
import com.backend.splitwise.controllers.GroupController;
import com.backend.splitwise.controllers.SettleUpController;
import com.backend.splitwise.controllers.UserController;
import com.backend.splitwise.dtos.settleup.SettleUpUserRequestDTO;
import com.backend.splitwise.dtos.settleup.SettleUpUserResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class UserSettleUpCommand implements CommandExe{
    private final SettleUpController settleUpController;

    public UserSettleUpCommand(SettleUpController settleUpController) {
        this.settleUpController = settleUpController;
    }

    @Override
    public boolean matches(String input) {
        String[] words = input.split(" ");
        return words.length>1 && words[1].equals("settleUpUser");
    }

    @Override
    public void execute(String input) {
        String[] words = input.split(" ");
        Long userId = Long.parseLong(words[0]);
        SettleUpUserRequestDTO settleUpUserRequestDTO = new SettleUpUserRequestDTO();
        settleUpUserRequestDTO.setUserId(userId);
        SettleUpUserResponseDTO settleUpUserResponseDTO = settleUpController.settleUpUser(settleUpUserRequestDTO);
        System.out.println("Response Settle Up User : "+settleUpUserResponseDTO);
    }
}
