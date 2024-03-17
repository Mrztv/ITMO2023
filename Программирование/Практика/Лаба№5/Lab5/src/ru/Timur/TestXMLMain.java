//package ru.Timur;
//
//import ru.Timur.Command.Storage;
//import ru.Timur.XML.StaxXMLReader;
//import ru.Timur.XML.StaxXMLWriter;
//
//import javax.xml.stream.XMLStreamException;
//import javax.xml.stream.events.XMLEvent;
//import java.io.*;
//import java.nio.Buffer;
//import java.nio.charset.StandardCharsets;
//
//public class TestXMLMain {
//
//    public static void main(String[] args){
//        try{
//            String string = "";
//            BufferedInputStream file = new BufferedInputStream(new FileInputStream("src/ru/Timur/XML/SpaceMarines.xml"));
//            StaxXMLReader processor = new StaxXMLReader(file);
//            string = processor.readElement();
//            //System.out.println(string);
//            Storage storage = new Storage();//test - УДАЛИТЬ НАХУЙ
//            storage.add(new BufferedInputStream(new ByteArrayInputStream(string.getBytes())));
//            storage.show();
//            processor.close();
//            file.close();
//
//            StaxXMLWriter writer = new StaxXMLWriter(new PrintWriter(new File("src/ru/Timur/XML/SpaceMarines.xml")));
//            writer.writeElement(storage);
//        }catch (XMLStreamException e){
//            System.out.println(e.toString());
//        }catch (IOException e){
//            System.out.println(e.toString());
//        }
//    }
//}
