package com.company;

import com.company.items.Item;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class FileInventory implements GrindInventory {

    private GrindInventory heroInv;

    public FileInventory(String fileName, GrindInventory heroInv) throws IOException {

        this.heroInv = heroInv;

        InputStream inputStream = new FileInputStream(fileName);
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        List<String> strings = bufferedReader.lines().collect(Collectors.toList());
        inputStream.close();

        heroInv.nameHeroAdd(strings.get(0));

        for (int i = 1; i < strings.size(); i++){
            String iElement = strings.get(i);
            int category = Integer.parseInt(iElement.substring(0, 1));
            int startIndName = iElement.indexOf("\"");
            int endIndName = iElement.indexOf("\"", startIndName + 1);
            int endIndMinStr = iElement.indexOf(",", endIndName + 2);
            Item item;
            if (category != 3){
                String name = iElement.substring(startIndName + 1, endIndName);
                int plusMaxHp = Integer.parseInt(iElement.substring(endIndName + 2));
                item = new Item(name, plusMaxHp, category);
                heroInv.add(item);
            } else {
                String name = iElement.substring(startIndName + 1, endIndName);
                int plusMinStr = Integer.parseInt(iElement.substring(endIndName + 2, endIndMinStr));
                int plusMaxStr = Integer.parseInt(iElement.substring(endIndMinStr + 1));
                item = new Item(name, plusMinStr, plusMaxStr, category);
                heroInv.add(item);
            }
        }
    }

    @Override
    public String nameHero() {
        return heroInv.nameHero();
    }

    @Override
    public void nameHeroAdd(String name){
        heroInv.nameHeroAdd(name);
    }

    @Override
    public List<Item> items() {
        return heroInv.items();
    }

    @Override
    public void add(Item item) {
        heroInv.add(item);
    }

    @Override
    public Item take(int slot) {
        return heroInv.take(slot);
    }
}
