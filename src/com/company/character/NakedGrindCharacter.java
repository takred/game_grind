package com.company.character;

import com.company.WeightDrop;
import com.company.character.GrindCharacter;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class NakedGrindCharacter implements GrindCharacter {
    private String name;
    private int maxHp;
    private int hp;
    private int minStr;
    private int maxStr;
    private int lvl;
    private int nextLvl;
    private int exp;
    private boolean perkDoubleAttack;
    private List<WeightDrop> itemDrop;

    public NakedGrindCharacter(String name, int maxHp, int hp, int minStr, int maxStr, int lvl, int nextLvl, int exp, boolean perkDoubleAttack){
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

    public NakedGrindCharacter(String name, int maxHp, int hp, int minStr, int maxStr, int exp, List<WeightDrop> itemDrop){
        this.name = name;
        this.maxHp = maxHp;
        this.hp = hp;
        this.minStr = minStr;
        this.maxStr = maxStr;
        this.exp = exp;
        this.itemDrop = itemDrop;
    }

    public NakedGrindCharacter(){}

    public int currentDamage() {
        if (minStr != maxStr) {
            return ThreadLocalRandom.current().nextInt(minStr, maxStr);
        }
        return minStr;
    }

    public String name() {
        return name;
    }

    public boolean perkDoubleAttack() {
        return perkDoubleAttack;
    }

    public void enableDoubleAttack() {
        perkDoubleAttack = true;
    }

    public int maxHp() {
        return maxHp;
    }

    public void increaseMaxHp(int plusMaxHp) {
        maxHp = maxHp + plusMaxHp;
    }

    public int hp(){
        return hp;
    }

    public void increaseHp(int restoreHp){
            hp = hp + restoreHp;
    }

    public void decreaseHp(int damageHp){
        hp = hp - damageHp;
    }

    public int minStr() {
        return minStr;
    }

    public void increaseMinStr(int plusMinStr) {
        minStr = minStr + plusMinStr;
    }

    public int maxStr() {
        return maxStr;
    }

    public void increaseMaxStr(int plusMaxStr) {
        maxStr = maxStr + plusMaxStr;
    }

    public int lvl() {
        return lvl;
    }

    public void increaseLvl() {
        lvl = lvl + 1;
    }

    public int nextLvl() {
        return nextLvl;
    }

    public void increaseNextLvl() {
        nextLvl = nextLvl * 2;
    }

    public int exp() {
        return exp;
    }

    public void increaseExp(int plusExp){
        exp = exp + plusExp;
    }

    public void decreaseExp(int minusExp) {
        exp = exp - minusExp;
    }

    public List<WeightDrop> itemDrop() {
        return itemDrop;
    }
}
