package com.company.character;

import com.company.WeightDrop;
import com.company.character.GrindCharacter;

import java.io.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

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

    public NakedGrindCharacter(String fileName) throws IOException {
        InputStream inputStream = new FileInputStream(fileName);
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        List<String> strings = bufferedReader.lines().collect(Collectors.toList());
        inputStream.close();
        String string = strings.get(0);
        int startIndName = string.indexOf("\"");
        int endIndName = string.indexOf("\"", startIndName + 1);
        int borderMaxHp = string.indexOf(",", endIndName + 2);
        int borderHp = string.indexOf(",", borderMaxHp + 1);
        int borderMinStr = string.indexOf(",", borderHp + 1);
        int borderMaxStr = string.indexOf(",", borderMinStr + 1);
        int borderLvl = string.indexOf(",", borderMaxStr + 1);
        int borderExpNextLvl = string.indexOf(",", borderLvl + 1);
        int borderExp = string.indexOf(",", borderExpNextLvl + 1);
        this.name = string.substring(startIndName + 1, endIndName);
        maxHp = Integer.valueOf(string.substring(endIndName + 2, borderMaxHp));
        hp = Integer.valueOf(string.substring(borderMaxHp + 1, borderHp));
        minStr = Integer.valueOf(string.substring(borderHp + 1, borderMinStr));
        maxStr = Integer.valueOf(string.substring(borderMinStr + 1, borderMaxStr));
        lvl = Integer.valueOf(string.substring(borderMaxStr + 1, borderLvl));
        nextLvl = Integer.valueOf(string.substring(borderLvl + 1, borderExpNextLvl));
        exp = Integer.valueOf(string.substring(borderExpNextLvl + 1, borderExp));
        perkDoubleAttack = Boolean.valueOf(string.substring(borderExp + 1));
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

    @Override
    public void writeInFile() throws IOException {
    }
}
