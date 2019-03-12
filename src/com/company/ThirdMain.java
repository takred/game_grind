package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ThirdMain {
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

        for (int i = 0; i < 1; ) {
            System.out.println("Выберите действие: 1 - Посмотреть инвентарь; 2 - Посмотреть надетые предметы; 0 - Выход.");
            int switcherMode = scanner.nextInt();
            if (switcherMode == 0) {
                return;
            } else if (switcherMode == 1) {
                for (int a = 0; a < 1; ) {
                    for (int j = 0; j < invGarm.items().size(); j++) {
                        System.out.println(j + 1 + " - " + invGarm.items().get(j).name);
                    }
                    System.out.println("Выберите предмет, чтобы посмотреть его характеристики или нажмите 0, чтобы вернуться назад.");
                    int switcherInv = scanner.nextInt();
                    if (switcherInv != 0) {
//                        if(invGarm.items().get(switcherInv - 1).category != 3){
                        if (invGarm.items().get(switcherInv - 1).category != 3) {
                            System.out.println(invGarm.items().get(switcherInv - 1).name);
                            System.out.println("Прибавка к здоровью - " + invGarm.items().get(switcherInv - 1).plusMaxHp);
                        }else if(invGarm.items().get(switcherInv - 1).category == 3){
                            System.out.println(invGarm.items().get(switcherInv - 1).name);
                            System.out.println("Увеличение минимального порога урона - " + invGarm.items().get(switcherInv - 1).plusMinStr);
                            System.out.println("Увеличение максимального порога урона - " + invGarm.items().get(switcherInv - 1).plusMaxStr);
                        }
                            System.out.println("Выберите действие: 1 - Надеть предмет; 0 - Вернуться в инвентарь.");
                            int switcherItem = scanner.nextInt();
                            if (switcherItem == 1){
                                if(!equipInvGarm.isOn(invGarm.items().get(switcherInv).category)){
                                    invGarm.add(equipInvGarm.takeOff(invGarm.items().get(switcherInv).category));
                                    equipInvGarm.putOn(invGarm.items().get(switcherInv - 1));
                                    invGarm.take(switcherInv - 1);
                                }else {
                                    equipInvGarm.putOn(invGarm.items().get(switcherInv - 1));
                                    invGarm.take(switcherInv - 1);
                                }
                            }
                    } else {
                        break;
                    }
                }
            } else if (switcherMode == 2) {
                for (int j = 0; j < 1; ) {
                    for (int a = 0; a < equipInvGarm.items().size(); a++) {
                        if (equipInvGarm.items().get(a) != null) {
                            System.out.println(a + 1 + ") " + category.get(a) + " - " + equipInvGarm.items().get(a).name);
                        } else {
                            System.out.println(a + 1 + ") " + category.get(a) + " - " + "Ничего не надето.");
                        }
                    }
                    System.out.println("Выберите предмет, чтобы посмотреть его характеристики или нажмите 0, чтобы вернуться назад.");
                    int switcherInv = scanner.nextInt();
                    if (switcherInv != 0) {
                        if (!equipInvGarm.isOn(switcherInv - 1) && equipInvGarm.items().get(switcherInv - 1).category != 3) {
                            System.out.println(equipInvGarm.items().get(switcherInv - 1).name);
                            System.out.println("Прибавка к здоровью - " + equipInvGarm.items().get(switcherInv - 1).plusMaxHp);
                            System.out.println("Выберите действие: 0 - Вернуться в меню надетых предметов.");
                            int illusionSwitcher = scanner.nextInt();
                        }else if(!equipInvGarm.isOn(switcherInv - 1) && equipInvGarm.items().get(switcherInv - 1).category == 3){
                            System.out.println(equipInvGarm.items().get(switcherInv - 1).name);
                            System.out.println("Увеличение минимального порога урона - " + equipInvGarm.items().get(switcherInv - 1).plusMinStr);
                            System.out.println("Увеличение максимального порога урона - " + equipInvGarm.items().get(switcherInv - 1).plusMaxStr);
                            System.out.println("Выберите действие: 0 - Вернуться в меню надетых предметов.");
                            int illusionSwitcher = scanner.nextInt();
                        }else {
                            System.out.println("В этом слоте нет надетых предметов. Выберите другой слот.");
                        }
                    } else {
                        break;
                    }
                }
            }
        }
    }
}
