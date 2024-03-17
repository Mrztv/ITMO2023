package ru.Timur.Scripts;

import java.io.*;

public class StreamReader {
    private InputStream inputStream;
    BufferedReader bufferedReader;

    public StreamReader(InputStream inputStream){
        this.inputStream = inputStream;
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    public String readLine(){
        try{
            return bufferedReader.readLine();
        }catch (IOException e){
            System.out.println(e.toString());
            return null;
        }
    }



}
