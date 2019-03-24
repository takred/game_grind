package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestMain {
    public static void main(String[] args) throws IOException {
        List<Item> itemList = new ArrayList<>();

        AllItems allItems = new AllItems("AllItems.txt");

        List<String> category = new ArrayList<>();
        category.add("Голова");
        category.add("Туловище");
        category.add("Ноги");
        category.add("Оружие");

        for (int i = 0; i < allItems.itemsList().size(); i++) {
            if (allItems.itemsList().get(i) != null) {
                System.out.println(i + 1 + ") " + category.get(allItems.itemsList().get(i).category) + " - " + allItems.itemsList().get(i).name);
            } else {
                System.out.println(i + 1 + ") " + category.get(allItems.itemsList().get(i).category) + " - " + "Ничего не надето.");
            }
        }
    }
}
