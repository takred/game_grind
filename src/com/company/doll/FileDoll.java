package com.company.doll;

import com.company.items.Item;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileDoll implements Doll {
    String nameHero;
    Doll doll;
    private List<Item> equippedItem = new ArrayList<>();

    public FileDoll(Doll doll){
        this.doll = doll;
    }
    @Override
    public List<Item> items(){
        return doll.items();
    }
    @Override
    public void putOn(Item item) throws FileNotFoundException {
        doll.putOn(item);
        OutputStream outputStream = new FileOutputStream("FileDoll");
        PrintWriter writer = new PrintWriter(outputStream);
        List<String> categoryList = new ArrayList<>();
        categoryList.add("Голова");
        categoryList.add("Туловище");
        categoryList.add("Ноги");
        categoryList.add("Оружие");
        for (int a = 0; a < doll.items().size(); a++) {
            if (doll.items().get(a) != null) {
                writer.println(a + 1 + ") " + categoryList.get(a) + " - " + doll.items().get(a).name);
            } else {
                writer.println(a + 1 + ") " + categoryList.get(a) + " - " + "Ничего не надето.");
            }
        }
        writer.close();
    }
    @Override
    public Item takeOff(int category) throws FileNotFoundException {
        Item item = doll.takeOff(category);
        OutputStream outputStream = new FileOutputStream("FileDoll");
        PrintWriter writer = new PrintWriter(outputStream);
        List<String> categoryList = new ArrayList<>();
        categoryList.add("Голова");
        categoryList.add("Туловище");
        categoryList.add("Ноги");
        categoryList.add("Оружие");
        for (int a = 0; a < doll.items().size(); a++) {
            if (doll.items().get(a) != null) {
                writer.println(a + 1 + ") " + categoryList.get(a) + " - " + doll.items().get(a).name);
            } else {
                writer.println(a + 1 + ") " + categoryList.get(a) + " - " + "Ничего не надето.");
            }
        }
        writer.close();
        return item;
    }
    @Override
    public boolean isOn(int category) {
        return doll.isOn(category);
    }
}
