package com.example.controllers;

import com.example.entity.Child;

import java.sql.SQLException;
import java.util.List;

public interface ChildController extends Controller<Child> {

    List<Child> getByGroupId(String groupId) throws SQLException;
}
