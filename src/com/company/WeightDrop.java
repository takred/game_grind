package com.company;

import java.util.ArrayList;
import java.util.List;

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

    static List<Item> collect (List<Item> inventHero, List<Item> allItems, List<WeightDrop> monsterItemDrops){
        List<Item> result = new ArrayList<>();
        return result;

    }
}