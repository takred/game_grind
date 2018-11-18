package com.company;

public class Character {
    String name;
    int maxHp;
    int hp;
    int str;
    int nextLvl;
    int exp;
    public Character(String name,int maxHp, int hp, int str, int nextLvl, int exp){
        this.name = name;
        this.maxHp = maxHp;
        this.hp = hp;
        this.str = str;
        this.nextLvl = nextLvl;
        this.exp = exp;
    }
    public Character(String name, int maxHp, int hp, int str, int exp){
        this.name = name;
        this.maxHp = maxHp;
        this.hp = hp;
        this.str = str;
        this.exp = exp;
    }
    public Character(){}
}
