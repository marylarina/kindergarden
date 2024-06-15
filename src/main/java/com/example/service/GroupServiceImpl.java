package com.example.service;

import com.example.entity.Group;
import com.example.repository.GroupRepository;
import com.example.repository.GroupRepositoryImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class GroupServiceImpl implements GroupService{

    private static GroupServiceImpl instance;
    private GroupRepository groupRepository;

    private GroupServiceImpl(){
        groupRepository = GroupRepositoryImpl.getInstance();
    }

    public static GroupServiceImpl getInstance(){
        if (instance == null){
            instance = new GroupServiceImpl();
        }
        return instance;
    }

    @Override
    public Group getById(UUID uuid) throws SQLException {
        return groupRepository.getById(uuid);
    }

    @Override
    public List<Group> getAll() throws SQLException {
        return groupRepository.getAll();
    }

    @Override
    public void add(Group obj) throws SQLException {
        groupRepository.add(obj);
    }

    @Override
    public void removeById(UUID uuid) throws SQLException {
        groupRepository.removeById(uuid);
    }

    @Override
    public void update(Group obj) throws SQLException {
        groupRepository.update(obj);
    }
}
