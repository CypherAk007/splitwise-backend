package com.backend.splitwise.commandExecutor;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandExecutor {
    private List<CommandExe> commands;

    public CommandExecutor(UserSettleUpCommand userSettleUpCommand,ExitCommand exitCommand,SignUpCommand signUpCommand,LoginCommand loginCommand) {
        this.commands = new ArrayList<>();
//        During application start up time how to add command
        addCommand(userSettleUpCommand);
        addCommand(exitCommand);
        addCommand(signUpCommand);
        addCommand(loginCommand);
    }

    public void addCommand(CommandExe command){
        commands.add(command);
    }

    public void removeCommand(CommandExe command){
        commands.remove(command);
    }

    public void execute(String input){
        commands.stream()
                .filter(command -> command.matches(input))
                .findFirst()
                .ifPresent(command -> command.execute(input));
    }
}