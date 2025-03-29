package com.backend.splitwise.commandExecutor;

public interface CommandExe {
    boolean matches(String input);

    void execute(String input);
}
