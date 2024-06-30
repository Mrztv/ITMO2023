package ru.Timur;

import ru.Timur.Command.DataBaseManager;
import ru.Timur.Exceptions.NonValidFileElementException;
import ru.Timur.Exceptions.WrongPasswordExeption;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;


public class Auth{


    public boolean inUsers(String name, String password) throws WrongPasswordExeption {
        if(name == null || password == null) throw new NonValidFileElementException("name or password");
        try {
            String _salt = null;
            String  _hash = null;
            DataBaseManager dataBaseManager = DataBaseManager.getDataBaseManager();

            ResultSet resultSet = dataBaseManager.executeQuery("SELECT * FROM Users;");
            while (resultSet.next()) {
                if (name.equals(resultSet.getArray("Login").toString())) {
                    _hash = resultSet.getString("hash");
                    _salt = resultSet.getString("salt");
                    break;
                }
            }
            if(_salt == null){
                return false;
            }

            MessageDigest messageDigest = MessageDigest.getInstance("SHA-224");
            byte[] hash = messageDigest.digest((password + _salt + "s132$##ASDASF#").getBytes());
            BigInteger bigInteger = new BigInteger(1, hash);
            String sHash = bigInteger.toString(16);

            if(sHash.equals(_hash)){
                return true;
            }
            else throw new WrongPasswordExeption();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean createNewUser(String name, String password) {
        DataBaseManager dataBaseManager = DataBaseManager.getDataBaseManager();
        return dataBaseManager.put(new User(name, password));
    }


}