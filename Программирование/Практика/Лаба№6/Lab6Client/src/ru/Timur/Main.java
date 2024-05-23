package ru.Timur;

import ru.Timur.Command.Invoker;
import ru.Timur.Command.Validation;

public class Main{
    public static void main(String[] args) {
        while (true){

            try{
                Invoker invoker = new Invoker(System.in);
                invoker.readStream(new Validation());
            }catch (RuntimeException e){
                System.out.println(e.toString());
            }
        }
    }
}