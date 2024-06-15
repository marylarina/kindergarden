package com.example.cli;

import com.example.entity.Group;
import com.example.exceptions.IncorrectInput;
import com.example.repository.GroupRepositoryImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class AddGroup implements Command{
    @Override
    public void execute() throws IncorrectInput, SQLException {
        Scanner scn = new Scanner(System.in);

        System.out.println("Enter name of a group:");
        String name = scn.nextLine();
        if (name.equals("")){
            throw new IncorrectInput("Incorrect name of a group");
        }
        Group group = new Group(name);
        //group.setId(UUID.randomUUID());
        GroupRepositoryImpl.getInstance().add(group);
    }

    @Override
    public String getCommandName() {
        return "Add group";
    }
}
