package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestMain {
    public static void main(String[] args) throws IOException {
//        String string = "0,\"Холщовый капюшон\",5";
//        Integer integer = Integer.valueOf(string.substring(0, 1));
//        System.out.println(integer);
        List<Item> itemList = new ArrayList<>();

        AllItems allItems = new AllItems("AllItems.txt", itemList);

        List<String> category = new ArrayList<>();
        category.add("Голова");
        category.add("Туловище");
        category.add("Ноги");
        category.add("Оружие");

        for (int i = 0; i < allItems.itemsList().size(); i++) {
            if (itemList.get(i) != null) {
                System.out.println(i + 1 + ") " + category.get(itemList.get(i).category) + " - " + itemList.get(i).name);
            } else {
                System.out.println(i + 1 + ") " + category.get(itemList.get(i).category) + " - " + "Ничего не надето.");
            }
        }
    }
}
