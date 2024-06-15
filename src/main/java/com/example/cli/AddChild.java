package com.example.cli;

import com.example.entity.Child;
import com.example.entity.Group;
import com.example.exceptions.IncorrectInput;
import com.example.my_enum.Gender;
import com.example.service.ChildServiceImpl;
import com.example.service.GroupServiceImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class AddChild implements Command{
    @Override
    public void execute() throws IncorrectInput, SQLException {

        List<Group> groups = GroupServiceImpl.getInstance().getAll();
        if (groups.size() == 0){
            System.out.println("There are no groups. Please create group first, then add children!");
        }else {
            Scanner scn = new Scanner(System.in);

            System.out.println("Enter surname:");
            String surname = scn.nextLine();
            if(surname.equals("")){
                throw new IncorrectInput("Incorrect surname");
            }

            System.out.println("Enter name:");
            String name = scn.nextLine();
            if(name.equals("")){
                throw new IncorrectInput("Incorrect name");
            }

            System.out.println("Enter age:");
            int age = scn.nextInt();
            if(age == 0 || age > 7){
                throw new IncorrectInput("Incorrect age");
            }

            System.out.println("Enter gender (0 - male, 1 - female):");
            int genderInt = scn.nextInt();
            if (genderInt != 0 && genderInt != 1) {
                throw new IncorrectInput("Incorrect gender");
            }
            Gender gender = Gender.values()[genderInt];

            System.out.println("Choose group:");
            new ReadGroups().execute();

            int chosenGroup = scn.nextInt();
            if (chosenGroup > groups.size() || chosenGroup < 0){
                throw new IncorrectInput("Incorrect group ID");
            }
            UUID groupId = groups.get(chosenGroup - 1).getId();

            System.out.println("Does child have a patronymic? Enter 1 for yes:");
            int k = scn.nextInt();
            scn.nextLine();
            if (k == 1) {
                System.out.println("Enter patronymic:");
                String patronymic = scn.nextLine();
                if(patronymic.equals("")){
                    throw new IncorrectInput("Incorrect patronymic");
                }
                Child child = new Child(surname, name, patronymic, gender, age, groupId);
                //child.setId(UUID.randomUUID());
                ChildServiceImpl.getInstance().add(child);
            } else {
                Child child = new Child(surname, name, gender, age, groupId);
                //child.setId(UUID.randomUUID());
                ChildServiceImpl.getInstance().add(child);
            }
        }
    }

    @Override
    public String getCommandName() {
        return "Add child";
    }
}

