package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class InventoryDoll implements Doll {
    String nameHero;
    private List<Item> equippedItem = new ArrayList<>();

    public InventoryDoll(){
        equippedItem.add(null);
        equippedItem.add(null);
        equippedItem.add(null);
        equippedItem.add(null);
    }
    @Override
    public List<Item> items(){
        return Collections.unmodifiableList(equippedItem);
    }
    @Override
    public void putOn(Item item){
        equippedItem.set(item.category, item);
    }
    @Override
    public Item takeOff(int category) {
        Item item = equippedItem.get(category);
        equippedItem.set(category, null);
        return item;
    }
    @Override
    public boolean isOn(int category) {
        return equippedItem.get(category) == null;
    }

//    public List<Item> equipItem(List<Item> invent, List<Item> equipInvent){
//        Scanner scanner = new Scanner(System.in);
//        List<Item> newInvent = new ArrayList<>();
//        for (int i = 0; i < invent.size(); i++){
//            System.out.println(i + 1 + ") " + invent.get(i).name);
//        }
//        System.out.println("Выберите предмет, который хотите надеть.");
//        int targetItem = scanner.nextInt();
//        for (int i = 0; i < invent.size(); i++){
//            if (i + 1 != targetItem){
//                newInvent.add(invent.get(i));
//            }
//        }
//        Item removeItem;
//        List<Item> newEquipInvent = new ArrayList<>();
//        for (int i = 0; i < equipInvent.size(); i++){
//            if (invent.get(targetItem).category.equals(equipInvent.get(i).category)){
//                removeItem = equipInvent[i];
//                newInvent.add(removeItem);
//                for (int j = 0; j < equipInvent.length; j++){
//                    if (j != i) {
//                        newEquipInvent[j] = equipInvent[j];
//                    }else {
//                        newEquipInvent[j] = invent.get(targetItem);
//                    }
//                }
//            }
//        }
//        return invent;
//    }
}
