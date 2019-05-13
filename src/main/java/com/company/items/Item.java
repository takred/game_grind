package com.company.items;

public class Item {
//
    public static final int HEAD = 0;
    public static final int TORSO = 1;
    public static final int LEGS = 2;
    public static final int WEAPON = 3;

    public String name;
    public int plusMinStr;
    public int plusMaxStr;
    public int plusMaxHp;
    public int category;
    public Item(String name, int plusMinStr, int plusMaxStr, int category){
        this.name = name;
        this.plusMinStr = plusMinStr;
        this.plusMaxStr = plusMaxStr;
        this.category = category;
    }
    public Item(String name, int plusMaxHp, int category){
        this.name = name;
        this.plusMaxHp = plusMaxHp;
        this.category = category;
    }
}

