package com.company;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    static int fightResult(Character hero, Character monster) {
        for (int i = 0; i < 1000; i++) {
            boolean resultHeroAttack = heroAttack(hero, monster);
            if (resultHeroAttack) {
                return 0;
            }
            if(hero.perkDoubleAttack){
                System.out.println("Из-за особенности \"Двойной удар\", вы наносите ещё 1 удар.");
                resultHeroAttack = heroAttack(hero, monster);
                if (resultHeroAttack){
                    return 0;
                }
            }
            boolean resultMonsterAttack = monsterAttack(hero, monster);
            if (resultMonsterAttack){
                return 1;
            }
        }
        return 2;
    }

    static Character lvlUp(Character hero) {
        hero.lvl = hero.lvl + 1;
        if (hero.lvl % 4 == 0 && hero.lvl != 32) {
            System.out.println("Выберите усиление : 1 - увеличить максимальный запас здоровья на 20;" +
                    " 2 - увеличить минимальнй порог урона на 2; 3 - увеличить максимальный порог урона на 2;");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            if (choice == 1) {
                hero.maxHp = hero.maxHp + 20;
            } else if (choice == 2) {
                hero.minStr = hero.minStr + 2;
            } else if (choice == 3) {
                hero.maxStr = hero.maxStr + 2;
            }
        } else if (hero.lvl == 32) {
            System.out.println("Вы получили новую особенность - \"Двойной удар\"!");
            hero.perkDoubleAttack = true;
        }
        hero.maxHp = hero.maxHp + 10;
        hero.hp = hero.maxHp;
        hero.minStr = hero.minStr + 1;
        hero.maxStr = hero.maxStr + 1;
        hero.exp = hero.exp - hero.nextLvl;
        hero.nextLvl = hero.nextLvl * 2;
        return hero;
    }

    static Character recreation(Character hero){
        if (hero.maxHp - hero.hp < 20) {
            hero.hp = hero.maxHp;
        } else {
            hero.hp = hero.hp + 20;
        }
        System.out.println("Вы отдохнули.");
        return hero;
    }

    static int currentDamage(int minStr, int maxStr) {
        if (minStr != maxStr) {
            return ThreadLocalRandom.current().nextInt(minStr, maxStr);
        }
        return minStr;
    }

    static boolean heroAttack(Character hero, Character monster) {
        int currentDamageHero = currentDamage(hero.minStr, hero.maxStr);
        if (monster.hp - currentDamageHero > 0) {
            monster.hp = monster.hp - currentDamageHero;
            System.out.println("Вы нанесли " + monster.name + " " + currentDamageHero + " урона." + " У " + monster.name
                    + " осталось " + monster.hp + " здоровья.");
        } else {
            System.out.println("Вы нанесли " + monster.name + " " + currentDamageHero + " урона.");
            System.out.println("Вы убили " + monster.name + ".");
            hero.exp = hero.exp + monster.exp;
            monster.hp = monster.maxHp;
            System.out.println("Вы получили " + monster.exp + " опыта.");
            if (hero.exp >= hero.nextLvl) {
                System.out.println("Вы повысили уровень до " + (hero.lvl + 1) + "-го !");
                hero = lvlUp(hero);
            }
            System.out.println("У вас " + hero.exp + "/" + hero.nextLvl + " опыта.");
            return true;
        }
        return false;
    }
    static boolean monsterAttack(Character hero, Character monster){
        int currentDamageMonster = currentDamage(monster.minStr, monster.maxStr);
        if (hero.hp - currentDamageMonster > 0) {
            hero.hp = hero.hp - currentDamageMonster;
            System.out.println(monster.name + " нанёс вам " + currentDamageMonster + " урона." + " У вас осталось "
                    + hero.hp + " здоровья.");
        } else {
            hero.hp = hero.hp - currentDamageMonster;
            System.out.println(monster.name + " нанёс вам " + currentDamageMonster + " урона.");
            System.out.println("Вас убил " + monster.name + ".");
            return true;
        }
        return false;
    }
    static List<Character> fillingMonsterList(List<Character> allMonsters){
        Character character;
        character = new Character("Giant rat", 65, 65, 4, 7, 10);
        allMonsters.add(character);
        character = new Character("Goblin", 90, 90, 6, 9, 15);
        allMonsters.add(character);
        character = new Character("Familiar", 165, 165, 2, 6, 20);
        allMonsters.add(character);
        character = new Character("Wolf", 105, 105, 7, 11, 25);
        allMonsters.add(character);
        character = new Character("Ghoul", 150, 150, 5, 8, 30);
        allMonsters.add(character);
        character = new Character("Ghost", 130, 130, 10, 10, 35);
        allMonsters.add(character);
        return allMonsters;
    }

    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        Character hero = new Character("Garm", 100, 100, 8, 11, 1, 10, 0, false);
        List<Character> allMonsters = new ArrayList<>();
        allMonsters = fillingMonsterList(allMonsters);
        Map<String, Integer> countKill = new HashMap<>();
        for (int i = 0; i < allMonsters.size(); i++) {
            countKill.put(allMonsters.get(i).name, 0);
        }
        List<Item> allItems = new ArrayList<>();

        for (int i = 0; i < 1; )
            if (hero.hp > 0) {
                System.out.println("у вас " + hero.hp + " единиц здоровья.");
                System.out.println("Введите: 1 - напасть на монстра; 2 - отдохнуть(восстановить 20 здоровья); 3 - отступить.");
                int switcherMode = scanner.nextInt();
                if (switcherMode == 1) {
                    System.out.println("Выберите противника :");
                    for (int j = 0; j < allMonsters.size(); j++) {
                        System.out.println(j + 1 + " - " + allMonsters.get(j).name);
                    }
                    int switcherMonster = scanner.nextInt();
                    int result = fightResult(hero, allMonsters.get(switcherMonster - 1));
                    if (result == 0) {
                        String currentMonster = allMonsters.get(switcherMonster - 1).name;
                        countKill.put(currentMonster, countKill.get(currentMonster) + 1);
                    } else if (result == 2) {
                        System.out.println("Ошибка! Этого не должно быть!");
                        return;
                    }
                    System.out.println();
                } else if (switcherMode == 2) {
                    recreation(hero);
                } else if (switcherMode == 3) {
                    System.out.println("Вы отступили. За сессию вы убили:");
                    break;
                }
            } else {
                System.out.println("Вы погибли и успели убить:");
                break;
            }
        for (Map.Entry<String, Integer> stringIntegerEntry : countKill.entrySet()) {
            if (stringIntegerEntry.getValue() > 0) {
                System.out.println(stringIntegerEntry.getKey() + " - " + stringIntegerEntry.getValue());
            }
        }
    }
}
//
//        ThreadLocalRandom current = ThreadLocalRandom.current();
//        current.nextInt(allMonsters.get(1).minStr - 2, )
