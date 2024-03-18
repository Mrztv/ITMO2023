package ru.Timur;

import java.nio.file.Path;
import java.util.HashSet;

/**
 * Класс для хранения открытых файлов-скриптов
 * @author timur
 */

public class OpenedFileSet {
    /**
     * {@link HashSet} для хранения путей открытых файлов
     */
    private static final HashSet<String> hashSet = new HashSet<>();

    /**
     * Добавления путей в {@link OpenedFileSet#hashSet}
     * @param path
     */
    public static void add(Path path){
        hashSet.add(path.toAbsolutePath().toString());
    }

    /**
     * Метод для проверки файлов
     * @param path
     * @return true если файл открыт, false если файл не открыт
     */
    public static boolean inSet(Path path){
        return hashSet.contains(path.toAbsolutePath().toString());
    }

    /**
     * Метод для удаления закрытых файлов
     * @param path
     */
    public static void remove(Path path){
        hashSet.remove(path.toAbsolutePath().toString());
    }
}
