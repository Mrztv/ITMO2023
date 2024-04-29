package ru.Timur.Command;

import org.graalvm.collections.Pair;
import ru.Timur.*;
import ru.Timur.Exceptions.NonValidFileElementException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Класс хранилища коллекции и ресивер
 * @author timur
 */
public class Validation implements Serializable {
    static final long serialVersionUID = 1L;
    private boolean isSystemIn;

    /**
     * Конструктор
     */
    public Validation(){
        isSystemIn = true;
    }

    /**
     * Добавление элемента в коллекцию
     * @throws NonValidFileElementException
     * @throws IOException
     * @throws InputMismatchException
     */
    public SpaceMarine add() throws NonValidFileElementException, IOException, InputMismatchException{
        SpaceMarine spaceMarine = createElemFromInput();
        if(spaceMarine != null){
            return spaceMarine;
        }
        else return null;
    }

    /**
     * Удаление элемента из коллекции по ID
     */
    public Long remove_by_id(){
        Long id;
        if(Arguments.getSize() == 1) {
            try {
                id = Long.parseLong(Arguments.getArg());
                return id;
            }catch (NumberFormatException e){
                return null;
            }
        }
        return null;
    }

    /**
     * Обновление существующего элемента
     * @throws IOException
     * @throws NonValidFileElementException
     */
    public SpaceMarine update() throws IOException, NonValidFileElementException{
        Long id = null;
        if(Arguments.getSize() == 1) {
            try {
                id = Long.parseLong(Arguments.getArg());
            }catch (NumberFormatException e){
                return null;
            }
        }

        SpaceMarine spaceMarine = createElemFromInput();
        spaceMarine.setId(id);
        return spaceMarine;

    }


    /**
     * Выход из приложения без сохранения
     */
    public void exit(){
        System.exit(1);
    }


