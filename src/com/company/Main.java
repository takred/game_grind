package com.company;

import java.util.*;

public class Main {
    static int fightResult(Character hero, Character monster){
        for (int i = 0; i < 1000; i++){
            if(monster.hp - hero.str > 0) {
                monster.hp = monster.hp - hero.str;
                System.out.println("Вы нанесли " + monster.name + " " + hero.str + " урона." + " У " + monster.name +
                        " осталось " + monster.hp + " здоровья.");
            }else {
                System.out.println("Вы нанесли " + monster.name + " " + hero.str + " урона.");
                System.out.println("Вы убили " + monster.name + ".");
                hero.exp = hero.exp + monster.exp;
                monster.hp = monster.maxHp;
                System.out.println("Вы получили " + monster.exp + " опыта.");
                if (hero.exp >= hero.nextLvl){
                    System.out.println("Вы повысили уровень!");
                    hero = lvlUp(hero);
                }
                System.out.println("У вас " + hero.exp + "/" + hero.nextLvl + " опыта.");
                return 0;
            }
            if (hero.hp - monster.str > 0){
                hero.hp = hero.hp - monster.str;
                System.out.println(monster.name + " нанёс вам " + monster.str + " урона." + " У вас осталось "
                        + hero.hp + " здоровья.");
            } else {
                hero.hp = hero.hp - monster.str;
                System.out.println(monster.name + " нанёс вам " + monster.str + " урона.");
                System.out.println("Вас убил " + monster.name + ".");
                return 1;
            }
        }
        return 2;
    }
    static Character lvlUp(Character hero){
        hero.maxHp = hero.maxHp + 10;
        hero.hp = hero.maxHp;
        hero.str = hero.str + 1;
        hero.exp = hero.exp - hero.nextLvl;
        hero.nextLvl = hero.nextLvl * 2;
        return hero;
    }

    public static void main(String[] args) {
	// write your code here
        Scanner scanner = new Scanner(System.in);
        Character hero = new Character("Garm", 100, 100, 10, 10, 0);
        List<Character> allMonsters = new ArrayList<>();
        Character character;
        character = new Character("Giant rat", 65, 65, 7, 10);
        allMonsters.add(character);
        character = new Character("Goblin", 90, 90, 8, 15);
        allMonsters.add(character);
        character = new Character("Familiar", 165, 165, 5, 20);
        allMonsters.add(character);
        character = new Character("Wolf", 105, 105, 9, 25);
        allMonsters.add(character);
        character = new Character("Ghoul", 150, 150, 6, 30);
        allMonsters.add(character);
        character = new Character("Ghost", 130, 130, 10, 35);
        allMonsters.add(character);
        Map<String, Integer> countKill = new HashMap<>();
        for (int i = 0; i < allMonsters.size(); i++){
            countKill.put(allMonsters.get(i).name, 0);
        }
//        int countKill = 0;
        for (int i = 0; i < 1;)
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
                    if (hero.maxHp - hero.hp < 20) {
                        hero.hp = hero.maxHp;
                        System.out.println("Вы отдохнули.");
                    } else {
                        hero.hp = hero.hp + 20;
                        System.out.println("Вы отдохнули.");
                    }
                } else if (switcherMode == 3) {
                    System.out.println("Вы отступили. За сессию вы убили:");

//                    System.out.println("Вы отступили. За сессию вы убили " + countKill + ".");
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
