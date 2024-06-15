package com.example.cli;

import com.example.exceptions.IncorrectInput;

import java.sql.SQLException;

public interface Command{
    void execute() throws IncorrectInput, SQLException;
    String getCommandName();
}
