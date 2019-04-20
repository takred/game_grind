package com.company;

import com.company.items.Item;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Drop {
    String name;
    int minBorder;
    int maxBorder;
    public Drop(String name, int minBorder, int maxBorder){
        this.name = name;
        this.minBorder = minBorder;
        this.maxBorder = maxBorder;
    }
    static List<Item> collect (List<Item> inventHero, List<Item> allItems, List<Drop> monsterItemDrops){

        if (monsterItemDrops.size() > 0) {
            int drop = ThreadLocalRandom.current().nextInt(1, 100);
            if (drop <= 10) {
                int dropItem;
                if (monsterItemDrops.size() > 1) {
                    dropItem = ThreadLocalRandom.current().nextInt(monsterItemDrops.get(0).minBorder, monsterItemDrops.get(monsterItemDrops.size() - 1).maxBorder);
                } else{
                    dropItem = 0;
                }
                for (int i = 0; i < monsterItemDrops.size(); i++) {
                    int minBorder = monsterItemDrops.get(i).minBorder;
                    int maxBorder = monsterItemDrops.get(i).maxBorder;
                    if (dropItem >= minBorder && dropItem <= maxBorder) {
                        String itemName = monsterItemDrops.get(i).name;
                        for (int j = 0; j < allItems.size(); j++) {
                            if (itemName.equals(allItems.get(j).name)) {
                                inventHero.add(allItems.get(j));
                                System.out.println("Вам выпал предмет \"" + itemName + "\".");
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
        return inventHero;
    }

    @Override
    public String toString() {
        return "Drop{" +
                "name='" + name + '\'' +
                ", minBorder=" + minBorder +
                ", maxBorder=" + maxBorder +
                '}';
    }
}
