//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ru.Timur;

import java.io.Serializable;

public class User implements Serializable {
    private boolean set = false;
    private static final User user = new User();
    private static final long serialVersionUID = 1L;
    private String name;
    private String password;

    public User() {
    }

    public void setUser(String login, String password) {
        this.name = login;
        this.password = password;
        this.set = true;
    }

    public static User getUser() {
        return user;
    }

    public boolean isSet() {
        return this.set;
    }
}
