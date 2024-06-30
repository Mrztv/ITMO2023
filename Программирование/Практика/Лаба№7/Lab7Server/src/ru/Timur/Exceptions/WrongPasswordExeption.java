package ru.Timur.Exceptions;

public class WrongPasswordExeption extends RuntimeException{
    public WrongPasswordExeption(){
        super("Wrong password");
    }
}
