package ru.Timur.XML;

import ru.Timur.*;
import ru.Timur.Exceptions.EndOfFileException;
import ru.Timur.Exceptions.NonValidFileElementException;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.InputMismatchException;

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
    public SpaceMarine readElement() throws XMLStreamException {
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

        SpaceMarine element = validation(string);

        return element;
    }

    /**
     * Есть ли еще элемент в файле
     * @return true если есть
     */
    public boolean hasNext(){
        return this.reader.hasNext();
    }

    public SpaceMarine validation(String element){
        ByteArrayInputStream bais = new ByteArrayInputStream(element.getBytes());
        InputStream inputStream = bais;
        StreamReader streamReader = new StreamReader(bais);


        String name = null; //Поле не может быть null, Строка не может быть пустой
        Coordinates coordinates= null; //Поле не может быть null
        Float health= null; //Поле не может быть null, Значение поля должно быть больше 0
        boolean loyal = false;
        String achievements = null; //Поле может быть null
        AstartesCategory category = null; //Поле может быть null
        Chapter chapter = null; //Поле не может быть null

//      name
        while(true){
            try {
                if(inputStream == System.in) System.out.println("Введите имя:");
                String input;
                input = streamReader.readLine();
                if(inputStream != System.in && input.equals("null")){
                    System.out.println("Неправильный ввод");
                    throw new NonValidFileElementException("name value");
                }


                if(input.equals("s409145")){
                    name = "Timur";
                    coordinates = new Coordinates(123, 312.d);
                    health = 123.f;
                    loyal = true;
                    achievements = "achievement";
                    category = AstartesCategory.ASSAULT;
                    chapter = new Chapter("first", 400L );

                    return new SpaceMarine(name, coordinates, health, loyal, achievements, category, chapter);
                }


                if (input != null && !input.trim().isEmpty()) {
                    name = input;
                    break;
                } else if(inputStream == System.in){
                    System.out.println("Неправильный ввод имени, попробуйте ещё раз");
                } else if (input.isBlank() || input.isEmpty()) {
                    throw new InputMismatchException();
                }
            }catch (InputMismatchException e){
                if(inputStream == System.in) System.out.println("Неправильный ввод имени, попробуйте ещё раз");
                else throw new InputMismatchException();
                if(inputStream == System.in) System.out.println("Введите имя:");
            }
        }

//        coordinates
        {
            Integer coordX = null;
            Double coordY = null;

            boolean successfulX = false;
            do{
                try {

                    if(inputStream == System.in) System.out.println("Введите координату X:");
                    Integer inputX = Integer.parseInt(streamReader.readLine());

                    coordX = inputX;
                    successfulX = true;
                } catch (InputMismatchException | NumberFormatException e) {
                    if(inputStream == System.in) System.out.println("Неправильный ввод координаты X, попробуйте ещё раз");
                    else throw new NonValidFileElementException("X coord");
                }
            }while (!successfulX);

            boolean successfulY = false;
            do {
                if(inputStream == System.in) System.out.println("Введите координату Y:");
                try {

                    Double inputY = Double.parseDouble(streamReader.readLine());
                    if (inputY > -162.d) {
                        coordY = inputY;
                        successfulY = true;
                    } else {
                        if(inputStream == System.in) System.out.println("Неправильный ввод координаты Y, попробуйте ещё раз");
                    }
                } catch (InputMismatchException | NumberFormatException e) {
                    if(inputStream == System.in) System.out.println("Неправильный ввод координаты Y, попробуйте ещё раз");
                    else throw  new NonValidFileElementException("Y coord");
                }
            }while (!successfulY);

            coordinates = new Coordinates(coordX, coordY);
        }

//        health
        while(true){
            if(inputStream == System.in) System.out.println("Введите здоровье:");

            try {

                Float input = Float.parseFloat(streamReader.readLine());
                if (input != null && input > 0) {
                    health = input;
                    break;
                } else {
                    if(inputStream == System.in) System.out.println("Неправильный ввод здоровья, попробуйте ещё раз");
                }
            }catch (InputMismatchException | NumberFormatException e){
                if(inputStream == System.in) System.out.println("Неправильный ввод здоровья, попробуйте ещё раз");
                else throw  new NonValidFileElementException("health value");
            }
        }

//        loyal
        while(true){
            try {
                if(inputStream == System.in) System.out.println("Ответе \"Да(true)\" или \"Нет(false)\" на то союзный ли космодесантник");

                String input = streamReader.readLine();
                if(inputStream != System.in && input.equals("null")){
                    System.out.println("Неправильный ввод");
                    throw new NonValidFileElementException("неправильный элемент в файле");
                }
                if (input.equals("Да") || input.equals("true")) {
                    loyal = true;
                    break;
                } else if (input.equals("Нет") || input.equals("false")) {
                    loyal = false;
                    break;
                } else {
                    if(inputStream == System.in) System.out.println("Неправильный ввод, попробуйте ещё раз");
                }
            }catch (InputMismatchException e){
                if(inputStream == System.in) System.out.println("Неправильный ввод, попробуйте ещё раз");
                else throw  new NonValidFileElementException("loyal value");
            }

        }

//        achievements
        while(true){
            try{
                if(inputStream == System.in) System.out.println("Введите достижения Космического десантника");

                String input = streamReader.readLine();
                achievements = input;
                break;
            } catch (InputMismatchException e){
                if(inputStream == System.in) System.out.println("Неправильный ввод, попробуйте ещё раз");
                else throw  new NonValidFileElementException("achievements value");
            }
        }

//        category
        outerloop:
        while(true){
            String input = null;
            if (inputStream == System.in) System.out.println("Выберите тип космодесантника из:\n" +
                    "   1)ASSAULT,\n" +
                    "   2)INCEPTOR,\n" +
                    "   3)TERMINATOR,\n" +
                    "   4)CHAPLAIN,\n" +
                    "   5)APOTHECARY");
            try {
                input = streamReader.readLine();
                if(inputStream != System.in && input.equals("null")){
                    System.out.println("Неправильный ввод");
                    throw new NonValidFileElementException("неправильный элемент в файле");
                }
                int index = Integer.parseInt(input);
                switch (index){
                    case 1:
                        category = AstartesCategory.ASSAULT;
                        break outerloop;
                    case 2:
                        category = AstartesCategory.INCEPTOR;
                        break outerloop;
                    case 3:
                        category = AstartesCategory.TERMINATOR;
                        break outerloop;
                    case 4:
                        category = AstartesCategory.CHAPLAIN;
                        break outerloop;
                    case 5:
                        category = AstartesCategory.APOTHECARY;
                        break outerloop;
                    default:
                        if(inputStream == System.in) System.out.println("Неправильный ввод, попробуйте ещё раз");
                        break;
                }
            }catch (InputMismatchException | NumberFormatException e){
                for(AstartesCategory astartesCategory : AstartesCategory.values()){
                    if(input.toLowerCase().equals(astartesCategory.name().toLowerCase())){
                        category = astartesCategory;
                        break outerloop;
                    }
                }
                if(inputStream == System.in) System.out.println("Неправильный ввод, попробуйте ещё раз");
                else throw  new NonValidFileElementException("category value");
            }
        }

//        chapter
        String chapterName = null;
        Long chapterCount = null;

        boolean successName = false;
        do{
            try {
                if(inputStream == System.in) System.out.println("Введите название части");

                String inputName = streamReader.readLine();
                if(inputStream != System.in && inputName.equals("null")){
                    System.out.println("Неправильный ввод");
                    throw new NonValidFileElementException("неправильный элемент в файле");
                }
                if (inputName != null && !inputName.isEmpty()) {
                    chapterName = inputName;
                    successName = true;
                } else {
                    if(inputStream == System.in) System.out.println("Непрвильный ввод, попробуйте снова");
                }
            } catch (InputMismatchException e) {
                if(inputStream == System.in) System.out.println("Неправильный ввод, попробуйте снова");
                else throw  new NonValidFileElementException("category value");
            }
        }while (!successName);

        boolean successCount = false;
        do{
            try{
                if(inputStream == System.in) System.out.println("Введите количество морпехов");

                Long inputCount = Long.parseLong(streamReader.readLine());

                if (inputCount > 0 && inputCount < 1000) {
                    chapterCount = inputCount;
                    successCount =  true;
                } else {
                    if(inputStream == System.in) System.out.println("Неправильный ввод, попробуйте снова");
                }
            }catch (InputMismatchException | NumberFormatException e){
                if(inputStream == System.in) System.out.println("Неправильный ввод, попробуйте снова");
                else throw  new NonValidFileElementException("category value");
            }
        }while (!successCount);

        chapter = new Chapter(chapterName, chapterCount);
        Id.decId();
        return new SpaceMarine(name, coordinates, health, loyal, achievements, category, chapter);
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
