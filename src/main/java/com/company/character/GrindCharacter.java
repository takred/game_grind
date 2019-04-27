package com.company.character;

import com.company.WeightDrop;

import java.util.List;

public interface GrindCharacter {
    String name();

    int currentDamage();

    boolean perkDoubleAttack();

    void enableDoubleAttack();

    int maxHp();

    void increaseMaxHp(int plusMaxHp);

    int hp();

    void increaseHp(int restoreHp);

    void decreaseHp(int damageHp);

    int minStr();

    void increaseMinStr(int plusMinStr);

    int maxStr();

    void increaseMaxStr(int plusMaxStr);

    int lvl();

    void increaseLvl();

    int nextLvl();

    void increaseNextLvl();

    int exp();

    void increaseExp(int plusExp);

    void decreaseExp(int minusExp);

    List<WeightDrop> itemDrop();
}
