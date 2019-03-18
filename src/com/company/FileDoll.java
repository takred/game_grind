package com.company;

import com.company.Doll;
import com.company.Item;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileDoll implements Doll {
    String nameHero;
    private List<Item> equippedItem = new ArrayList<>();

    public FileDoll(){
        equippedItem.add(null);
        equippedItem.add(null);
        equippedItem.add(null);
        equippedItem.add(null);
    }
    @Override
    public List<Item> items(){
        return Collections.unmodifiableList(equippedItem);
    }
    @Override
    public void putOn(Item item) throws FileNotFoundException {
        equippedItem.set(item.category, item);
        OutputStream outputStream = new FileOutputStream("FileDoll");
        PrintWriter writer = new PrintWriter(outputStream);
        List<String> categoryList = new ArrayList<>();
        categoryList.add("Голова");
        categoryList.add("Туловище");
        categoryList.add("Ноги");
        categoryList.add("Оружие");
        for (int a = 0; a < equippedItem.size(); a++) {
            if (equippedItem.get(a) != null) {
                writer.println(a + 1 + ") " + categoryList.get(a) + " - " + equippedItem.get(a).name);
            } else {
                writer.println(a + 1 + ") " + categoryList.get(a) + " - " + "Ничего не надето.");
            }
        }
        writer.close();
    }
    @Override
    public Item takeOff(int category) throws FileNotFoundException {
        Item item = equippedItem.get(category);
        equippedItem.set(category, null);
        OutputStream outputStream = new FileOutputStream("FileDoll");
        PrintWriter writer = new PrintWriter(outputStream);
        List<String> categoryList = new ArrayList<>();
        categoryList.add("Голова");
        categoryList.add("Туловище");
        categoryList.add("Ноги");
        categoryList.add("Оружие");
        for (int a = 0; a < equippedItem.size(); a++) {
            if (equippedItem.get(a) != null) {
                writer.println(a + 1 + ") " + categoryList.get(a) + " - " + equippedItem.get(a).name);
            } else {
                writer.println(a + 1 + ") " + categoryList.get(a) + " - " + "Ничего не надето.");
            }
        }
        writer.close();
        return item;
    }
    @Override
    public boolean isOn(int category) {
        return equippedItem.get(category) == null;
    }
}
