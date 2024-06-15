package com.example.repository;

import com.example.entity.Child;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface ChildRepository extends Repository<Child, UUID>{
    List<Child> getByGroupId(UUID id) throws SQLException;
}
