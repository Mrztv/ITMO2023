package ru.Timur.Command;

import ru.Timur.*;
import ru.Timur.Exceptions.NonValidFileElementException;
import ru.Timur.XML.StaxXMLWriter;

import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс хранилища коллекции и ресивер
 * @author timur
 */
public class Storage implements Serializable {

    private static final Date initializationDate = new Date();
    static final long serialVersionUID = 1L;
    private Set<SpaceMarine> collection = new TreeSet<>();
    private InputStream inputStream;

    public void setInputStream(InputStream inputStream){
        this.inputStream = inputStream;
    }

    public boolean add(SpaceMarine spaceMarine){
        spaceMarine.setId(Id.incAndGet());
        spaceMarine.setCreationDate(ZonedDateTime.now());
        return collection.add(spaceMarine);
    }


    public boolean addIfMin(SpaceMarine spaceMarine){
        Optional<SpaceMarine> collection2 = collection.stream().min((p1, p2) -> p1.getHealth().compareTo(p2.getHealth()));
        if(spaceMarine.getHealth() < collection2.get().getHealth()){
            return collection.add(spaceMarine);
        }
        return false;
    }

    public Float averageOfHealth(){
        return (float) collection.stream()
                .mapToDouble(sm -> sm.getHealth())
                .average()
                .orElse(Double.NaN);
    }

    public void clear(){
        collection.clear();
    }

    public long countGreaterThanHealth(Float health){
         return collection.stream()
            .filter(sm -> sm.getHealth() > health)
            .count();
    }

    public void executeScript(Queue<Command> queue){
        while(!queue.isEmpty()){
            queue.poll().execute();
        }
    }

    public void exit(){
        System.exit(1);
    }

    public Set<SpaceMarine> filterStartsWithName(String name){
        return collection.stream()
                .filter(p -> p.getName().startsWith(name))
                .collect(Collectors.toSet());
    }

    public String help(){
        Map<String, Command> commands = new HashMap<String, Command>();
        commands.put("add", new AddCommand(this));
        commands.put("info", new InfoCommand(this));
        commands.put("show", new ShowCommand(this));
        commands.put("remove_by_id", new RemoveByIdCommand(this));
        commands.put("update", new UpdateCommand(this));
        commands.put("help", new HelpCommand(this));
        commands.put("exit", new ExitCommand(this));
        commands.put("clear", new ClearCommand(this));
        commands.put("execute_script", new ExecuteScriptCommand(this));
        commands.put("add_if_min", new AddIfMinCommand(this));
        commands.put("remove_greater", new RemoveGreaterCommand(this));
        commands.put("remove_lower", new RemoveLowerCommand(this));
        commands.put("average_of_health", new AverageOfHealthCommand(this));
        commands.put("count_greater_than_health", new CountGreaterThanHealthCommand(this));
        commands.put("filter_starts_with_name", new FilterStartsWithNameCommand(this));

        String out = "";
        for(Map.Entry<String, Command> elem : commands.entrySet()){
            out += elem.getKey() + ": ";
            out += elem.getValue().getDiscription();
            out += '\n';
        }
        return out;
    }

    public String info(){
        String out = "";
        out += collection.getClass() + "\n";
        out += collection.size() + "\n";
        out += initializationDate + "\n";
        return out;
    }

    public boolean removeById(Long id){
        if(!collection.stream()
                .filter(spaceMarine -> !Objects.equals(spaceMarine.getId(), id))
                .collect(Collectors.toSet()).isEmpty()){
            collection = collection.stream()
                    .filter(spaceMarine -> !Objects.equals(spaceMarine.getId(), id))
                    .collect(Collectors.toSet());
            return true;
        }
        else return false;
    }

    public boolean removeGreater(Float health){
        if(collection.equals(collection.stream()
                .filter(spaceMarine -> spaceMarine.getHealth() < health)
                .collect(Collectors.toSet()))) return false;
        else {
            collection = collection.stream()
                    .filter(spaceMarine -> spaceMarine.getHealth() < health)
                    .collect(Collectors.toSet());
            return true;
        }
    }

    public boolean removeLower(Float health){
        if(collection.equals(collection.stream()
                .filter(spaceMarine -> spaceMarine.getHealth() > health)
                .collect(Collectors.toSet()))) return false;
        else {
            collection = collection.stream()
                    .filter(spaceMarine -> spaceMarine.getHealth() > health)
                    .collect(Collectors.toSet());
            return true;
        }
    }

    public String show(){
        return this.collection
                .stream()
                .map(SpaceMarine::toString)
                .collect(Collectors.joining("\n"));
    }

    public boolean update(SpaceMarine spaceMarine){
        if(!collection.stream().filter(spaceMarine1 -> Objects.equals(spaceMarine1.getId(), spaceMarine.getId())).map(spaceMarine1 -> spaceMarine1 = spaceMarine).collect(Collectors.toSet()).isEmpty()){
            collection = collection.stream().filter(spaceMarine1 -> Objects.equals(spaceMarine1.getId(), spaceMarine.getId())).map(spaceMarine1 -> spaceMarine1 = spaceMarine).collect(Collectors.toSet());
            return true;
        }
        else return false;
    }

    public void save(File file){
        try{
            StaxXMLWriter staxXMLWriter = new StaxXMLWriter(new PrintWriter(file));
            staxXMLWriter.writeElement(this);
        } catch (XMLStreamException e) {
            System.out.println(e.toString());
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        }
    }




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

        return new SpaceMarine(name, coordinates, health, loyal, achievements, category, chapter);

    }



    public Set<SpaceMarine> getCollection(){
        return collection;
    }

}

