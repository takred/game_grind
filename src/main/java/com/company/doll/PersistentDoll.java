package com.company.doll;

import com.company.items.Item;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistentDoll implements Doll {
    String nameHero;
    Doll doll;
    private List<Item> equippedItem = new ArrayList<>();

    public PersistentDoll(Doll doll){
        this.doll = doll;
    }
    @Override
    public List<Item> items(){
        return doll.items();
    }
    @Override
    public void putOn(Item item) throws FileNotFoundException {
        doll.putOn(item);
//        writeInFile();
    }
    @Override
    public Item takeOff(int category) throws FileNotFoundException {
        Item item = doll.takeOff(category);
//        writeInFile();
        return item;
    }
    @Override
    public boolean isOn(int category) {
        return doll.isOn(category);
    }

    public void writeInFile(String saveName) throws IOException {
        OutputStream outputStream = new FileOutputStream("saves/" + saveName + "/PersistentDoll.txt");
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
}
