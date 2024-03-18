package ru.Timur;

import java.io.*;

/**
 * Чтение Потока сделано для чтения файлов, в том числе
 * Свойства bufferedReader
 * @author timur
 */

public class StreamReader {
        private BufferedReader bufferedReader;

    /**
     * Constructor
     * @param inputStream поток чтения команд
     */
    public StreamReader(InputStream inputStream){
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }
    /**
     * set bufferedReader
     * @param inputStream поток чтения команд
     *
     */
    public void setBufferedReader(InputStream inputStream) {
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    /**
     * read line from stream
     * @return line from stream | null
     */
    public String readLine(){
        try{
            return bufferedReader.readLine();
        }catch (IOException e){
            System.out.println(e.toString());
            return null;
        }
    }

}
