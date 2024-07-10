//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ru.Timur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;

public class StreamReader implements Serializable {
    private static BufferedReader bufferedReader;

    public StreamReader() {
    }

    public static void changeStreamReader(InputStream inputStream) {
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    public static String readLine() {
        try {
            return bufferedReader.readLine();
        } catch (IOException var1) {
            System.out.println(var1.toString());
            return null;
        }
    }

    public static BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public static void setBufferedReader(BufferedReader bufferedReader) {
        StreamReader.bufferedReader = bufferedReader;
    }

    static {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }
}
