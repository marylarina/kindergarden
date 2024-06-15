package com.example.cli;

import com.example.entity.Child;
import com.example.exceptions.IncorrectInput;
import com.example.repository.ChildRepositoryImpl;

import java.sql.SQLException;

public class ReadChildren implements Command{
    @Override
    public void execute() throws IncorrectInput, SQLException {
        int count = 0;
        for (Child child : ChildRepositoryImpl.getInstance().getAll()){
            count++;
            System.out.println(
                    count + ". "
                    + child.getId() + " "
                    + child.get_surname() + " "
                    + child.get_name() + " "
                    + child.get_patronymic() + " "
                    + child.get_age() + " "
                    + child.get_gender().toString() + " "
                    + child.getGroup_Id().toString());
        }
    }

    @Override
    public String getCommandName() {
        return "Read list of children";
    }
}
