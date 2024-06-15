package com.example.cli;

import com.example.entity.Child;
import com.example.exceptions.IncorrectInput;
import com.example.repository.ChildRepositoryImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class RemoveChild implements Command{
    @Override
    public void execute() throws IncorrectInput, SQLException {
        Scanner scn = new Scanner(System.in);

        List<Child> children = ChildRepositoryImpl.getInstance().getAll();
        if (children.size() == 0){
            System.out.println("There are no children");
        }else {
            System.out.println("Choose child you want to delete");
            new ReadChildren().execute();
            System.out.println("Enter number");
            int id = scn.nextInt();
            if (id > children.size() || id < 0) {
                throw new IncorrectInput("Incorrect id");
            }
            ChildRepositoryImpl.getInstance().removeById(children.get(id - 1).getId());
        }
    }

    @Override
    public String getCommandName() {
        return "Remove child";
    }
}
