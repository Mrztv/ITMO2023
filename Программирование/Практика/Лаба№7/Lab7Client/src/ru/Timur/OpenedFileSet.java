//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ru.Timur;

import java.nio.file.Path;
import java.util.HashSet;

public class OpenedFileSet {
    private static final HashSet<String> hashSet = new HashSet();

    public OpenedFileSet() {
    }

    public static void add(Path path) {
        hashSet.add(path.toAbsolutePath().toString());
    }

    public static boolean inSet(Path path) {
        return hashSet.contains(path.toAbsolutePath().toString());
    }

    public static void remove(Path path) {
        hashSet.remove(path.toAbsolutePath().toString());
    }

    public static boolean isStart() {
        return !hashSet.isEmpty();
    }
}
