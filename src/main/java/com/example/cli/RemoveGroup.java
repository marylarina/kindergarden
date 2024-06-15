package com.example.cli;

import com.example.entity.Child;
import com.example.entity.Group;
import com.example.exceptions.IncorrectInput;
import com.example.service.ChildServiceImpl;
import com.example.service.GroupServiceImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class RemoveGroup implements Command{

    @Override
    public void execute() throws IncorrectInput, SQLException {
        Scanner scn = new Scanner(System.in);

        List<Group> groups = GroupServiceImpl.getInstance().getAll();
        if (groups.size() == 1 || groups.size() == 0){
            System.out.println("You can't delete group. Create 1 or more groups first!");
        }else {

            System.out.println("Choose group you want to delete");
            new ReadGroups().execute();
            System.out.println("Enter number");
            int id = scn.nextInt();
            scn.nextLine();
            if (id > groups.size() || id < 0) {
                throw new IncorrectInput("Incorrect id");
            }

            UUID deletedGroupId = groups.get(id - 1).getId();

            //GroupServiceImpl.getInstance().removeById(deletedGroupId);

            List<Child> children = ChildServiceImpl.getInstance().getByGroupId(deletedGroupId);

            for (Child child : children) {
                if(GroupServiceImpl.getInstance().getAll().get(0).getId() != deletedGroupId) {
                    child.setGroup_Id(GroupServiceImpl.getInstance().getAll().get(0).getId());
                    ChildServiceImpl.getInstance().update(child);
                }else{
                    child.setGroup_Id(GroupServiceImpl.getInstance().getAll().get(1).getId());
                    ChildServiceImpl.getInstance().update(child);
                }
            }
            GroupServiceImpl.getInstance().removeById(deletedGroupId);
        }
    }

    @Override
    public String getCommandName() {
        return "Remove group";
    }
}
