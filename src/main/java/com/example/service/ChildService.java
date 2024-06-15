package com.example.service;

import com.example.entity.Child;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface ChildService extends Service<Child, UUID>{
    List<Child> getByGroupId(UUID id) throws SQLException;
}
