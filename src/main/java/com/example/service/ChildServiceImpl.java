package com.example.service;

import com.example.entity.Child;
import com.example.repository.ChildRepository;
import com.example.repository.ChildRepositoryImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class ChildServiceImpl implements ChildService{

    private static ChildServiceImpl instance;
    private ChildRepository childRepository;

    private ChildServiceImpl(){
        childRepository = ChildRepositoryImpl.getInstance();
    }

    public static ChildServiceImpl getInstance(){
        if (instance == null){
            instance = new ChildServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Child> getByGroupId(UUID id) throws SQLException {
        return childRepository.getByGroupId(id);
    }

    @Override
    public Child getById(UUID uuid) throws SQLException {
        return childRepository.getById(uuid);
    }

    @Override
    public List<Child> getAll() throws SQLException {
        return childRepository.getAll();
    }

    @Override
    public void add(Child obj) throws SQLException {childRepository.add(obj);}

    @Override
    public void removeById(UUID uuid) throws SQLException {
        childRepository.removeById(uuid);
    }

    @Override
    public void update(Child obj) throws SQLException {
        childRepository.update(obj);
    }
}
