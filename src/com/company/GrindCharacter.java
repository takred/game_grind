package com.company;

public interface GrindCharacter {
    int currentDamage();

    boolean perkDoubleAttack();

    void enableDoubleAttack();

    int maxHp();

    void increaseMaxHp(int plusMaxHp);

    int hp();

    void increaseHp(int restoreHp);

    void restoreMaxHp();

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
}
