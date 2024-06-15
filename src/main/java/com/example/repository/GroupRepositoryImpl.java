package com.example.repository;

import com.example.entity.Group;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GroupRepositoryImpl implements GroupRepository{

    //private List<Group> objects;
    Connection conn;
    private static GroupRepositoryImpl instance;

    private GroupRepositoryImpl(){
        DBService dbService = new DBService();
        conn = dbService.connection();
    }

    public static GroupRepositoryImpl getInstance(){
        if (instance == null){
            instance = new GroupRepositoryImpl();
        }
        return instance;
    }

    @Override
    public Group getById(UUID uuid) throws SQLException {
        Group g = null;
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM groups WHERE group_id = '" + uuid + "';" );
        while (rs.next()) {
            String group_name = rs.getString(2);
            g = new Group(group_name);
            g.setId(uuid);
        }
        return g;
    }

    @Override
    public List<Group> getAll() throws SQLException {
        List<Group> groups = new ArrayList<>();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM groups");
        while(rs.next()){
            UUID uuid = rs.getObject("group_id", UUID.class);
            String name = rs.getString("group_name");

            Group group = new Group(name);
            group.setId(uuid);
            groups.add(group);
        }
        return groups;
    }

    @Override
    public void add(Group obj) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate("INSERT INTO groups VALUES(gen_random_uuid (), '" + obj.get_name() + "');");
    }

    @Override
    public void removeById(UUID uuid) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate("DELETE FROM groups WHERE group_id = '" + uuid + "';");
    }

    @Override
    public void update(Group obj) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate("UPDATE groups SET group_name = '" + obj.get_name() +
                "' WHERE group_id = '" + obj.getId() + "';");
    }

    /*@Override
    public Group getById(UUID uuid) {
        for (Group object: objects){
            if (object.getId() == uuid){
                return object;
            }
        };
        return null;
    }

    @Override
    public List<Group> getAll() {
        return List.copyOf(objects);
    }

    @Override
    public void add(Group obj) {
        objects.add(obj);
    }

    @Override
    public void removeById(UUID uuid) {
        objects.removeIf(object -> object.getId() == uuid);
    }

    @Override
    public void update(Group obj) {
        for (Group object: objects){
            if (object.getId() == obj.getId()){
                object.set_name(obj.get_name());
            }
        }
    }*/
}
