package com.company;

import com.company.character.EquipedCharacter;
import com.company.character.GrindCharacter;
import com.company.character.NakedGrindCharacter;
import com.company.character.PersistentGrindCharacter;
import com.company.doll.Doll;
import com.company.doll.PersistentDoll;
import com.company.doll.InventoryDollCopy;
import com.company.doll.FileDoll;
import com.company.items.AllItems;
import com.company.items.Item;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

public class ThirdMain {
    static int fightResult(GrindCharacter equipedHero, NakedGrindCharacter monster, TextGraphics tGraphics, Screen screen) throws IOException {
        for (int i = 0; i < 1000; i++) {
            boolean resultHeroAttack = heroAttack(equipedHero, monster, tGraphics, screen);
            if (resultHeroAttack) {
                return 0;
            }
            if(equipedHero.perkDoubleAttack()){
                System.out.println("Из-за особенности \"Двойной удар\", вы наносите ещё 1 удар.");
                resultHeroAttack = heroAttack(equipedHero, monster, tGraphics, screen);
                if (resultHeroAttack){
                    return 0;
                }
            }
            boolean resultMonsterAttack = monsterAttack(equipedHero, monster);
            if (resultMonsterAttack){
                return 1;
            }
        }
        return 2;
    }

    static GrindCharacter lvlUp(GrindCharacter hero, TextGraphics tGraphics, Screen screen) throws IOException {
        hero.increaseLvl();
        if (hero.lvl() % 4 == 0 && hero.lvl() != 32) {
            tGraphics.putString(10, 11, "Выберите усиление : 1 - увеличить максимальный запас здоровья на 20;" +
                    " 2 - увеличить минимальнй порог урона на 2; 3 - увеличить максимальный порог урона на 2;");
            screen.refresh();
//            System.out.println("Выберите усиление : 1 - увеличить максимальный запас здоровья на 20;" +
//                    " 2 - увеличить минимальнй порог урона на 2; 3 - увеличить максимальный порог урона на 2;");
            Scanner scanner = new Scanner(System.in);
            int choice = Integer.valueOf("" + screen.readInput().getCharacter());
//            int choice = scanner.nextInt();
            if (choice == 1) {
                hero.increaseMaxHp(20);
            } else if (choice == 2) {
                hero.increaseMinStr(2);
            } else if (choice == 3) {
                hero.increaseMaxStr(2);
            }
        } else if (hero.lvl() == 32) {
            System.out.println("Вы получили новую особенность - \"Двойной удар\"!");
            hero.enableDoubleAttack();
        }
        hero.increaseMaxHp(10);
        hero.increaseHp(hero.maxHp() - hero.hp());
        hero.increaseMinStr(1);
        hero.increaseMaxStr(1);
        hero.decreaseExp(hero.nextLvl());
        hero.increaseNextLvl();
        return hero;
    }

    static void rest(GrindCharacter hero, int hours){
        if (hero.maxHp() - hero.hp() < 20 * hours) {
            hero.increaseHp(hero.maxHp() - hero.hp());
        }else{
            hero.increaseHp(20 * hours);
        }
        System.out.println("Вы отдохнули.");
    }

