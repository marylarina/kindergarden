package com.example.controllers;


import com.example.entity.Child;
import com.example.entity.Group;
import com.example.repository.GroupRepositoryImpl;
import com.example.service.ChildServiceImpl;
import com.example.service.GroupServiceImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class GroupControllerImpl implements GroupController {

    private static GroupControllerImpl instance;

    public static GroupControllerImpl getInstance() {
        if (instance == null)
            instance = new GroupControllerImpl();
        return instance;
    }

    @Override
    public String Add(String... args) throws SQLException {
        String name = args[0];
        if (name.isEmpty()){
            return "Incorrect name of a group";
        }
        Group group = new Group(name);
        GroupRepositoryImpl.getInstance().add(group);

        return "Group created";
    }

    @Override
    public String Delete(String Id) throws SQLException {
        int count = 0;
        List<Group> groups = GroupServiceImpl.getInstance().getAll();
        UUID deletedGroupId = UUID.fromString(Id);
        for (Group group : groups){
            if (group.getId().equals(deletedGroupId)){
                count++;
            }
        }
        if (groups.size() == 1 || groups.isEmpty() || count == 0){
            return "You can't delete group. Create 1 or more groups first! Or chech uuid, there can be mistake";
        }
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
        return "Group deleted";
    }

    @Override
    public String Update(String... args) throws SQLException {

        List<Group> groups = GroupServiceImpl.getInstance().getAll();
        int count = 0;
        String Id = args[0];
        UUID updateGroupId = UUID.fromString(Id);
        for (Group group : groups){
            if (group.getId().equals(updateGroupId)){
                count++;
            }
        }
        if (count == 0) {
            return "You can't update group. Create 1 or more groups first!";
        }

        Group updateGroup = GroupServiceImpl.getInstance().getById(updateGroupId);
        String name = args[1];
        if (name.isEmpty()){
            return "Incorrect name of a group";
        }
        updateGroup.set_name(name);
        GroupServiceImpl.getInstance().update(updateGroup);
        return "Group updated";
    }

    @Override
    public List<Group> All() throws SQLException {
        return GroupRepositoryImpl.getInstance().getAll();
    }
}
