package ru.Timur;

import java.io.Serializable;

public class User implements Serializable {
    private boolean set = false;
    private static final User user = new User();
    private static final long serialVersionUID = 1L;
    private String name;
    private String password;

    public void setUser(String login, String password){
        this.name = login;
        this.password = password;
        set = true;
    }

    public static User getUser() {
        return user;
    }

    public boolean isSet() {
        return set;
    }
}
