package com.company.doll;

import com.company.items.Item;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

public class ChangelogDoll implements Doll {
    String nameHero;
    Doll doll;

    public ChangelogDoll(Doll doll){
        this.doll = doll;
    }
    @Override
    public List<Item> items(){
        return doll.items();
    }
    @Override
    public void putOn(Item item) throws FileNotFoundException {
        doll.putOn(item);
        OutputStream outputStream = new FileOutputStream("ChangelogDoll", true);
        PrintWriter writer = new PrintWriter(outputStream);
        writer.println("Экипирован предмет : " + item.name);
        writer.close();
    }
    @Override
    public Item takeOff(int category) throws FileNotFoundException {
        Item item = doll.takeOff(category);
        OutputStream outputStream = new FileOutputStream("ChangelogDoll", true);
        PrintWriter writer = new PrintWriter(outputStream);
        writer.println("Снят предмет : " + item.name);
        writer.close();
        return item;
    }
    @Override
    public boolean isOn(int category) {
        return doll.isOn(category);
    }
}
