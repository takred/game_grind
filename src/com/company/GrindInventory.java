package com.company;

import com.company.items.Item;

import java.util.List;

public interface GrindInventory {

    String nameHero();

    void nameHeroAdd(String name);

    List<Item> items();

    void add(Item item);

    Item take(int slot);
}
