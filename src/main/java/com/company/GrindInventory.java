package com.company;

import com.company.items.Item;

import java.io.FileNotFoundException;
import java.util.List;

public interface GrindInventory {

    String nameHero();

    void nameHeroAdd(String name);

    List<Item> items();

    void add(Item item) throws FileNotFoundException;

    Item take(int slot) throws FileNotFoundException;

    void writeInFile(String saveName) throws FileNotFoundException;
}
