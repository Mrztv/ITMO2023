//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ru.Timur.Command;

import java.io.Serializable;

public interface Command extends Serializable {
  long serialVersionUID = 1L;

  boolean execute();

  void getDiscription();
}
