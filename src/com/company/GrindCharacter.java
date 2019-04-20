package com.company;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GrindCharacter {
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

    public GrindCharacter(String name, int maxHp, int hp, int minStr, int maxStr, int lvl, int nextLvl, int exp, boolean perkDoubleAttack){
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

    public GrindCharacter(String name, int maxHp, int hp, int minStr, int maxStr, int exp, List<WeightDrop> itemDrop){
        this.name = name;
        this.maxHp = maxHp;
        this.hp = hp;
        this.minStr = minStr;
        this.maxStr = maxStr;
        this.exp = exp;
        this.itemDrop = itemDrop;
    }

    public GrindCharacter(){}

    public int currentDamage() {
        if (minStr != maxStr) {
            return ThreadLocalRandom.current().nextInt(minStr, maxStr);
        }
        return minStr;
    }
}
