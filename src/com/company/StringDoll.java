package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StringDoll implements Doll{
    String nameHero;
    private List<Item> equippedItem = new ArrayList<>();

    public StringDoll (List<String> listItem, Inventory inv){
        equippedItem.add(null);
        equippedItem.add(null);
        equippedItem.add(null);
        equippedItem.add(null);

        for (int j = 0; j < listItem.size(); j++) {
            for (int i = 0; i < inv.items().size(); i++) {
                boolean cont = listItem.get(j).contains(inv.items().get(i).name);
                if (cont) {
                    equippedItem.set(j, inv.items().get(i));
                    break;
                }
            }
        }
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

}