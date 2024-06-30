package ru.Timur.Command;

import ru.Timur.*;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

/**
 * Класс хранилища коллекции и ресивер
 * @author timur
 */
public class Storage implements Serializable {

    private static final Storage storage = new Storage();
    private static final Date initializationDate = new Date();
    static final long serialVersionUID = 1L;
    private CopyOnWriteArraySet<SpaceMarine> collection = new CopyOnWriteArraySet<>();
    private Connection connection;

    private Storage(){
        connection = DataBaseManager.getDataBaseManager().getConnection();
        initializationCollection();
    }

    public static Storage getStorage() {
        return storage;
    }

    public boolean add(SpaceMarine spaceMarine){
        try {
            Long id;
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO SpaceMarine(Name, UserName, CoordinatesX, CoordinatesY, CreationDate, Health, Loyal, Achievements, Category, ChapterName, ChapterMarinesCount) VALUES (?, ?, ?, ?, NOW(), ?, ?, ?, ?::category, ?, ?);");
            preparedStatement.setString(1, spaceMarine.getName());
            preparedStatement.setInt(3, spaceMarine.getCoordinates().getX());
            preparedStatement.setDouble(4, spaceMarine.getCoordinates().getY());
            preparedStatement.setDouble(5, spaceMarine.getHealth());
            preparedStatement.setBoolean(6, spaceMarine.isLoyal());
            preparedStatement.setString(7, spaceMarine.getAchievements());
            preparedStatement.setString(8, spaceMarine.getCategory().toString());
            preparedStatement.setString(9, spaceMarine.getChapter().getName());
            preparedStatement.setLong(10, spaceMarine.getChapter().getMarinesCount());
            preparedStatement.setString(2, spaceMarine.getUser());
            preparedStatement.execute();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT last_value FROM spacemarine_id_seq;");
            resultSet.next();
            id = resultSet.getLong(1);

            spaceMarine.setId(id);
            collection.add(spaceMarine);
            return true;
        } catch (SQLException e){
            System.out.println(e.toString());
            return false;
        }
    }


    public boolean addIfMin(SpaceMarine spaceMarine){
        Optional<SpaceMarine> optionalSpaceMarine = collection.parallelStream().min(Comparator.comparing(SpaceMarine::getHealth));
        if(spaceMarine.getHealth() < optionalSpaceMarine.get().getHealth()){
            return add(spaceMarine);
        }
        return false;
    }

