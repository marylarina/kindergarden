package com.example.cli;

import com.example.entity.Child;
import com.example.entity.Group;
import com.example.exceptions.IncorrectInput;
import com.example.my_enum.ChildFields;
import com.example.my_enum.Gender;
import com.example.repository.ChildRepositoryImpl;
import com.example.service.ChildServiceImpl;
import com.example.service.GroupServiceImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class UpdateChild implements Command{
    @Override
    public void execute() throws IncorrectInput, SQLException {
        Scanner scn = new Scanner(System.in);

        List<Child> children = ChildRepositoryImpl.getInstance().getAll();
        if (children.size() == 0){
            System.out.println("There are no children");
        }else {
            List<Group> groups = GroupServiceImpl.getInstance().getAll();

            System.out.println("Choose child you want to update");
            new ReadChildren().execute();
            System.out.println("Enter number");
            int id = scn.nextInt();
            scn.nextLine();
            if (id > children.size() || id < 0) {
                throw new IncorrectInput("Incorrect id");
            }

            Child updateChild = children.get(id - 1);

            while (true) {
                ChildFields[] fields = ChildFields.values();

                System.out.println("Choose field to update:");
                System.out.println("0. Exit");
                System.out.println(ChildFields.SURNAME.ordinal() + 1 + ". " + ChildFields.SURNAME);
                System.out.println(ChildFields.NAME.ordinal() + 1 + ". " + ChildFields.NAME);
                System.out.println(ChildFields.PATRONYMIC.ordinal() + 1 + ". " + ChildFields.PATRONYMIC);
                System.out.println(ChildFields.GENDER.ordinal() + 1 + ". " + ChildFields.GENDER);
                System.out.println(ChildFields.AGE.ordinal() + 1 + ". " + ChildFields.AGE);
                System.out.println(ChildFields.GROUP_ID.ordinal() + 1 + ". " + ChildFields.GROUP_ID);

                int k = scn.nextInt();
                scn.nextLine();
                if (k == 0) {
                    break;
                }
                ChildFields field = fields[k - 1];
                switch (field) {
                    case SURNAME -> {
                        System.out.println("Enter new surname:");
                        String surname = scn.nextLine();
                        if (surname.equals("")) {
                            throw new IncorrectInput("Incorrect surname");
                        }
                        updateChild.set_surname(surname);
                    }
                    case NAME -> {
                        System.out.println("Enter new name:");
                        String name = scn.nextLine();
                        if (name.equals("")) {
                            throw new IncorrectInput("Incorrect name");
                        }
                        updateChild.set_name(name);
                    }
                    case PATRONYMIC -> {
                        System.out.println("Enter new patronymic:");
                        String patronymic = scn.nextLine();
                        updateChild.set_patronymic(patronymic);
                    }
                    case GENDER -> {
                        System.out.println("Enter gender (0 - male, 1 - female):");
                        int genderInt = scn.nextInt();
                        if (genderInt != 0 && genderInt != 1) {
                            throw new IncorrectInput("Incorrect gender");
                        }
                        Gender gender = Gender.values()[genderInt];
                        updateChild.set_gender(gender);
                    }
                    case AGE -> {
                        System.out.println("Enter new age:");
                        int age = scn.nextInt();
                        if (age == 0 || age > 7) {
                            throw new IncorrectInput("Incorrect age");
                        }
                        updateChild.set_age(age);
                    }
                    case GROUP_ID -> {
                        System.out.println("Choose group:");
                        new ReadGroups();
                        int chosenGroup = scn.nextInt();
                        if (chosenGroup > groups.size() || chosenGroup < 0) {
                            throw new IncorrectInput("Incorrect group ID");
                        }
                        UUID groupId = groups.get(chosenGroup - 1).getId();
                        updateChild.setGroup_Id(groupId);
                    }
                    default -> throw new IncorrectInput("Incorrect input while choosing field to update");
                }
            }
            ChildServiceImpl.getInstance().update(updateChild);
        }
    }

    @Override
    public String getCommandName() {
        return "Update child";
    }
}
