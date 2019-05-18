package com.company;

import com.company.items.Item;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Inventory implements GrindInventory {
    private String nameHero;
    private List<Item> inv;

    public Inventory(String nameHero){
        this.nameHero = nameHero;
        this.inv = new ArrayList<>();
    }

    public Inventory() {
        inv = new ArrayList<>();
    }

    @Override
    public String nameHero() {
        return nameHero;
    }

    @Override
    public void nameHeroAdd(String name){
        nameHero = name;
    }

    @Override
    public List<Item> items() {
        return Collections.unmodifiableList(inv);
    }

    @Override
    public void add(Item item){
        inv.add(item);
    }

    @Override
    public Item take(int slot){
        Item item = inv.get(slot);
        inv.remove(slot);
        return item;
    }

    @Override
    public void writeInFile(String saveName) throws FileNotFoundException {

    }
}

