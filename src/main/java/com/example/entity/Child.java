package com.example.entity;

import com.example.my_enum.Gender;

import java.util.UUID;

public class Child implements Identifiable<UUID>{

    private UUID _id;
    private String _surname;
    private String _name;
    private String _patronymic;
    private Gender _gender;
    private int _age;
    private UUID group_Id;

    public Child(String _surname, String _name, String _patronymic, Gender _gender, int _age, UUID group_Id) {
        this._surname = _surname;
        this._name = _name;
        this._patronymic = _patronymic;
        this._gender = _gender;
        this._age = _age;
        this.group_Id = group_Id;
    }

    public Child(String _surname, String _name, Gender _gender, int _age, UUID group_Id) {
        this._surname = _surname;
        this._name = _name;
        this._gender = _gender;
        this._age = _age;
        this.group_Id = group_Id;
    }

    public String get_surname() {
        return _surname;
    }

    public void set_surname(String _surname) {
        this._surname = _surname;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_patronymic() {
        return _patronymic;
    }

    public void set_patronymic(String _patronymic) {
        this._patronymic = _patronymic;
    }

    public Gender get_gender() {
        return _gender;
    }

    public void set_gender(Gender _gender) {
        this._gender = _gender;
    }

    public int get_age() {
        return _age;
    }

    public void set_age(int _age) {
        this._age = _age;
    }

    public UUID getGroup_Id() {
        return group_Id;
    }

    public void setGroup_Id(UUID group_Id) {
        this.group_Id = group_Id;
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
