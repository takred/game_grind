package com.company;

import com.company.doll.Doll;
import com.company.items.Item;

import java.util.concurrent.ThreadLocalRandom;

public class EquipedCharacter implements GrindCharacter{
    private final Doll equipInvHero;
    private final GrindCharacter hero;

    public EquipedCharacter(Doll equipInvHero, GrindCharacter hero){
        this.equipInvHero = equipInvHero;
        this.hero = hero;
    }

    public int currentDamage(){
        Item weapon = equipInvHero.items().get(3);
        if (hero.minStr() != hero.maxStr() && weapon != null) {
            return ThreadLocalRandom.current().nextInt(hero.minStr() + weapon.plusMinStr,
                    hero.maxStr() + weapon.plusMaxStr);
        }
        return ThreadLocalRandom.current().nextInt(hero.minStr(), hero.maxStr());
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
    public void restoreMaxHp() {
        hero.restoreMaxHp();
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
}
