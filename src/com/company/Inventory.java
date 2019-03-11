package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Inventory {
    String nameHero;
    private List<Item> inv;

    public Inventory(String nameHero){
        this.nameHero = nameHero;
        this.inv = new ArrayList<>();
    }

    public List<Item> items() {
        return Collections.unmodifiableList(inv);
    }

    public void add(Item item){
        inv.add(item);
    }

    public Item take(int slot){
        Item item = inv.get(slot);
        inv.remove(slot);
        return item;
    }
}

