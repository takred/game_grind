package com.company;

import com.company.items.Item;

import java.io.*;
import java.util.List;

public class PersistentInventory implements GrindInventory {

    private GrindInventory grindInventory;

    public PersistentInventory(GrindInventory grindInventory){
        this.grindInventory = grindInventory;
    }

    @Override
    public String nameHero() {
        return grindInventory.nameHero();
    }

    @Override
    public void nameHeroAdd(String name) {
        grindInventory.nameHeroAdd(name);
    }

    @Override
    public List<Item> items() {
        return grindInventory.items();
    }

    @Override
    public void add(Item item) throws FileNotFoundException {
        grindInventory.add(item);
        writeInFile();
    }

    @Override
    public Item take(int slot) throws FileNotFoundException {
        Item itemInSlot = grindInventory.take(slot);
        writeInFile();
        return itemInSlot;
    }
    public void writeInFile() throws FileNotFoundException {
        OutputStream outputStream = new FileOutputStream("Inventory.txt");
        PrintWriter printWriter = new PrintWriter(outputStream);
        printWriter.println(grindInventory.nameHero());
        for (int i = 0; i < grindInventory.items().size(); i++) {
            Item currentItem = grindInventory.items().get(i);
            if (currentItem.category != 3) {
                printWriter.println(currentItem.category + "," + "\"" + currentItem.name + "\"," + currentItem.plusMaxHp);
            }else {
                printWriter.println(currentItem.category + "," + "\"" + currentItem.name + "\"," + currentItem.plusMinStr + "," + currentItem.plusMaxStr);
            }
        }
        printWriter.close();
    }
}
