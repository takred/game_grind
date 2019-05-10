package com.company.doll;

import com.company.items.Item;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface Doll {
    List<Item> items();

    void putOn(Item item) throws FileNotFoundException;

    Item takeOff(int category) throws FileNotFoundException;

    boolean isOn(int category);

    void writeInFile(String saveName) throws IOException;
}