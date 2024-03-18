package ru.Timur.XML;

import ru.Timur.Exceptions.EndOfFileException;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;

/**
 * Класс для чтения XML файла и парсинга в коллекцию
 */
public class StaxXMLReader implements AutoCloseable {
    private static final XMLInputFactory FACTORY = XMLInputFactory.newInstance();

    private final XMLEventReader reader;

    /**
     * Задание читателя потока
     * @param inputStream
     * @throws XMLStreamException
     */
    public StaxXMLReader(InputStream inputStream) throws XMLStreamException {
        this.reader = FACTORY.createXMLEventReader(inputStream);
    }


    /**
     * Чтение элемента из файла и оформление его в виде {@link ru.Timur.SpaceMarine}
     * @return элемент
     * @throws XMLStreamException
     */
    public String readElement() throws XMLStreamException {
        XMLEvent xmlEvent = null;
        boolean inSpaceMarine = false;
        while (reader.hasNext()) {
            xmlEvent = reader.nextEvent();
            if(xmlEvent.isStartElement()){
                if(xmlEvent.asStartElement().getName().toString().equals("SpaceMarine")) {
                    inSpaceMarine = true;
                    break;
                }
            } else if (xmlEvent.isEndDocument()) {
                throw new EndOfFileException();
            }

        }
        boolean written = false;
        String string = "";
        while(inSpaceMarine && reader.hasNext()){
            xmlEvent = reader.nextEvent();
            if(xmlEvent.isCharacters()){
                if(!xmlEvent.asCharacters().isWhiteSpace()){
                    written = true;
                    string += xmlEvent.asCharacters().getData() + "\n";
                }
            }else if(xmlEvent.isEndElement()) {
                if(!written){
                    written = false;
                    string += null + "\n";
                }
                if (xmlEvent.asEndElement().getName().toString().equals("SpaceMarine")) {
                    break;
                }
            }else if (xmlEvent.isEndDocument()) {
                throw new EndOfFileException();
            }else{
                written = false;
            }
        }
        return string;
    }

    /**
     * Есть ли еще элемент в файле
     * @return true если есть
     */
    public boolean hasNext(){
        return this.reader.hasNext();
    }

    @Override
    public void close() {
        if (reader != null) {
            try {
                reader.close();
            } catch (XMLStreamException e) { // empty
            }
        }
    }
}
