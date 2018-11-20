package com.company;

public class Character {
    String name;
    int maxHp;
    int hp;
    int minStr;
    int maxStr;
    int lvl;
    int nextLvl;
    int exp;
    boolean perkDubleAttack;

    public Character(String name, int maxHp, int hp, int minStr, int maxStr, int lvl, int nextLvl, int exp, boolean perkDubleAttack){
        this.name = name;
        this.maxHp = maxHp;
        this.hp = hp;
        this.minStr = minStr;
        this.maxStr = maxStr;
        this.lvl = lvl;
        this.nextLvl = nextLvl;
        this.exp = exp;
        this.perkDubleAttack = perkDubleAttack;
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
