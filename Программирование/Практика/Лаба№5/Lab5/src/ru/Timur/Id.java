package ru.Timur;

/**
 * Класс для создания id
 * @author timur
 */
public class Id {
    /**
     * Статическое поле id для получения его для {@link SpaceMarine}
     */
    public static long  id = 0;

    /**
     * Увеличение id и возвращение его нового значения
     * @return id
     */
    public static long incAndGet(){
        return ++id;
    }

    /**
     * Метод для умиеньшения id на 1
     */
    public static void decId(){
        id -= 1;
    }

}
