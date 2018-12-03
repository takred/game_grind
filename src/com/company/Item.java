package com.company;

public class Item {
    String name;
    int plusMinStr;
    int plusMaxStr;
    int plusMaxHp;
    String category;
    public Item(String name, int plusMinStr, int plusMaxStr, String category){
        this.name = name;
        this.plusMinStr = plusMinStr;
        this.plusMaxStr = plusMaxStr;
        this.category = category;
    }
    public Item(String name, int plusMaxHp, String category){
        this.name = name;
        this.plusMaxHp = plusMaxHp;
        this.category = category;
    }
}
