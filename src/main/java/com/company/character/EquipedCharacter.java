package com.company.character;

import com.company.WeightDrop;
import com.company.character.GrindCharacter;
import com.company.doll.Doll;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class EquipedCharacter implements GrindCharacter {
    private final Doll equipInvHero;
    private final GrindCharacter hero;

    public EquipedCharacter(Doll equipInvHero, GrindCharacter hero){
        this.equipInvHero = equipInvHero;
        this.hero = hero;
    }



    public int currentDamage(){
        return ThreadLocalRandom.current().nextInt(minStr(), maxStr());
    }

    @Override
    public String name() {
        return hero.name();
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
        int maxHp = hero.maxHp();
        for (int i = 0; i < equipInvHero.items().size(); i++) {
            if (equipInvHero.items().get(i) != null && equipInvHero.items().get(i).plusMaxHp > 0){
                maxHp = maxHp + equipInvHero.items().get(i).plusMaxHp;
            }
        }
        return maxHp;
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
        int minStr = hero.minStr();
        for (int i = 0; i < equipInvHero.items().size(); i++) {
            if (equipInvHero.items().get(i) != null && equipInvHero.items().get(i).plusMinStr > 0){
                minStr = minStr + equipInvHero.items().get(i).plusMinStr;
            }
        }
        return minStr;
    }

    @Override
    public void increaseMinStr(int plusMinStr) {
        hero.increaseMinStr(plusMinStr);
    }

    @Override
    public int maxStr() {
        int maxStr = hero.maxStr();
        for (int i = 0; i < equipInvHero.items().size(); i++) {
            if (equipInvHero.items().get(i) != null && equipInvHero.items().get(i).plusMaxStr > 0){
                maxStr = maxStr + equipInvHero.items().get(i).plusMaxStr;
            }
        }
        return maxStr;
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
        return itemDrop();
    }

    @Override
    public void writeInFile(String saveName) {}

}
