package ru.Timur;

import java.io.*;

/**
 * Чтение Потока сделано для чтения файлов, в том числе
 * Свойства bufferedReader
 * @author timur
 */

public class StreamReader implements Serializable {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Constructor
     * @param inputStream поток чтения команд
     */
    public static void changeStreamReader(InputStream inputStream){
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    /**
     * read line from stream
     * @return line from stream | null
     */
    public static String readLine(){
        try{
            return bufferedReader.readLine();
        }catch (IOException e){
            System.out.println(e.toString());
            return null;
        }
    }

    public static BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public static void setBufferedReader(BufferedReader bufferedReader) {
        StreamReader.bufferedReader = bufferedReader;
    }
}
