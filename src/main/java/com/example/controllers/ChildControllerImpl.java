package com.example.controllers;


import com.example.entity.Child;
import com.example.entity.Group;
import com.example.my_enum.Gender;
import com.example.repository.ChildRepositoryImpl;
import com.example.service.ChildServiceImpl;
import com.example.service.GroupServiceImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class ChildControllerImpl implements ChildController {
    private static ChildControllerImpl instance;

    public static ChildControllerImpl getInstance() {
        if (instance == null)
            instance = new ChildControllerImpl();
        return instance;
    }

    @Override
    public List<Child> getByGroupId(String groupId) throws SQLException {
        UUID Id = UUID.fromString(groupId);
        return ChildRepositoryImpl.getInstance().getByGroupId(Id);
    }

    @Override
    public String Add(String... args) throws SQLException {

        List<Group> groups = GroupServiceImpl.getInstance().getAll();
        if (groups.isEmpty()){
            return "There are no groups. Please create group first, then add children!";
        }

        String surname = args[0];
        if(surname.isEmpty()){
            return "Incorrect surname";
        }

        String name = args[1];
        if(name.isEmpty()){
            return "Incorrect name";
        }

        int age = Integer.parseInt(args[2]);
        if(age == 0 || age > 7){
            return "Incorrect age";
        }

        int genderInt = Integer.parseInt(args[3]);
        if (genderInt != 0 && genderInt != 1) {
            return "Incorrect gender";
        }
        Gender gender = Gender.values()[genderInt];

        int count = 0;
        UUID GroupId = UUID.fromString(args[4]);
        for (Group group : groups){
            if (group.getId().equals(GroupId)){
                count++;
            }
        }
        if (count == 0) {
            return "You can't update group. Create 1 or more groups first!";
        }

        String patronymic = args[5];
        if (patronymic.isEmpty()){
            Child child = new Child(surname, name, gender, age, GroupId);
            ChildServiceImpl.getInstance().add(child);
            return "Added child successfully!";
        }
        Child child = new Child(surname, name, patronymic, gender, age, GroupId);
        ChildServiceImpl.getInstance().add(child);
        return "Added child successfully!";
    }

    @Override
    public String Delete(String Id) throws SQLException {
        int count = 0;
        UUID childId = UUID.fromString(Id);
        List<Child> children = ChildRepositoryImpl.getInstance().getAll();
        if (children.isEmpty()){
            return "There are no children";
        }else {
            for (Child child : children){
                if (child.getId().equals(childId)){
                    count++;
                }
            }
            if(count == 0){
                return "There are no children with this id!";
            }
            ChildRepositoryImpl.getInstance().removeById(childId);
        }
        return "Deleted child successfully!";
    }

    @Override
    public String Update(String... args) throws SQLException {
        List<Child> children = ChildRepositoryImpl.getInstance().getAll();
        List<Group> groups = GroupServiceImpl.getInstance().getAll();
        int count = 0;
        String Id = args[0];
        UUID updateChildId = UUID.fromString(Id);
        for (Child child : children){
            if (child.getId().equals(updateChildId)){
                count++;
            }
        }
        if(count == 0){
            return "There are no children with this id!";
        }
        Child updateChild = ChildRepositoryImpl.getInstance().getById(updateChildId);

        String surname = args[1];
        if(surname.isEmpty()){
            return "Incorrect surname";
        }
        updateChild.set_surname(surname);

        String name = args[2];
        if(name.isEmpty()){
            return "Incorrect name";
        }
        updateChild.set_name(name);

        int age = Integer.parseInt(args[3]);
        if(age == 0 || age > 7){
            return "Incorrect age";
        }
        updateChild.set_age(age);

        int genderInt = Integer.parseInt(args[4]);
        if (genderInt != 0 && genderInt != 1) {
            return "Incorrect gender";
        }
        Gender gender = Gender.values()[genderInt];
        updateChild.set_gender(gender);

        int countGroup = 0;
        UUID GroupId = UUID.fromString(args[5]);
        for (Group group : groups){
            if (group.getId().equals(GroupId)){
                countGroup++;
            }
        }
        if (countGroup == 0) {
            return "You can't update group. Create 1 or more groups first!";
        }
        updateChild.setGroup_Id(GroupId);

        String patronymic = args[6];
        updateChild.set_patronymic(patronymic);
        ChildServiceImpl.getInstance().update(updateChild);
        return "Updated child successfully!";
    }

    @Override
    public List<Child> All() throws SQLException {
        return ChildServiceImpl.getInstance().getAll();
    }
}
