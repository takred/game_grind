package com.company;

import java.io.IOException;
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

    static Character rest(Character hero, int hours){
        if (hero.maxHp - hero.hp < (20 * hours)) {
            hero.hp = hero.maxHp;
        } else {
            hero.hp = hero.hp + (20 * hours);
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

        {
            List<WeightDrop> drops = Arrays.asList(new WeightDrop("Холщовый капюшон", 1));
            Character monster = new Character("Гигантская крыса", 65, 65, 4, 7, 10, drops);
            allMonsters.add(monster);
        }
        {
            List<WeightDrop> drops = Arrays.asList(
                    new WeightDrop("Холщовые штаны", 8),
                    new WeightDrop("Ржавая кочерга", 2));
            Character monster = new Character("Гоблин", 90, 90, 6, 9, 15, drops);
            allMonsters.add(monster);
        }
        {
            List<WeightDrop> drops = Arrays.asList(
                    new WeightDrop("Холщовая жилетка", 1));
            Character monster = new Character("Фамильяр", 165, 165, 2, 6, 20, drops);
            allMonsters.add(monster);
        }
        {
            List<WeightDrop> drops = new ArrayList<>();
            Character monster = new Character("Волк", 105, 105, 7, 11, 25, drops);
            allMonsters.add(monster);
        }
        {
            List<WeightDrop> drops = Arrays.asList(
                    new WeightDrop("Холщовые штаны", 5),
                    new WeightDrop("Холщовая жилетка", 5));
            character = new Character("Упырь", 150, 150, 5, 8, 30, drops);
            allMonsters.add(character);
        }
        {
            List<WeightDrop> drops = Arrays.asList(
                    new WeightDrop("Холщовый капюшон", 2),
                    new WeightDrop("Холщовая жилетка", 2),
                    new WeightDrop("Холщовые штаны", 2),
                    new WeightDrop("Ржавая кочерга", 4));
            character = new Character("Призрак", 130, 130, 10, 10, 35, drops);
            allMonsters.add(character);
        }
        return allMonsters;
    }
    static List<Item> fillingItemList(List<Item> allItems){
        Item item;
        item = new Item("Холщовый капюшон", 5, Item.HEAD);
        allItems.add(item);
        item = new Item("Холщовая жилетка", 15, Item.TORSO);
        allItems.add(item);
        item = new Item("Холщовые штаны", 10, Item.LEGS);
        allItems.add(item);
        item = new Item("Ржавая кочерга", 2, 1, Item.WEAPON);
        allItems.add(item);
        return allItems;
    }
//    static List<Drop> fillingDropList (List<Drop> allItemDrops){
//        Drop drop;
//        drop = new Drop("Холщовый капюшон", 0, 9);
//        allItemDrops.add(drop);
//        drop = new Drop("Холщовая жилетка", allItemDrops.get(0).maxBorder + 1, allItemDrops.get(0).maxBorder + 8);
//        allItemDrops.add(drop);
//        drop = new Drop("Холщовые штаны", allItemDrops.get(1).maxBorder + 1, allItemDrops.get(1).maxBorder + 6);
//        allItemDrops.add(drop);
//        drop = new Drop("Ржавая кочерга", allItemDrops.get(2).maxBorder + 1, allItemDrops.get(2).maxBorder + 4);
//        allItemDrops.add(drop);
//        return allItemDrops;
//    }

    public static void main(String[] args) throws IOException {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        Character hero = new Character("Гарм", 100, 100, 8, 11, 1, 10, 0, false);
        List<Item> inventGarm = new ArrayList<>();
        Inventory invGarm = new Inventory("Гарм");
        List<Character> allMonsters = new ArrayList<>();
        allMonsters = fillingMonsterList(allMonsters);
        List<Drop> allItemDrops = new ArrayList<>();
//        allItemDrops = fillingDropList(allItemDrops);
        Map<String, Integer> countKill = new HashMap<>();
        for (int i = 0; i < allMonsters.size(); i++) {
            countKill.put(allMonsters.get(i).name, 0);
        }
        AllItems allItems = new AllItems("AllItems.txt");
        for (int i = 0; i < 1; )
            if (hero.hp > 0) {
                System.out.println("у вас " + hero.hp + " единиц здоровья.");
                System.out.println("Введите: 1 - напасть на монстра; 2 - отдохнуть(восстановить 20 здоровья за 1 час); 3 - отступить.");
                int switcherMode = scanner.nextInt();
                if (switcherMode == 1) {
                    System.out.println("Выберите противника :");
                    for (int j = 0; j < allMonsters.size(); j++) {
                        System.out.println(j + 1 + " - " + allMonsters.get(j).name);
                    }
                    int switcherMonster = scanner.nextInt();
                    int result = fightResult(hero, allMonsters.get(switcherMonster - 1));
                    if (result == 0) {
                        WeightDrop.collect(invGarm, allItems.itemsList(), allMonsters.get(switcherMonster - 1).itemDrop);
                        String currentMonster = allMonsters.get(switcherMonster - 1).name;
                        countKill.put(currentMonster, countKill.get(currentMonster) + 1);
                    } else if (result == 2) {
                        System.out.println("Ошибка! Этого не должно быть!");
                        return;
                    }
                    System.out.println();
                } else if (switcherMode == 2) {
                    System.out.println("Введите сколько часов вы хотите отдохнуть: ");
                    int hours = scanner.nextInt();
                    rest(hero, hours);
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
        for (int i = 0; i < invGarm.items().size(); i++) {
            System.out.println(invGarm.items().get(i).name);
        }
    }
}
//
//        ThreadLocalRandom current = ThreadLocalRandom.current();
//        current.nextInt(allMonsters.get(1).minStr - 2, )