    /**
     * Выполнение файла-скрипта
     */
    public String execute_script(){
        try{
            String filePath;
            if(Arguments.getSize() == 1){
                filePath = Arguments.getArg();
            }else{
                System.out.println("Неправильный аргумент");
                return null;
            }
            Path path = Paths.get(filePath);
            if(Files.exists(path) && Files.isReadable(path)){
                if(OpenedFileSet.inSet(path)){
                    System.out.println("Файл \"" + path.toAbsolutePath().toString() + "\" не выполнен");
                    return null;
                }
                //System.out.println(path.toAbsolutePath().toString());
                OpenedFileSet.add(path);
                isSystemIn = false;
                InputStream inputStream = new FileInputStream(filePath);
                BufferedReader bufferedReader = StreamReader.getBufferedReader();
                Invoker invoker = new Invoker(inputStream);
                invoker.readStream(this);
                StreamReader.setBufferedReader(bufferedReader);
                OpenedFileSet.remove(path);
                isSystemIn = true;
                return filePath;
            }else{
                System.out.println("Файл не найден");
                return null;
            }
        }catch (FileNotFoundException | RuntimeException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Создание элемента типа SpaceMarine из потока ввода
     */
    public SpaceMarine createElemFromInput() throws NoSuchElementException, IOException{
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
                if(isSystemIn) System.out.println("Введите имя:");
                String input;
                input = StreamReader.readLine();
                if(isSystemIn  && input.equals("null")){
                    System.out.println("Неправильный ввод");
                    throw new NonValidFileElementException("name value");
                }


                if(input.equals("s")){
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
                } else if(isSystemIn ){
                    System.out.println("Неправильный ввод имени, попробуйте ещё раз");
                } else if (input.isBlank() || input.isEmpty()) {
                    throw new InputMismatchException();
                }
            }catch (InputMismatchException e){
                if(isSystemIn ) System.out.println("Неправильный ввод имени, попробуйте ещё раз");
                else throw new InputMismatchException();
                if(isSystemIn ) System.out.println("Введите имя:");
            }
        }

//        coordinates
        {
            Integer coordX = null;
            Double coordY = null;

            boolean successfulX = false;
            do{
                try {

                    if(isSystemIn ) System.out.println("Введите координату X:");
                    Integer inputX = Integer.parseInt(StreamReader.readLine());

                    coordX = inputX;
                    successfulX = true;
                } catch (InputMismatchException | NumberFormatException e) {
                    if(isSystemIn ) System.out.println("Неправильный ввод координаты X, попробуйте ещё раз");
                    else throw new NonValidFileElementException("X coord");
                }
            }while (!successfulX);

            boolean successfulY = false;
            do {
                if(isSystemIn ) System.out.println("Введите координату Y:");
                try {

                    Double inputY = Double.parseDouble(StreamReader.readLine());
                    if (inputY > -162.d) {
                        coordY = inputY;
                        successfulY = true;
                    } else {
                        if(isSystemIn ) System.out.println("Неправильный ввод координаты Y, попробуйте ещё раз");
                    }
                } catch (InputMismatchException | NumberFormatException e) {
                    if(isSystemIn ) System.out.println("Неправильный ввод координаты Y, попробуйте ещё раз");
                    else throw  new NonValidFileElementException("Y coord");
                }
            }while (!successfulY);

            coordinates = new Coordinates(coordX, coordY);
        }

//        health
        while(true){
            if(isSystemIn ) System.out.println("Введите здоровье:");

            try {

                Float input = Float.parseFloat(StreamReader.readLine());
                if (input != null && input > 0) {
                    health = input;
                    break;
                } else {
                    if(isSystemIn ) System.out.println("Неправильный ввод здоровья, попробуйте ещё раз");
                }
            }catch (InputMismatchException | NumberFormatException e){
                if(isSystemIn ) System.out.println("Неправильный ввод здоровья, попробуйте ещё раз");
                else throw  new NonValidFileElementException("health value");
            }
        }

//        loyal
        while(true){
            try {
                if(isSystemIn ) System.out.println("Ответе \"Да(true)\" или \"Нет(false)\" на то союзный ли космодесантник");

                String input = StreamReader.readLine();
                if(isSystemIn  && input.equals("null")){
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
                    if(isSystemIn ) System.out.println("Неправильный ввод, попробуйте ещё раз");
                }
            }catch (InputMismatchException e){
                if(isSystemIn ) System.out.println("Неправильный ввод, попробуйте ещё раз");
                else throw  new NonValidFileElementException("loyal value");
            }

        }

//        achievements
        while(true){
            try{
                if(isSystemIn ) System.out.println("Введите достижения Космического десантника");

                String input = StreamReader.readLine();
                achievements = input;
                break;
            } catch (InputMismatchException e){
                if(isSystemIn ) System.out.println("Неправильный ввод, попробуйте ещё раз");
                else throw  new NonValidFileElementException("achievements value");
            }
        }

//        category
        outerloop:
        while(true){
            String input = null;
            if (isSystemIn ) System.out.println("Выберите тип космодесантника из:\n" +
                    "   1)ASSAULT,\n" +
                    "   2)INCEPTOR,\n" +
                    "   3)TERMINATOR,\n" +
                    "   4)CHAPLAIN,\n" +
                    "   5)APOTHECARY");
            try {
                input = StreamReader.readLine();
                if(isSystemIn  && input.equals("null")){
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
                        if(isSystemIn ) System.out.println("Неправильный ввод, попробуйте ещё раз");
                        break;
                }
            }catch (InputMismatchException | NumberFormatException e){
                for(AstartesCategory astartesCategory : AstartesCategory.values()){
                    if(input.toLowerCase().equals(astartesCategory.name().toLowerCase())){
                        category = astartesCategory;
                        break outerloop;
                    }
                }
                if(isSystemIn ) System.out.println("Неправильный ввод, попробуйте ещё раз");
                else throw  new NonValidFileElementException("category value");
            }
        }

//        chapter
        String chapterName = null;
        Long chapterCount = null;

        boolean successName = false;
        do{
            try {
                if(isSystemIn ) System.out.println("Введите название части");

                String inputName = StreamReader.readLine();
                if(isSystemIn  && inputName.equals("null")){
                    System.out.println("Неправильный ввод");
                    throw new NonValidFileElementException("неправильный элемент в файле");
                }
                if (inputName != null && !inputName.isEmpty()) {
                    chapterName = inputName;
                    successName = true;
                } else {
                    if(isSystemIn ) System.out.println("Непрвильный ввод, попробуйте снова");
                }
            } catch (InputMismatchException e) {
                if(isSystemIn ) System.out.println("Неправильный ввод, попробуйте снова");
                else throw  new NonValidFileElementException("category value");
            }
        }while (!successName);

        boolean successCount = false;
        do{
            try{
                if(isSystemIn ) System.out.println("Введите количество морпехов");

                Long inputCount = Long.parseLong(StreamReader.readLine());

                if (inputCount > 0 && inputCount < 1000) {
                    chapterCount = inputCount;
                    successCount =  true;
                } else {
                    if(isSystemIn ) System.out.println("Неправильный ввод, попробуйте снова");
                }
            }catch (InputMismatchException | NumberFormatException e){
                if(isSystemIn ) System.out.println("Неправильный ввод, попробуйте снова");
                else throw  new NonValidFileElementException("category value");
            }
        }while (!successCount);

        chapter = new Chapter(chapterName, chapterCount);

        return new SpaceMarine(name, coordinates, health, loyal, achievements, category, chapter);

    }


    /**
     * Добавляет элемент в коллекцию если значение его здоровья минимально
     * @throws IOException
     */
    public SpaceMarine addIfMin() throws IOException{
        return createElemFromInput();
    }

    /**
     * Удаляет из коллекции все элементы, превышающие заданный
     * @throws IOException
     */
    public SpaceMarine removeGreater() throws IOException{
        return createElemFromInput();
    }

    /**
     * Удаляет из коллекции все элементы, меньшие, чем заданный
     * @throws IOException
     */
    public SpaceMarine removeLower() {
        try {
            return createElemFromInput();
        }catch (IOException e){
            System.out.println(e.toString());
            return null;
        }
    }

    /**
     * Выводит количество элементов, значение поля health которых больше заданного
     * @throws NumberFormatException
     */
    public Float countGreaterThanHealth() throws NumberFormatException   {
        Float health;
        if(Arguments.getSize() == 1) {
            try {
                health = Float.parseFloat(Arguments.getArg());
                return health;
            }catch (NumberFormatException e){
                return null;
            }

        }else return null;
    }

    /**
     * Выводит элементы, значение поля name которых начинается с заданной подстроки
     */
    public String filterStartsWithName(){
        if(Arguments.getSize() == 1){
            return Arguments.getArg();
        }
        else return null;
    }



    public boolean help(){
        return true;
    }

}

