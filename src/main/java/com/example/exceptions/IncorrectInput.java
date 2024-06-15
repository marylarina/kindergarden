package com.example.exceptions;

public class IncorrectInput extends Exception{
    public IncorrectInput(String errorMessage){
        super(errorMessage);
    }
}
