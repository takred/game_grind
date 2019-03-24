package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AllItems {
    private List<Item> items = new ArrayList<>();

    public AllItems(List<Item> allItems){
        items = allItems;
    }
    public AllItems(String fileName) throws IOException {
        InputStream inputStream = new FileInputStream(fileName);
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        List<String> strings = bufferedReader.lines().collect(Collectors.toList());
        inputStream.close();
        for (int i = 0; i < strings.size(); i++){
            Integer category = Integer.valueOf(strings.get(i).substring(0, 1));//Спросить у Ильи
            int startIndName = strings.get(i).indexOf("\"");
            int endIndName = strings.get(i).indexOf("\"", startIndName + 1);
            int endIndMinStr = strings.get(i).indexOf(",", endIndName + 2);
            Item item;
            if (category != 3){
                String name = strings.get(i).substring(startIndName + 1, endIndName);
                Integer plusMaxHp = Integer.valueOf(strings.get(i).substring(endIndName + 2));
                item = new Item(name, plusMaxHp, category);
                items.add(item);
            } else {
                String name = strings.get(i).substring(startIndName + 1, endIndName);
                Integer plusMinStr = Integer.valueOf(strings.get(i).substring(endIndName + 2, endIndMinStr));
                Integer plusMaxStr = Integer.valueOf(strings.get(i).substring(endIndMinStr + 1));
                item = new Item(name, plusMinStr, plusMaxStr, category);
                items.add(item);
            }
        }
    }

    public List<Item> itemsList(){
        return Collections.unmodifiableList(items);
    }
}
