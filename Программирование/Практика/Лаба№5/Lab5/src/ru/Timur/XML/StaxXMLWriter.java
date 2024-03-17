package ru.Timur.XML;

import ru.Timur.Command.Storage;
import ru.Timur.SpaceMarine;

import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.*;

public class StaxXMLWriter{
    private static final XMLOutputFactory FACTORY = XMLOutputFactory.newInstance();
    private final XMLStreamWriter writer;

    public StaxXMLWriter(PrintWriter printWriter) throws XMLStreamException{
        this.writer = FACTORY.createXMLStreamWriter(printWriter);
    }

    public void writeElement(Storage storage) throws XMLStreamException{
        writer.writeStartDocument();
        writer.writeCharacters("\n");
        writer.writeStartElement("storage");

        for(SpaceMarine spaceMarine : storage.getCollection()){
            writer.writeCharacters("\n\t");
            writer.writeStartElement("SpaceMarine");
            //name
            writer.writeCharacters("\n\t\t");
            writer.writeStartElement("name");
            writer.writeCharacters(spaceMarine.getName());
            writer.writeEndElement();
            //coordinates block
            writer.writeCharacters("\n\t\t");
            writer.writeStartElement("coordinates");
            //X
            writer.writeCharacters("\n\t\t\t");
            writer.writeStartElement("X");
            writer.writeCharacters(spaceMarine.getCoordinates().getX().toString());
            writer.writeEndElement();
            //Y
            writer.writeCharacters("\n\t\t\t");
            writer.writeStartElement("Y");
            writer.writeCharacters(spaceMarine.getCoordinates().getX().toString());
            writer.writeEndElement();

            writer.writeCharacters("\n\t\t");
            writer.writeEndElement();
            //health
            writer.writeCharacters("\n\t\t");
            writer.writeStartElement("health");
            writer.writeCharacters(spaceMarine.getHealth().toString());
            writer.writeEndElement();
            //loyal
            writer.writeCharacters("\n\t\t");
            writer.writeStartElement("loyal");
            writer.writeCharacters(spaceMarine.isLoyal()?"true" : "false");
            writer.writeEndElement();
            //achievements
            writer.writeCharacters("\n\t\t");
            writer.writeStartElement("achievements");
            writer.writeCharacters(spaceMarine.getAchievements());
            writer.writeEndElement();
            //category
            writer.writeCharacters("\n\t\t");
            writer.writeStartElement("category");
            writer.writeCharacters(spaceMarine.getCategory().name());
            writer.writeEndElement();
            //chapter block
            writer.writeCharacters("\n\t\t");
            writer.writeStartElement("chapter");
            //X
            writer.writeCharacters("\n\t\t\t");
            writer.writeStartElement("name");
            writer.writeCharacters(spaceMarine.getChapter().getName());
            writer.writeEndElement();
            //Y
            writer.writeCharacters("\n\t\t\t");
            writer.writeStartElement("marinesCount");
            writer.writeCharacters(spaceMarine.getChapter().getMarinesCount().toString());
            writer.writeEndElement();

            writer.writeCharacters("\n\t\t");
            writer.writeEndElement();

            writer.writeCharacters("\n\t");
            writer.writeEndElement();
        }

        writer.writeCharacters("\n");
        writer.writeEndElement();
        writer.writeCharacters("\n");
        writer.writeEndDocument();
        writer.close();
    }

}
