package com.company;

import com.company.items.Item;

import java.util.List;

public class PersistentInventory implements GrindInventory {

    private GrindInventory grindInventory;

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
    public void add(Item item) {
        grindInventory.add(item);
    }

    @Override
    public Item take(int slot) {
        return grindInventory.take(slot);
    }
}
