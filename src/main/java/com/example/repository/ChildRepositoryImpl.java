package com.example.repository;

import com.example.entity.Child;
import com.example.my_enum.Gender;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChildRepositoryImpl implements ChildRepository{

    //private List<Child> objects;
    Connection conn;
    private static ChildRepositoryImpl instance;

    private ChildRepositoryImpl(){
        DBService dbService = new DBService();
        conn = dbService.connection();
    }

    public static ChildRepositoryImpl getInstance(){
        if (instance == null){
            instance = new ChildRepositoryImpl();
        }
        return instance;
    }

    @Override
    public List<Child> getByGroupId(UUID id) throws SQLException {
        List<Child> children = new ArrayList<>();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM children WHERE group_id = '" + id + "';");
        while (rs.next()){
            UUID uuid = rs.getObject("child_id", UUID.class);
            String surname = rs.getString("surname");
            String name = rs.getString("name");
            String patronymic = rs.getString("patronymic");
            int gender = rs.getInt("gender");
            int age = rs.getInt("age");
            UUID group_id = rs.getObject("group_id", UUID.class);

            Child child = new Child(surname, name, patronymic, Gender.FEMALE, age, group_id);
            child.setId(uuid);
            if(gender == 1){
                child.set_gender(Gender.MALE);
            }
            children.add(child);
        }
        return children;
    }

    @Override
    public Child getById(UUID uuid) throws SQLException {
        Child child = null;
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM children WHERE child_id = '" + uuid + "';");
        while (rs.next()) {
            String surname = rs.getString("surname");
            String name = rs.getString("name");
            String patronymic = rs.getString("patronymic");
            int gender = rs.getInt("gender");
            int age = rs.getInt("age");
            UUID group_id = rs.getObject("group_id", UUID.class);

            child = new Child(surname, name, patronymic, Gender.FEMALE, age, group_id);
            child.setId(uuid);
            if (gender == 1) {
                child.set_gender(Gender.MALE);
            }
        }
        return child;
    }

    @Override
    public List<Child> getAll() throws SQLException {
        List<Child> children = new ArrayList<>();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM children");
        while(rs.next()){
            UUID uuid = rs.getObject("child_id", UUID.class);
            String surname = rs.getString("surname");
            String name = rs.getString("name");
            String patronymic = rs.getString("patronymic");
            int gender = rs.getInt("gender");
            int age = rs.getInt("age");
            UUID group_id = rs.getObject("group_id", UUID.class);

            Child child = new Child(surname, name, patronymic, Gender.FEMALE, age, group_id);
            child.setId(uuid);
            if(gender == 1){
                child.set_gender(Gender.MALE);
            }
            children.add(child);
        }
        return children;
    }

    @Override
    public void add(Child obj) throws SQLException {
        int gender = 1;
        if (obj.get_gender() == Gender.FEMALE){
            gender += 1;
        }
        Statement st = conn.createStatement();
        if (obj.get_patronymic() == null){
            st.executeUpdate("INSERT INTO children(child_id, surname, name, gender, age, group_id)" +
                    " VALUES(gen_random_uuid (), '" + obj.get_surname() +
                    "', '" + obj.get_name() + "', " + gender +
                    ", " + obj.get_age() + ", '" + obj.getGroup_Id() + "');");
        }else {
            st.executeUpdate("INSERT INTO children VALUES(gen_random_uuid (), '" + obj.get_surname() +
                    "', '" + obj.get_name() + "', '" + obj.get_patronymic() + "', " + gender +
                    ", " + obj.get_age() + ", '" + obj.getGroup_Id() + "');");
        }
    }

    @Override
    public void removeById(UUID uuid) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate("DELETE FROM children WHERE child_id = '" + uuid + "';");
    }

    @Override
    public void update(Child obj) throws SQLException {
        int gender = 1;
        if (obj.get_gender() == Gender.FEMALE){
            gender += 1;
        }
        Statement st = conn.createStatement();
        if (obj.get_patronymic() == null){
            st.executeUpdate("UPDATE children SET surname = '" + obj.get_surname() +
                    "', name = '" + obj.get_name() +
                    "', gender = " + gender + ", age = " + obj.get_age() +
                    ", group_id = '" + obj.getGroup_Id() + "' WHERE child_id = '" + obj.getId() + "';");
        }else {
            st.executeUpdate("UPDATE children SET surname = '" + obj.get_surname() +
                    "', name = '" + obj.get_name() + "', patronymic = '" + obj.get_patronymic() +
                    "', gender = " + gender + ", age = " + obj.get_age() +
                    ", group_id = '" + obj.getGroup_Id() + "' WHERE child_id = '" + obj.getId() + "';");
        }
    }

    /*@Override
    public List<Child> getByGroupId(UUID id) {
        List<Child> childrenByGroupId = new ArrayList<>();
        for (Child object: objects){
            if(object.getGroup_Id() == id){
                childrenByGroupId.add(object);
            }
        };
        return childrenByGroupId;
    }

    @Override
    public Child getById(UUID uuid) {
        for (Child object: objects){
            if (object.getId() == uuid){
                return object;
            }
        };
        return null;
    }

    @Override
    public List<Child> getAll() {
        return List.copyOf(objects);
    }

    @Override
    public void add(Child obj) {
        objects.add(obj);
    }

    @Override
    public void removeById(UUID uuid) {
        objects.removeIf(object -> object.getId() == uuid);
    }

    @Override
    public void update(Child obj) {
        for (Child object: objects){
            if(object.getId() == obj.getId()){
                object.set_surname(obj.get_surname());
                object.set_name(obj.get_name());
                object.set_patronymic(obj.get_patronymic());
                object.set_age(obj.get_age());
                object.set_gender(obj.get_gender());
                object.setGroup_Id(obj.getGroup_Id());
            }
        }
    }*/
}
