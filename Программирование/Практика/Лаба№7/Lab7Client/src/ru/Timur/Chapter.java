//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ru.Timur;

import java.io.Serializable;

public class Chapter implements Serializable {
    static final long serialVersionUID = 1L;
    private String name;
    private Long marinesCount;

    public Chapter(String name, Long marinesCount) {
        this.name = name;
        this.marinesCount = marinesCount;
    }

    public String getName() {
        return this.name;
    }

    public Long getMarinesCount() {
        return this.marinesCount;
    }

    public String toString() {
        return "{name='" + this.name + "', marinesCount=" + this.marinesCount + "}";
    }
}
