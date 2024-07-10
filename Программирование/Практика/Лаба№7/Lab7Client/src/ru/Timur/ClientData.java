//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ru.Timur;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class ClientData implements Serializable {
    static final long serialVersionUID = 1L;
    private int variant;
    private Set<SpaceMarine> collection;
    private String out;
    private Number number;

    public ClientData() {
    }

    public void write() {
        if (this.variant == 1) {
            Set var10000 = this.collection;
            PrintStream var10001 = System.out;
            Objects.requireNonNull(var10001);
            var10000.forEach(var10001::println);
        } else if (this.variant == 2) {
            System.out.println(this.out);
        } else if (this.variant == 3) {
            System.out.println(this.number);
        } else {
            System.out.println("ERR");
        }

    }

    public String getOut() {
        return this.out;
    }
}
