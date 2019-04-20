package com.company;

import com.company.doll.Doll;
import com.company.items.Item;

import java.util.concurrent.ThreadLocalRandom;

public class EquipedCharacter {
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
}