    static boolean heroAttack(GrindCharacter equipedHero, NakedGrindCharacter monster, TextGraphics tGraphics, Screen screen) throws IOException {
        int currentDamageHero = equipedHero.currentDamage();
        if (monster.hp() - currentDamageHero > 0) {
            monster.decreaseHp(currentDamageHero);
            System.out.println("Вы нанесли " + monster.name() + " " + currentDamageHero + " урона." + " У " + monster.name()
                    + " осталось " + monster.hp() + " здоровья из " + monster.maxHp() + ".");
        } else {
            System.out.println("Вы нанесли " + monster.name() + " " + currentDamageHero + " урона.");
            System.out.println("Вы убили " + monster.name() + ".");
            equipedHero.increaseExp(monster.exp());
            monster.increaseHp(monster.maxHp());
            System.out.println("Вы получили " + monster.exp() + " опыта.");
            if (equipedHero.exp() >= equipedHero.nextLvl()) {
                screen.clear();
                tGraphics.putString(10, 10, "Вы повысили уровень до " + (equipedHero.lvl() + 1) + "-го !");
//                System.out.println("Вы повысили уровень до " + (equipedHero.lvl() + 1) + "-го !");
                lvlUp(equipedHero, tGraphics, screen);
                screen.refresh();
//                ПОМЕТКА
            }
            System.out.println("У вас " + equipedHero.exp() + "/" + equipedHero.nextLvl() + " опыта.");
            return true;
        }
        return false;
    }
    static boolean monsterAttack(GrindCharacter hero, NakedGrindCharacter monster){
        int currentDamageMonster = monster.currentDamage();
        if (hero.hp() - currentDamageMonster > 0) {
            hero.decreaseHp(currentDamageMonster);
            System.out.println(monster.name() + " нанёс вам " + currentDamageMonster + " урона." + " У вас осталось "
                    + hero.hp() + " здоровья из " + hero.maxHp() + ".");
        } else {
            hero.decreaseHp(currentDamageMonster);
            System.out.println(monster.name() + " нанёс вам " + currentDamageMonster + " урона.");
            System.out.println("Вас убил " + monster.name() + ".");
            return true;
        }
        return false;
    }

    static AllMonsters monsterList() throws IOException {
        AllMonsters allMonsters = new AllMonsters("AllMonsters.txt");
        return allMonsters;
    }

    static void printListInvent(GrindInventory inv, List<String> category, TextGraphics tGraphics){
        if (inv.items().size() > 0) {
            tGraphics.putString(10, 10, "Выберите предмет, чтобы посмотреть его характеристики или нажмите 0, чтобы вернуться назад.");
            for (int a = 0; a < inv.items().size(); a++) {
                if (inv.items().get(a) != null) {
                    tGraphics.putString(10, 11 + a, a + 1 + ") " + category.get(inv.items().get(a).category) + " - " + inv.items().get(a).name);
                } else {
                    tGraphics.putString(10, 11 + a, a + 1 + ") " + category.get(inv.items().get(a).category) + " - " + "Ничего не надето.");
                }
            }
        } else {
//            System.out.println("Нажмите 0, чтобы вернуться назад.");
            System.out.println("В сумке пусто.");
        }
    }
    static void printListEquipInvent(Doll inv, List<String> category, TextGraphics tGraphics){
        for (int a = 0; a < inv.items().size(); a++) {
            if (inv.items().get(a) != null) {
                tGraphics.putString(10, 10 + a, a + 1 + ") " + category.get(a) + " - " + inv.items().get(a).name);
//                System.out.println(a + 1 + ") " + category.get(a) + " - " + inv.items().get(a).name);
            } else {
                tGraphics.putString(10, 10 + a, a + 1 + ") " + category.get(a) + " - " + "Ничего не надето.");
//                System.out.println(a + 1 + ") " + category.get(a) + " - " + "Ничего не надето.");
            }
        }
    }
    static void printInfoItem(Item item, TextGraphics tGraphics){
        if (item.category != 3) {
            tGraphics.putString(10, 10, item.name);
            tGraphics.putString(10, 11, "Прибавка к здоровью - " + item.plusMaxHp);
//            System.out.println(item.name);
//            System.out.println("Прибавка к здоровью - " + item.plusMaxHp);
        }else {
            tGraphics.putString(10, 10, item.name);
            tGraphics.putString(10, 11, "Увеличение минимального порога урона - " + item.plusMinStr);
            tGraphics.putString(10, 12, "Увеличение максимального порога урона - " + item.plusMaxStr);
//            System.out.println(item.name);
//            System.out.println("Увеличение минимального порога урона - " + item.plusMinStr);
//            System.out.println("Увеличение максимального порога урона - " + item.plusMaxStr);
        }
    }
    static void printInfoEquipItem(Item itemByIndex, TextGraphics tGraphics) {
        if (itemByIndex.category != 3) {
            tGraphics.putString(10, 10, itemByIndex.name);
            tGraphics.putString(10, 11, "Прибавка к здоровью - " + itemByIndex.plusMaxHp);
//            System.out.println(itemByIndex.name);
//            System.out.println("Прибавка к здоровью - " + itemByIndex.plusMaxHp);
        } else {
            tGraphics.putString(10, 10, itemByIndex.name);
            tGraphics.putString(10, 11, "Увеличение минимального порога урона - " + itemByIndex.plusMinStr);
            tGraphics.putString(10, 12, "Увеличение максимального порога урона - " + itemByIndex.plusMaxStr);
            System.out.println(itemByIndex.name);
            System.out.println("Увеличение минимального порога урона - " + itemByIndex.plusMinStr);
            System.out.println("Увеличение максимального порога урона - " + itemByIndex.plusMaxStr);
        }
    }
    static String createNewHero() throws IOException {
        Scanner scanner = new  Scanner(System.in);
        System.out.println("Напишите название сохранения.");
        String saveName = scanner.nextLine();
        System.out.println(saveName);
        File newDir = new File("saves/" + saveName);
        if (!newDir.exists()) {
            newDir.mkdir();
        }
        System.out.println("Введите имя персонажа.");
        GrindCharacter nakedHero = new NakedGrindCharacter();
        GrindCharacter saveHero = new PersistentGrindCharacter(nakedHero);
        saveHero.writeInFile(saveName);
        GrindInventory inv = new Inventory(nakedHero.name());
        GrindInventory saveInv = new PersistentInventory(inv);
        saveInv.writeInFile(saveName);
        OutputStream equipedInv = new FileOutputStream("saves/" + saveName + "/PersistentDoll.txt");
        equipedInv.close();
        return saveName;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
//        String saveName = "";
        String saveName = "";
        GrindCharacter nakedHeroGarm;

        GrindInventory inventory;
        GrindInventory createInv;
        GrindInventory invGarm;

        Doll originalDoll;
        Doll createDoll;
        Doll equipInvGarm;

        GrindCharacter equipedHeroGarm;
        GrindCharacter saveNakedHeroGarm;

        AllMonsters allMonsters = monsterList();
        AllItems allItems = new AllItems("AllItems.txt");

        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);
        TextGraphics tGraphics = screen.newTextGraphics();
        screen.startScreen();

