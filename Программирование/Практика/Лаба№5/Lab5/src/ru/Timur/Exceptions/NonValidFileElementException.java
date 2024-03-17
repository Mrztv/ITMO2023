package ru.Timur.Exceptions;

public class NonValidFileElementException extends RuntimeException{
    public NonValidFileElementException(String reason){
        super("Non valid " + reason);
    }
}
