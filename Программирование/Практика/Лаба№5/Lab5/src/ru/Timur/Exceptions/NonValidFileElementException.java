package ru.Timur.Exceptions;

/**
 * Исключение для неправильного формата элемента в файле
 */
public class NonValidFileElementException extends RuntimeException{
    public NonValidFileElementException(String reason){
        super("Non valid " + reason);
    }
}
