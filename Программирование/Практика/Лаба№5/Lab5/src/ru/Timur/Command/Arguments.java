package ru.Timur.Command;

import java.util.*;

/**
 * Класс для аргументов команд
 * @author timur
 * @see Command
 */
public class Arguments {
    private static PriorityQueue<String> collection = new PriorityQueue<>();

    /**
     * Функция получения значения размера очереди аргументов
     * @return размер
     */
    public static int getSize(){
        return collection.size();
    }

    /**
     * Добавление аргумента в очередь
     * @param string
     */
    public static void addArg(String string){

        if(!string.isBlank() && !string.isEmpty()){
            collection.add(string);
        }
    }

    /**
     * Получение аргумента
     * @return последний элемент из очереди
     */
    public static String getArg(){
        return collection.remove();
    }

    /**
     * Очищение очереди
     */
    public static void clearArgs(){
        collection.clear();
    }

}
