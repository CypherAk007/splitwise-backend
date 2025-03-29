package com.backend.splitwise.commandExecutor;

import org.springframework.stereotype.Component;

@Component
public class ExitCommand implements CommandExe{
    @Override
    public boolean matches(String input) {
        return input.equals("exit");
    }

    @Override
    public void execute(String input) {
        System.exit(0);
    }
}
