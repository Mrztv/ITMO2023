package ru.Timur.Command;

import com.sun.source.tree.Tree;
import ru.Timur.*;
import ru.Timur.Exceptions.ExitException;
import ru.Timur.Exceptions.NonValidFileElementException;
import ru.Timur.Scripts.StreamReader;
import ru.Timur.XML.StaxXMLWriter;

import javax.xml.stream.XMLStreamException;
import java.awt.font.LineMetrics;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;

public class Storage{
    private TreeSet<SpaceMarine> collection = new TreeSet<SpaceMarine>();
    private final Date initializationDate;
    private InputStream inputStream;



    private PriorityQueue<String> executeScriptQueue = new PriorityQueue<String>();

    public Storage(){
        initializationDate = new Date();
        inputStream = System.in;
    }

    public void add(StreamReader streamReader) throws NonValidFileElementException, IOException, InputMismatchException{
        if (collection.add(createElemFromInput(streamReader))) {
            if (inputStream == System.in) System.out.println("Элемент успешно добавлен");
        } else {
            if (inputStream == System.in) System.out.println("Элемент не был добавлен");
        }
    }

    public void info(){
        System.out.println(collection.getClass());
        System.out.println(collection.size());
        System.out.println(initializationDate);
    }

    public void show(){
        System.out.println("Collection:");
        if(collection.isEmpty()){
            System.out.println("Перекати поле...");
        }
        for(SpaceMarine sm : collection){
            System.out.println(sm.toString());
        }
    }

    public void delete(){
        int id;
        if(Arguments.getSize() == 1) {
            id = Integer.parseInt(Arguments.getArg());
        }else{
            throw new NumberFormatException();
        }
        Iterator<SpaceMarine> it = collection.iterator();
        while(it.hasNext()){
            if(it.next().getId() == id){
                it.remove();
            }
        }
    }


    public void update(StreamReader streamReader) throws IOException, NonValidFileElementException{
        final int id;
        Id.decId();
        if(Arguments.getSize() != 0) {
            id = Integer.parseInt(Arguments.getArg());
        } else{
            throw new NumberFormatException();
        }
        for(SpaceMarine sm : collection) {
            if (sm.getId() == id) {
                SpaceMarine buffersm;

                buffersm = createElemFromInput(streamReader);

                sm.setName(buffersm.getName());

                sm.setCoordinates(buffersm.getCoordinates());

                sm.setHealth(buffersm.getHealth());

                sm.setLoyal(buffersm.isLoyal());

                sm.setAchievements(buffersm.getAchievements());

                sm.setCategory(buffersm.getCategory());

                sm.setChapter(buffersm.getChapter());
                return;
            }
        }
        System.out.println("Id не найден");
        throw new RuntimeException();

    }

    public void help(){
        for(Map.Entry<String, Command> elem : Invoker.commands.entrySet()){
            System.out.print(elem.getKey() + ": ");
            elem.getValue().getDiscription();
        }
    }

    public void exit(){
        throw new ExitException();
    }

    public void clear(){
        collection.clear();
    }

    public void save(File file) throws FileNotFoundException, XMLStreamException{
        StaxXMLWriter staxXMLWriter = new StaxXMLWriter(new PrintWriter(file));
        staxXMLWriter.writeElement(this);
    }

    public void execute_script(){
        final TreeSet<SpaceMarine> backupCollection = (TreeSet<SpaceMarine>) collection.clone();
        try{
            String filePath = Arguments.getArg();
            if(Arguments.getSize() != 0){
                System.out.println("Неправильный аргумент");
                return;
            }
            Path path = Paths.get(filePath);
            if(Files.exists(path) && Files.isReadable(path)){
                if(OpenedFileSet.inSet(path)){
                    return;
                }
                //System.out.println(path.toAbsolutePath().toString());
                OpenedFileSet.add(path);
                inputStream = new FileInputStream(new File(filePath));
                Invoker invoker = new Invoker(inputStream);
                invoker.readStream(this);
                OpenedFileSet.remove(path);
                inputStream = System.in;
            }else{
                System.out.println("Файл не найден");
                return;
            }
        }catch (FileNotFoundException e){
            System.out.println(e.toString());
        }catch (RuntimeException e){
            collection = backupCollection;
            System.out.println(e.toString());
        }
    }

