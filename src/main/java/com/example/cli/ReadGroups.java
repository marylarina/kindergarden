package com.example.cli;

import com.example.entity.Group;
import com.example.exceptions.IncorrectInput;
import com.example.repository.GroupRepositoryImpl;

import java.sql.SQLException;

public class ReadGroups implements Command{
    @Override
    public void execute() throws IncorrectInput, SQLException {
        int count = 0;
        for (Group group: GroupRepositoryImpl.getInstance().getAll()){
            count++;
            System.out.println(
                    count + ". " +
                    group.getId() + " "
                    + group.get_name());
        }
    }

    @Override
    public String getCommandName() {
        return "Read list of groups";
    }
}
