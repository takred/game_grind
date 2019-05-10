package com.company.doll;

import com.company.items.Item;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventoryDoll implements Doll {
    String nameHero;
    private List<Item> equippedItem = new ArrayList<>();

    public InventoryDoll(){
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
    public void putOn(Item item){
        equippedItem.set(item.category, item);
    }
    @Override
    public Item takeOff(int category) {
        Item item = equippedItem.get(category);
        equippedItem.set(category, null);
        return item;
    }
    @Override
    public boolean isOn(int category) {
        return equippedItem.get(category) == null;
    }

    @Override
    public void writeInFile(String saveName) {
    }
}
