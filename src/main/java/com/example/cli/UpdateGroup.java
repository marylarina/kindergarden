package com.example.cli;

import com.example.entity.Group;
import com.example.exceptions.IncorrectInput;
import com.example.my_enum.GroupFields;
import com.example.service.GroupServiceImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UpdateGroup implements Command{
    @Override
    public void execute() throws IncorrectInput, SQLException {
        Scanner scn = new Scanner(System.in);

        List<Group> groups = GroupServiceImpl.getInstance().getAll();
        if (groups.size() == 0){
            System.out.println("There are no groups");
        }else {
            System.out.println("Choose group you want to update");
            new ReadGroups().execute();
            System.out.println("Enter number");
            int id = scn.nextInt();
            scn.nextLine();
            if (id > groups.size() || id < 0) {
                throw new IncorrectInput("Incorrect id");
            }

            Group updateGroup = groups.get(id - 1);

            while (true) {
                GroupFields[] fields = GroupFields.values();

                System.out.println("Choose field to update:");
                System.out.println("0. Exit");
                System.out.println(GroupFields.NAME.ordinal() + 1 + ". " + GroupFields.NAME);

                int k = scn.nextInt();
                scn.nextLine();
                if (k == 0) {
                    break;
                }

                GroupFields field = fields[k - 1];
                if (field == GroupFields.NAME) {
                    System.out.println("Enter new name:");
                    String name = scn.nextLine();
                    if (name.equals("")) {
                        throw new IncorrectInput("Incorrect name of a group");
                    }
                    updateGroup.set_name(name);
                } else {
                    throw new IncorrectInput("Incorrect input while choosing field to update");
                }
            }
            GroupServiceImpl.getInstance().update(updateGroup);
        }
    }

    @Override
    public String getCommandName() {
        return "Update group";
    }
}
