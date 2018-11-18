package com.company;

public class Character {
    String name;
    int maxHp;
    int hp;
    int minStr;
    int maxStr;
    int nextLvl;
    int exp;
    public Character(String name, int maxHp, int hp, int minStr, int maxStr, int nextLvl, int exp){
        this.name = name;
        this.maxHp = maxHp;
        this.hp = hp;
        this.minStr = minStr;
        this.maxStr = maxStr;
        this.nextLvl = nextLvl;
        this.exp = exp;
    }
    public Character(String name, int maxHp, int hp, int minStr, int maxStr, int exp){
        this.name = name;
        this.maxHp = maxHp;
        this.hp = hp;
        this.minStr = minStr;
        this.maxStr = maxStr;
        this.exp = exp;
    }
    public Character(){}
}
