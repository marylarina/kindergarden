package com.example.entity;

import java.util.UUID;

public class Group implements Identifiable<UUID>{

    private UUID _id;
    private String _name;

    public Group(String _name) {
        this._name = _name;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    @Override
    public UUID getId() {
        return _id;
    }

    @Override
    public void setId(UUID id) {
        this._id = id;
    }
}