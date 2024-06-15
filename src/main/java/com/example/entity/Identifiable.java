package com.example.entity;

public interface Identifiable<T> {
    T getId();
    void setId(T id);
}