    // Создание элемента типа SpaceMarine из потока ввода, для ручного ввода и, в идеале, для чтения из файла.
    public SpaceMarine createElemFromInput(StreamReader streamReader) throws NoSuchElementException, IOException{
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
                if(inputStream == System.in) System.out.println("Непрвильный ввод, попробуйте снова");
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
                    if(inputStream == System.in) System.out.println("Непрвильный ввод, попробуйте снова");
                }
            }catch (InputMismatchException | NumberFormatException e){
                if(inputStream == System.in) System.out.println("Непрвильный ввод, попробуйте снова");
                else throw  new NonValidFileElementException("category value");
            }
        }while (!successCount);

        chapter = new Chapter(chapterName, chapterCount);

        return new SpaceMarine(name, coordinates, health, loyal, achievements, category, chapter);

    }


    public Collection<SpaceMarine> getCollection(){
        return collection;
    }

    public Date getInitializationDate(){
        return initializationDate;
    }


    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    static class HealthC implements Comparator<SpaceMarine>{
        @Override
        public int compare(SpaceMarine o1, SpaceMarine o2) {
            return o1.getHealth().compareTo(o2.getHealth());
        }
    }

    public void healthSort(){
        TreeSet<SpaceMarine> healthSet = new TreeSet<SpaceMarine>(new HealthC());
        healthSet.addAll(collection);
        System.out.println(healthSet);
    }

    public void addIfMin(StreamReader streamReader) throws IOException{
        SpaceMarine sm = createElemFromInput(streamReader);
        TreeSet<SpaceMarine> spaceMarines = new TreeSet<>(new HealthC());
        spaceMarines.addAll(collection);
        if(!spaceMarines.isEmpty()){
            if(sm.getHealth() < spaceMarines.first().getHealth()){
                collection.add(sm);
            }else if(Id.id > 1) Id.decId();
        }else collection.add(sm);
    }

    public void removeGreater(StreamReader streamReader) throws IOException{
        SpaceMarine spaceMarine = createElemFromInput(streamReader);
        Id.decId();
        TreeSet<SpaceMarine> healthSet = new TreeSet<>(new HealthC());
        boolean found = false;
        healthSet.removeIf(spaceMarine1 -> spaceMarine1.getHealth() > spaceMarine.getHealth());

    }

    public void removeLower(StreamReader streamReader) throws IOException{
        SpaceMarine spaceMarine = createElemFromInput(streamReader);
        Id.decId();
        TreeSet<SpaceMarine> healthSet = new TreeSet<>(new HealthC());
        boolean found = false;
        healthSet.removeIf(spaceMarine1 -> spaceMarine1.getHealth() < spaceMarine.getHealth());
    }

    public void averageOfHealth(){
        Double sum = 0.d;
        for(SpaceMarine spaceMarine : collection){
            sum += spaceMarine.getHealth();
        }
        System.out.println(sum/collection.size());
    }
    public void countGreaterThanHealth() throws NumberFormatException   {
        Double health;
        if(Arguments.getSize() == 1){
            health = Double.parseDouble(Arguments.getArg());
            int count = 0;
            for (SpaceMarine spaceMarine : collection){
                if(spaceMarine.getHealth() > health) count++;
            }
            System.out.println(count);
        }else{
            throw new NumberFormatException();
        }
    }

    public void filterStartsWithName(){
        String name = "";
        if(Arguments.getSize() == 1){
            name = Arguments.getArg();
        }
        for(SpaceMarine spaceMarine : collection){
            String regex = "^" + name;
            if(Pattern.matches(name, (CharSequence) spaceMarine.getName())){
                System.out.println(spaceMarine.toString());
            }
        }
    }


}