        while (true) {
            screen.clear();
            tGraphics.putString(10, 10, "Выберите действие : 1 - Создать нового персонажа; 2 - Загрузить персонажа.");
            screen.refresh();
            KeyStroke keyStroke = screen.readInput();
            System.out.println(keyStroke.getCharacter());
//            System.out.println("Выберите действие : 1 - Создать нового персонажа; 2 - Загрузить персонажа.");
//            int loadHero = scanner.nextInt();
            if (keyStroke.getCharacter() == '1') {
                screen.clear();
                tGraphics.putString(10, 10, "Напишите название сохранения.");
                screen.refresh();
//                System.out.println("Напишите название сохранения.");
//                scanner.nextLine();
//                saveName = scanner.nextLine();
                keyStroke = screen.readInput();
                saveName = keyStroke.getCharacter().toString();
                screen.clear();
                tGraphics.putString(10, 10, "Введите имя персонажа.");
                screen.refresh();
//                System.out.println("Введите имя персонажа.");
                keyStroke = screen.readInput();
                String nameHero = keyStroke.toString();
//                String nameHero = scanner.nextLine();
                nakedHeroGarm = new NakedGrindCharacter(nameHero, 100, 100, 8, 11, 1, 10, 0, false);

                inventory = new Inventory(nameHero);
                createInv = new FileInventory(inventory);
                invGarm = new PersistentInventory(createInv);

                originalDoll = new InventoryDollCopy(nakedHeroGarm.name());
                equipInvGarm = new PersistentDoll(originalDoll);

                equipedHeroGarm = new EquipedCharacter(equipInvGarm, nakedHeroGarm);
                saveNakedHeroGarm = new PersistentGrindCharacter(nakedHeroGarm);
                break;
            } else {
                File savesDir = new File("saves");
                if (!savesDir.isDirectory()) {
                    screen.clear();
                    tGraphics.putString(10, 10, "Сохранений нет.");
                    screen.refresh();
//                    System.out.println("Сохранений нет.");
                } else {
                    File[] saves = savesDir.listFiles();
                    screen.clear();
                    tGraphics.putString(10, 10, "Выберите сохранение, которое хотите загрузить :");
//                    System.out.println("Выберите сохранение, которое хотите загрузить :");
                    for (int i = 0; i < saves.length; i++) {
//                        System.out.println(i + 1 + " - " + saves[i].getName());
                        tGraphics.putString(10, 11 + i, i + 1 + " - " + saves[i].getName());
                    }
                    screen.refresh();
                    keyStroke = screen.readInput();
                    int numberLoad = Integer.valueOf("" + keyStroke.getCharacter());
                    System.out.println(numberLoad);
//                    int numberLoad = scanner.nextInt();
                    saveName = saves[numberLoad - 1].getName();
                }
                nakedHeroGarm = new NakedGrindCharacter("saves/" + saveName + "/Character.txt");

                inventory = new Inventory();
                createInv = new FileInventory("saves/" + saveName + "/Inventory.txt", inventory);
                invGarm = new PersistentInventory(createInv);

                originalDoll = new InventoryDollCopy(nakedHeroGarm.name());
                createDoll = new FileDoll("PersistentDoll.txt", saveName, allItems, originalDoll);
                equipInvGarm = new PersistentDoll(createDoll);

                equipedHeroGarm = new EquipedCharacter(equipInvGarm, nakedHeroGarm);
                saveNakedHeroGarm = new PersistentGrindCharacter(nakedHeroGarm);
                break;
            }
        }

