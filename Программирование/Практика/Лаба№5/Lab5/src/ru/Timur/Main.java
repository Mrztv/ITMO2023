package ru.Timur;


import ru.Timur.Command.Invoker;
import ru.Timur.Command.Storage;
import ru.Timur.Exceptions.CantReadFileException;
import ru.Timur.Exceptions.EndOfFileException;
import ru.Timur.Exceptions.NonValidFileElementException;
import ru.Timur.XML.StaxXMLReader;

import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Класс Main
 * @author timur
 */
public class Main {
    /**
     * main точка входа, загрузка файла xml
     * @param args
     */
    public static void main(String[] args) {
        Storage storage = new Storage(); //Создание хранилища для коллекции

        //File initialization

        String pathFile = System.getenv("FileJAVA");

        BufferedInputStream file = null;
        while (true){
            try{
                if(Files.exists(Paths.get(pathFile))) {
                    if(Files.isReadable(Paths.get(pathFile))){
                        file = new BufferedInputStream(new FileInputStream(pathFile));
                        break;
                    }else{
                        throw new CantReadFileException();
                    }
                }else {
                    throw new FileNotFoundException();
                }
            }catch (FileNotFoundException | NullPointerException | CantReadFileException e){
                System.out.println(e.toString());
                Scanner scanner = new Scanner(System.in);
                System.out.println("""
                        Выберите что делать:
                        1) Ввести путь к файлу вручную
                        !1) Остановка программы""");
                if(scanner.nextLine().equals("1")){
                    pathFile = scanner.nextLine();
                    scanner.close();
                }
                else {
                    return;
                }
            }
        }

        try{
            StaxXMLReader processor = new StaxXMLReader(file);
            while(processor.hasNext()){
                String string;
                try {
                    string = processor.readElement();
                }catch (EndOfFileException e){
                    break;
                }
                //System.out.println(string);
                storage.setInputStream(file);
                try{
                    storage.add(new StreamReader(new ByteArrayInputStream(string.getBytes())));
                }catch (IOException | NonValidFileElementException e){
                    System.out.println(e.toString());
                }
            }
        }catch (XMLStreamException e){
            System.out.println(e.toString());
        }

        storage.setInputStream(System.in);

        //invoker
        try{
            Invoker invoker = new Invoker(System.in);
            invoker.readStream(storage);
        }catch (RuntimeException e){
            System.out.println(e.toString());
        }
    }
}