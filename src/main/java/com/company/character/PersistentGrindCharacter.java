package com.company.character;

import com.company.WeightDrop;

import java.io.*;
import java.util.List;

public class PersistentGrindCharacter implements GrindCharacter {

    private GrindCharacter hero;

    public PersistentGrindCharacter(GrindCharacter hero){
        this.hero = hero;
    }

    @Override
    public String name() {
        return hero.name();
    }

    @Override
    public int currentDamage() {
        return hero.currentDamage();
    }

    @Override
    public boolean perkDoubleAttack() {
        return hero.perkDoubleAttack();
    }

    @Override
    public void enableDoubleAttack() {
        hero.enableDoubleAttack();
    }

    @Override
    public int maxHp() {
        return hero.maxHp();
    }

    @Override
    public void increaseMaxHp(int plusMaxHp) {
        hero.increaseMaxHp(plusMaxHp);
    }

    @Override
    public int hp() {
        return hero.hp();
    }

    @Override
    public void increaseHp(int restoreHp) {
        hero.increaseHp(restoreHp);
    }

    @Override
    public void decreaseHp(int damageHp) {
        hero.decreaseHp(damageHp);
    }

    @Override
    public int minStr() {
        return hero.minStr();
    }

    @Override
    public void increaseMinStr(int plusMinStr) {
        hero.increaseMinStr(plusMinStr);
    }

    @Override
    public int maxStr() {
        return hero.maxStr();
    }

    @Override
    public void increaseMaxStr(int plusMaxStr) {
        hero.increaseMaxStr(plusMaxStr);
    }

    @Override
    public int lvl() {
        return hero.lvl();
    }

    @Override
    public void increaseLvl() {
        hero.increaseLvl();
    }

    @Override
    public int nextLvl() {
        return hero.nextLvl();
    }

    @Override
    public void increaseNextLvl() {
        hero.increaseNextLvl();
    }

    @Override
    public int exp() {
        return hero.exp();
    }

    @Override
    public void increaseExp(int plusExp) {
        hero.increaseExp(plusExp);
    }

    @Override
    public void decreaseExp(int minusExp) {
        hero.decreaseExp(minusExp);
    }

    @Override
    public List<WeightDrop> itemDrop() {
        return hero.itemDrop();
    }

    public void writeInFile() throws IOException {
        OutputStream outputStream = new  FileOutputStream("CharacterGarm.txt");
        PrintWriter writer = new PrintWriter(outputStream);
        writer.println("\"" +  hero.name() + "\"," + hero.maxHp() + "," + hero.hp() + "," + hero.minStr() +
                "," + hero.maxStr() + "," + hero.lvl() + "," + hero.nextLvl() + "," + hero.exp() + "," + hero.perkDoubleAttack());
        writer.close();
    }
}
