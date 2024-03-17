package ru.Timur;

import java.io.File;
import java.nio.file.Path;
import java.util.HashSet;

public class OpenedFileSet {
    private static final HashSet<String> hashSet = new HashSet<String>();

    public static void add(Path path){
        hashSet.add(path.toAbsolutePath().toString());
    }

    public static boolean inSet(Path path){
        return hashSet.contains(path.toAbsolutePath().toString());
    }

    public static void remove(Path path){
        hashSet.remove(path.toAbsolutePath().toString());
    }
}
