//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ru.Timur.Exceptions;

public class NonValidFileElementException extends RuntimeException {
    public NonValidFileElementException(String reason) {
        super("Non valid " + reason);
    }
}