        List<String> category = new ArrayList<>();
        category.add("Голова");
        category.add("Туловище");
        category.add("Ноги");
        category.add("Оружие");

        Map<String, Integer> countKill = new HashMap<>();
        for (int i = 0; i < allMonsters.monstersList().size(); i++) {
            NakedGrindCharacter allMonster = allMonsters.monstersList().get(i);
            countKill.put(allMonster.name(), 0);
        }

        while (true) {
            screen.clear();
            if (equipedHeroGarm.hp() > 0) {
                tGraphics.putString(10, 10, "у вас " + equipedHeroGarm.hp() + " единиц здоровья из " + equipedHeroGarm.maxHp() + ".");
                tGraphics.putString(10, 11, "Введите: 1 - напасть на монстра; 2 - отдохнуть(восстановить 20 здоровья за 1 час); 3 - открыть меню инвентаря; 4 - отступить.");
                screen.refresh();
//                System.out.println("у вас " + equipedHeroGarm.hp() + " единиц здоровья из " + equipedHeroGarm.maxHp() + ".");
//                System.out.println("Введите: 1 - напасть на монстра; 2 - отдохнуть(восстановить 20 здоровья за 1 час); 3 - открыть меню инвентаря; 4 - отступить.");
//                int switcherMode = scanner.nextInt();
                char switcherMode = screen.readInput().getCharacter();

                if (switcherMode == '1') {
                    screen.clear();
                    tGraphics.putString(10, 10, "Выберите противника :");
//                    System.out.println("Выберите противника :");
                    for (int j = 0; j < allMonsters.monstersList().size(); j++) {
                        tGraphics.putString(10, 11 + j, j + 1 + " - " + allMonsters.monstersList().get(j).name());
//                        System.out.println(j + 1 + " - " + allMonsters.monstersList().get(j).name());
                    }
                    screen.refresh();
                    int switcherMonster = Integer.valueOf("" + screen.readInput().getCharacter());
                    int result = fightResult(equipedHeroGarm, allMonsters.monstersList().get(switcherMonster - 1), tGraphics, screen);
                    if (result == 0) {
                        WeightDrop.collect(invGarm, allItems.itemsList(), allMonsters.monstersList().get(switcherMonster - 1).itemDrop());
                        String currentMonster = allMonsters.monstersList().get(switcherMonster - 1).name();
                        countKill.put(currentMonster, countKill.get(currentMonster) + 1);
                    } else if (result == 2) {
                        System.out.println("Ошибка! Этого не должно быть!");
                        return;
                    }
                    System.out.println();
                } else if (switcherMode == '2') {
                    screen.clear();
                    tGraphics.putString(10, 10, "Введите сколько часов вы хотите отдохнуть: ");
                    screen.refresh();
//                    System.out.println("Введите сколько часов вы хотите отдохнуть: ");
                    int hours = Integer.valueOf("" + screen.readInput().getCharacter());
                    rest(equipedHeroGarm, hours);
                } else if (switcherMode == '3'){
                    while(true) {
                        screen.clear();
//                        System.out.println("Выберите действие: 1 - Посмотреть инвентарь; 2 - Посмотреть надетые предметы; 0 - Выход.");
                        tGraphics.putString(10, 10, "Выберите действие: 1 - Посмотреть инвентарь; 2 - Посмотреть надетые предметы; 0 - Выход.");
                        screen.refresh();
                        char switcherModeInv = screen.readInput().getCharacter();
//                        int switcherModeInv = scanner.nextInt();
                        if (switcherModeInv == '0') {
                            break;
                        } else if (switcherModeInv == '1') {
                            while(true) {
                                screen.clear();
                                printListInvent(invGarm, category, tGraphics);
                                screen.refresh();
                                int switcherInv = 0;
                                if (invGarm.items().size() > 0) {
                                    switcherInv = Integer.valueOf("" + screen.readInput().getCharacter());
                                }
                                if (switcherInv != 0) {
                                    screen.clear();
                                    Item itemByIndex = invGarm.items().get(switcherInv - 1);
                                    printInfoItem(itemByIndex, tGraphics);
                                    if (itemByIndex.category != 3) {
                                        tGraphics.putString(10, 12, "Выберите действие: 1 - Надеть предмет; 0 - Вернуться в инвентарь.");
//                                        System.out.println("Выберите действие: 1 - Надеть предмет; 0 - Вернуться в инвентарь.");
                                    } else {
                                        tGraphics.putString(10, 13, "Выберите действие: 1 - Надеть предмет; 0 - Вернуться в инвентарь.");
                                    }
                                    screen.refresh();
                                    int switcherItem = Integer.valueOf("" + screen.readInput().getCharacter());
                                    if (switcherItem == 1){
                                        if(!equipInvGarm.isOn(itemByIndex.category)){
                                            invGarm.add(equipInvGarm.takeOff(itemByIndex.category));
                                            equipInvGarm.putOn(itemByIndex);
                                            invGarm.take(switcherInv - 1);
                                        }else {
                                            equipInvGarm.putOn(itemByIndex);
                                            invGarm.take(switcherInv - 1);
                                        }
                                    }
                                } else {
                                    break;
                                }
                            }
                        } else if (switcherModeInv == '2') {
                            while(true) {
                                screen.clear();
                                printListEquipInvent(equipInvGarm, category, tGraphics);
                                tGraphics.putString(10, 10 + equipInvGarm.items().size(), "Выберите предмет, чтобы посмотреть его характеристики или нажмите 0, чтобы вернуться назад.");
                                screen.refresh();
//                                int switcherInv = scanner.nextInt();
                                int switcherInv = Integer.valueOf("" + screen.readInput().getCharacter());
                                if (switcherInv != 0) {
                                    final int cat = switcherInv - 1;
                                    final boolean on = !equipInvGarm.isOn(cat);
                                    if (on) {
                                        screen.clear();
                                        printInfoEquipItem(equipInvGarm.items().get(cat), tGraphics);
                                        tGraphics.putString(10, 10, "Выберите действие: любая клавиша - Вернуться в меню надетых предметов.");
                                        screen.refresh();
                                        screen.readInput();
                                    } else {
                                        screen.clear();
                                        tGraphics.putString(10, 10, "В этом слоте нет надетых предметов. Выберите другой слот.");
                                        screen.refresh();
                                    }
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                }
                else if (switcherMode == '4') {
                    saveNakedHeroGarm.writeInFile(saveName);
                    invGarm.writeInFile(saveName);
                    ((PersistentDoll) equipInvGarm).writeInFile(saveName);
                    screen.clear();
                    tGraphics.putString(10, 10, "Вы отступили. За сессию вы убили:");
                    screen.refresh();
//                    System.out.println("Вы отступили. За сессию вы убили:");
                    break;
                }
            } else {

                System.out.println("Вы погибли и успели убить:");
                break;
            }
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
