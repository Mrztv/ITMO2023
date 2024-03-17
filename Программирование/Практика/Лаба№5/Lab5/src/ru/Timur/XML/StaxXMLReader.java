package ru.Timur.XML;

import ru.Timur.Exceptions.EndOfFileException;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;

public class StaxXMLReader implements AutoCloseable {
    private static final XMLInputFactory FACTORY = XMLInputFactory.newInstance();

    private final XMLEventReader reader;

    public StaxXMLReader(InputStream inputStream) throws XMLStreamException {
        this.reader = FACTORY.createXMLEventReader(inputStream);
    }

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

//    public boolean startElement(String element, String parent) throws XMLStreamException {
//        while (reader.hasNext()) {
//            int event = reader.next();
//            if (parent != null && event == XMLEvent.END_ELEMENT &&
//                    parent.equals(reader.getLocalName())) {
//                return false;
//            }
//            if (event == XMLEvent.START_ELEMENT &&
//                    element.equals(reader.getLocalName())) {
//                return true;
//            }
//        }
//        return false;
//    }

//    public String getAttribute(String name) throws XMLStreamException {
//        return reader.getAttributeValue(null, name);
//    }
    public String getText() throws XMLStreamException {
        return reader.getElementText();
    }

//    public int getCount(){
//        return reader.getAttributeCount();
//    }

    public boolean hasNext(){
        return this.reader.hasNext();
    }


    public XMLEventReader getReader(){
        return reader;
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
