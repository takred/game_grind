package com.company;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Character {
    String name;
    int maxHp;
    int hp;
    int minStr;
    int maxStr;
    int lvl;
    int nextLvl;
    int exp;
    boolean perkDoubleAttack;
    List<WeightDrop> itemDrop;

    public Character(String name, int maxHp, int hp, int minStr, int maxStr, int lvl, int nextLvl, int exp, boolean perkDoubleAttack){
        this.name = name;
        this.maxHp = maxHp;
        this.hp = hp;
        this.minStr = minStr;
        this.maxStr = maxStr;
        this.lvl = lvl;
        this.nextLvl = nextLvl;
        this.exp = exp;
        this.perkDoubleAttack = perkDoubleAttack;
    }

    public Character(String name, int maxHp, int hp, int minStr, int maxStr, int exp, List<WeightDrop> itemDrop){
        this.name = name;
        this.maxHp = maxHp;
        this.hp = hp;
        this.minStr = minStr;
        this.maxStr = maxStr;
        this.exp = exp;
        this.itemDrop = itemDrop;
    }

    public Character(){}

    public int currentDamage(int minStr, int maxStr) {
        if (minStr != maxStr) {
            return ThreadLocalRandom.current().nextInt(minStr, maxStr);
        }
        return minStr;
    }
}
