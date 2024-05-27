package ru.Timur;

import org.postgresql.util.PSQLState;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String name;

    private final String password;
    private String salt;
    private String hash;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        encrypt(password);
    }
    public String getName() {
        return name;
    }

    public String getSalt() {
        return salt;
    }

    public String getHash() {
        return hash;
    }

    public String getPassword() {
        return password;
    }

    private void encrypt(String password){
        try {
            Random random = new Random();
            Date now = new Date();
            random.setSeed(now.getTime());
            byte[] saltArray = new byte[16];
            random.nextBytes(saltArray);
            salt = new String(saltArray, "UTF-8");
            salt.replaceAll("\u0000", "Q");

            MessageDigest messageDigest = MessageDigest.getInstance("SHA-224");
            hash = new BigInteger(1, messageDigest.digest((password + salt + "s132$##ASDASF#").getBytes())).toString(16);
            hash.replaceAll("\u0000", "Q");

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }


}