    public Float averageOfHealth(){
        try {
            ResultSet resultSet = DataBaseManager.readAll();
            ArrayList<Double> result = new ArrayList<>();
            while (resultSet.next()){
                result.add(resultSet.getDouble("health"));
            }
            OptionalDouble average = result.stream().mapToDouble(x -> x).average();
            return (float) average.getAsDouble();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void clear(User user){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from spacemarine where username = ?;");
            preparedStatement.setString(1, user.getName());
            preparedStatement.execute();
            collection = collection.stream()
                    .filter(spaceMarine -> !spaceMarine.getUser().equals(user.getName()))
                    .collect(Collectors.toCollection(CopyOnWriteArraySet::new));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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
        return collection.parallelStream()
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

    public boolean removeById(Long id, User user){
        try {
            if (collection.stream()
                    .filter(spaceMarine -> Objects.equals(spaceMarine.getId(), id) && spaceMarine.getUser().equals(user.getName()))
                    .count() > 0) {
                PreparedStatement preparedStatement = connection.prepareStatement("delete from spacemarine where id = ?;");
                preparedStatement.setLong(1, id);
                preparedStatement.execute();
                collection = collection.stream()
                        .filter(spaceMarine -> !Objects.equals(spaceMarine.getId(), id))
                        .collect(Collectors.toCollection(CopyOnWriteArraySet::new));
                return true;

            } else return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean removeGreater(Float health, User user){
        try{
            if(collection.stream()
                    .anyMatch(spaceMarine -> spaceMarine.getUser().equals(user.getName()) || spaceMarine.getHealth() > health)){

                collection = collection.stream()
                        .filter(spaceMarine -> !spaceMarine.getUser().equals(user.getName()) || spaceMarine.getHealth() < health)
                        .collect(Collectors.toCollection(CopyOnWriteArraySet::new));
                PreparedStatement preparedStatement = connection.prepareStatement("delete from spacemarine where health > ? and username = ?;");
                preparedStatement.setFloat(1, health);
                preparedStatement.setString(2, user.getName());
                preparedStatement.execute();
                return true;
            }
            else return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean removeLower(Float health, User user){
        try{

            if(collection.stream().anyMatch(spaceMarine -> spaceMarine.getUser().equals(user.getName()) || spaceMarine.getHealth() < health)){
                collection = collection.stream()
                        .filter(spaceMarine -> !spaceMarine.getUser().equals(user.getName()) || spaceMarine.getHealth() > health)
                        .collect(Collectors.toCollection(CopyOnWriteArraySet::new));
                PreparedStatement preparedStatement = connection.prepareStatement("delete from spacemarine where health < ? and username = ?;");
                preparedStatement.setFloat(1, health);
                preparedStatement.setString(2, user.getName());
                preparedStatement.execute();
                return true;
            }
            else return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String show(){
        return this.collection.stream()
                .map(SpaceMarine::toString)
                .collect(Collectors.joining("\n"));
    }

    public boolean update(SpaceMarine spaceMarine, User user){
        try {
            if (collection.stream().anyMatch(spaceMarine1 -> Objects.equals(spaceMarine1.getId(), spaceMarine.getId()) && spaceMarine1.getUser().equals(user.getName()))) {
                PreparedStatement preparedStatement = connection.prepareStatement("update spacemarine set name = ?, coordinatesx = ?, coordinatesy = ?, health = ? ,loyal = ? ,achievements = ? ,category = ?::category ,chaptername = ? , chaptermarinescount = ? where id = ?; ");
                preparedStatement.setString(1, spaceMarine.getName());
                preparedStatement.setInt(2, spaceMarine.getCoordinates().getX());
                preparedStatement.setDouble(3, spaceMarine.getCoordinates().getY());
                preparedStatement.setDouble(4,spaceMarine.getHealth());
                preparedStatement.setBoolean(5,spaceMarine.isLoyal());
                preparedStatement.setString(6,spaceMarine.getAchievements());
                preparedStatement.setString(7,spaceMarine.getCategory().toString());
                preparedStatement.setString(8,spaceMarine.getChapter().getName());
                preparedStatement.setLong(9,spaceMarine.getChapter().getMarinesCount());
                preparedStatement.setLong(10,spaceMarine.getId());


                preparedStatement.execute();
                collection = collection.stream()
                        .map(spaceMarine1 -> {
                            if(spaceMarine1.getId().equals(spaceMarine.getId())){
                                return spaceMarine;
                            }
                            else return spaceMarine1;
                        })
                        .collect(Collectors.toCollection(CopyOnWriteArraySet::new));
                return true;

            } else return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(File file){
    }
    public Set<SpaceMarine> getCollection(){
        return collection;
    }


    public void initializationCollection(){
        try {
            Statement preparedStatement = connection.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery("select * from spacemarine;");
            while(resultSet.next()){
                collection.add(creteElemFromDataBase(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e){
        }

    }

    public SpaceMarine creteElemFromDataBase(ResultSet resultSet){
        try {
            SpaceMarine spaceMarine = new SpaceMarine(
                resultSet.getString("name"),
                new Coordinates(resultSet.getInt("coordinatesx"), resultSet.getDouble("coordinatesy")),
                resultSet.getFloat("health"),
                resultSet.getBoolean("loyal"),
                resultSet.getString("achievements"),
                AstartesCategory.valueOf(resultSet.getString("category")),
                new Chapter(resultSet.getString("chaptername"), resultSet.getLong("chaptermarinescount")),
                resultSet.getString("username")
            );
            spaceMarine.setId(resultSet.getLong("id"));
            return spaceMarine;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

