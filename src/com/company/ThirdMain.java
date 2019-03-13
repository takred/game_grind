package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ThirdMain {
    static void printListInvent(Inventory inv, List<String> category){
        for (int a = 0; a < inv.items().size(); a++) {
            if (inv.items().get(a) != null) {
                System.out.println(a + 1 + ") " + category.get(a) + " - " + inv.items().get(a).name);
            } else {
                System.out.println(a + 1 + ") " + category.get(a) + " - " + "Ничего не надето.");
            }
        }
    }
    static void printListEquipInvent(InventoryDoll inv, List<String> category){
        for (int a = 0; a < inv.items().size(); a++) {
            if (inv.items().get(a) != null) {
                System.out.println(a + 1 + ") " + category.get(a) + " - " + inv.items().get(a).name);
            } else {
                System.out.println(a + 1 + ") " + category.get(a) + " - " + "Ничего не надето.");
            }
        }
    }
    static void printInfoItem(Inventory inv, int index){

        Item itemByIndex = inv.items().get(index - 1);
        if (itemByIndex.category != 3) {
            System.out.println(itemByIndex.name);
            System.out.println("Прибавка к здоровью - " + itemByIndex.plusMaxHp);
        }else {
            System.out.println(itemByIndex.name);
            System.out.println("Увеличение минимального порога урона - " + itemByIndex.plusMinStr);
            System.out.println("Увеличение максимального порога урона - " + itemByIndex.plusMaxStr);
        }
    }
    static void printInfoEquipItem(InventoryDoll inv, int index) {
        Scanner scanner = new Scanner(System.in);
        Item itemByIndex = inv.items().get(index - 1);
        if (!inv.isOn(index - 1) && itemByIndex.category != 3) {
            System.out.println(itemByIndex.name);
            System.out.println("Прибавка к здоровью - " + itemByIndex.plusMaxHp);
            System.out.println("Выберите действие: 0 - Вернуться в меню надетых предметов.");
            int illusionSwitcher = scanner.nextInt();
        } else if (!inv.isOn(index - 1) && itemByIndex.category == 3) {
            System.out.println(itemByIndex.name);
            System.out.println("Увеличение минимального порога урона - " + itemByIndex.plusMinStr);
            System.out.println("Увеличение максимального порога урона - " + itemByIndex.plusMaxStr);
            System.out.println("Выберите действие: 0 - Вернуться в меню надетых предметов.");
            int illusionSwitcher = scanner.nextInt();
        }else {
            System.out.println("В этом слоте нет надетых предметов. Выберите другой слот.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventory invGarm = new Inventory("Гарм");
        Item item;
        item = new Item("Холщовый капюшон", 5, Item.HEAD);
        invGarm.add(item);
        item = new Item("Холщовая жилетка", 15, Item.TORSO);
        invGarm.add(item);
        invGarm.add(item);
        item = new Item("Ржавая кочерга", 2, 1, Item.WEAPON);
        invGarm.add(item);

        InventoryDoll equipInvGarm = new InventoryDoll();
        equipInvGarm.putOn(item);
        item = new Item("Холщовые штаны", 10, Item.LEGS);
        equipInvGarm.putOn(item);

        List<String> category = new ArrayList<>();
        category.add("Голова");
        category.add("Туловище");
        category.add("Ноги");
        category.add("Оружие");

        while(true) {
            System.out.println("Выберите действие: 1 - Посмотреть инвентарь; 2 - Посмотреть надетые предметы; 0 - Выход.");
            int switcherMode = scanner.nextInt();
            if (switcherMode == 0) {
                return;
            } else if (switcherMode == 1) {
                while(true) {
                    printListInvent(invGarm, category);
                    System.out.println("Выберите предмет, чтобы посмотреть его характеристики или нажмите 0, чтобы вернуться назад.");
                    int switcherInv = scanner.nextInt();
                    if (switcherInv != 0) {
                        Item itemByIndex = invGarm.items().get(switcherInv - 1);
                        printInfoItem(invGarm, switcherInv);
                            System.out.println("Выберите действие: 1 - Надеть предмет; 0 - Вернуться в инвентарь.");
                            int switcherItem = scanner.nextInt();
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
            } else if (switcherMode == 2) {
                while(true) {
                    printListEquipInvent(equipInvGarm, category);
                    System.out.println("Выберите предмет, чтобы посмотреть его характеристики или нажмите 0, чтобы вернуться назад.");
                    int switcherInv = scanner.nextInt();
                    if (switcherInv != 0) {
                        printInfoEquipItem(equipInvGarm, switcherInv);
                    } else {
                        break;
                    }
                }
            }
        }
    }
}
