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
            int startIdxName = iElement.indexOf("\"");
            int endIdxName = iElement.indexOf("\"", startIdxName + 1);
            int endIdxMinStr = iElement.indexOf(",", endIdxName + 2);
            Item item;
            if (category != 3){
                String name = iElement.substring(startIdxName + 1, endIdxName);
                int plusMaxHp = Integer.parseInt(iElement.substring(endIdxName + 2));
                item = new Item(name, plusMaxHp, category);
                heroInv.add(item);
            } else {
                String name = iElement.substring(startIdxName + 1, endIdxName);
                int plusMinStr = Integer.parseInt(iElement.substring(endIdxName + 2, endIdxMinStr));
                int plusMaxStr = Integer.parseInt(iElement.substring(endIdxMinStr + 1));
                item = new Item(name, plusMinStr, plusMaxStr, category);
                heroInv.add(item);
            }
        }
    }

    public FileInventory(GrindInventory heroInv){
        this.heroInv = heroInv;
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
    public void add(Item item) throws FileNotFoundException {
        heroInv.add(item);
    }

    @Override
    public Item take(int slot) throws FileNotFoundException {
        return heroInv.take(slot);
    }

    @Override
    public void writeInFile(String saveName) throws FileNotFoundException { }
}
