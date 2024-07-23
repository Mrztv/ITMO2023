package ru.Timur.Command;

import ru.Timur.SpaceMarine;
import ru.Timur.User;

import java.sql.*;

public class DataBaseManager {
    private static final DataBaseManager dataBaseManager = new DataBaseManager();
    private static Connection connection;

    private DataBaseManager() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/studs", "s409145", "IcZHJoli85q0w5VV");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static DataBaseManager getDataBaseManager() {
        return dataBaseManager;
    }

    public ResultSet executeQuery(String SQLQuery) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery);
            return preparedStatement.executeQuery();
        } catch (SQLException e){
            System.out.println(e.toString());
        }

        return null;
    }

    public boolean put(User user){
        String U0 = "\u0000";
        try {
            PreparedStatement statement = connection.prepareStatement("insert into users(login, salt, hash) VALUES (?, ?, ?);");
            statement.setString(1, user.getName().replaceAll(U0, ""));
            statement.setString(2, user.getSalt().replaceAll(U0, ""));
            statement.setString(3, user.getHash().replaceAll(U0, ""));
            statement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static ResultSet readAll(){
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery("SELECT * FROM SpaceMarine;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}