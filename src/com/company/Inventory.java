package com.company;

import java.util.List;

public class Inventory {
    String nameHero;
    List<Item> inv;
    public Inventory(String nameHero, List<Item> inv){
        this.nameHero = nameHero;
        this.inv = inv;
    }
}
