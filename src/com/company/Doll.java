package com.company;

import java.io.FileNotFoundException;
import java.util.List;

public interface Doll {
    List<Item> items();
    void putOn(Item item) throws FileNotFoundException;
    Item takeOff(int category) throws FileNotFoundException;
    boolean isOn(int category);
}