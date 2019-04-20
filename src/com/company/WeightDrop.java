package com.company;

import com.company.items.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class WeightDrop {
    String name;
    int weight;
    public WeightDrop(String name, int weight){
        this.name = name;
        this.weight = weight;
    }
    public static List<Drop> weightToDrop(List<WeightDrop> drops){
        List<Drop> result = new ArrayList<>();
        for (int i = 0; i < drops.size(); i++) {
            if (result.size() == 0){
                Drop drop = new Drop(drops.get(i).name, 0, drops.get(i).weight - 1);
                result.add(drop);
            } else {
                Drop drop = new Drop(drops.get(i).name, result.get(i - 1).maxBorder + 1, result.get(i - 1).maxBorder + drops.get(i).weight);
                result.add(drop);
            }
        }
//        Drop drop = new Drop(drops.get(0).name, 0, drops.get(0).weight - 1);
//        result.add(drop);
        return result;
    }

    static void collect (Inventory inventHero, List<Item> allItems, List<WeightDrop> monsterItemDrops){
        if (monsterItemDrops.size() > 0) {
        int drop = ThreadLocalRandom.current().nextInt(1, 100);
        if (drop <= 10) {
            int dropItem;
            if (monsterItemDrops.size() > 1) {
                int sumWeight = 0;
                for (int i = 0; i < monsterItemDrops.size(); i++){
                    sumWeight = sumWeight + monsterItemDrops.get(i).weight;
                }
                dropItem = ThreadLocalRandom.current().nextInt(1, sumWeight);
            }
            else{
                dropItem = 0;
            }
            if (monsterItemDrops.size() > 0) {
                int weightCurrent = 0;
                for (int i = 0; i < monsterItemDrops.size(); i++) {
                    weightCurrent = weightCurrent + monsterItemDrops.get(i).weight;
                    if (dropItem <= weightCurrent) {
                        String itemName = monsterItemDrops.get(i).name;
                        for (int j = 0; j < allItems.size(); j++) {
                            if (itemName.equals(allItems.get(j).name)) {
                                inventHero.add(allItems.get(j));
                                System.out.println("Вам выпал предмет \"" + itemName + "\".");
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    }
}