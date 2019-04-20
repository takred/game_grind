package com.company.doll;

import com.company.Inventory;
import com.company.Item;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StringDoll implements Doll {
    String nameHero;
    Doll doll;
    private List<Item> equippedItem = new ArrayList<>();

    public StringDoll (List<String> listItem, Inventory inv, Doll doll) throws FileNotFoundException {
        this.doll = doll;

        for (int j = 0; j < listItem.size(); j++) {
            for (int i = 0; i < inv.items().size(); i++) {
                boolean cont = listItem.get(j).contains(inv.items().get(i).name);
                if (cont) {
                    doll.putOn(inv.items().get(i));
                    break;
                }
            }
        }
    }

    public StringDoll(String fileName, Inventory inv, Doll doll ) throws IOException {
        this.doll = doll;

        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("no");
        }else {
            InputStream inputStream = new FileInputStream(file);
            Reader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            List<String> strings = bufferedReader.lines().collect(Collectors.toList());
            inputStream.close();

            for (int j = 0; j < strings.size(); j++) {
                for (int i = 0; i < inv.items().size(); i++) {
                    boolean cont = strings.get(j).contains(inv.items().get(i).name);
                    if (cont) {
                        doll.putOn(inv.items().get(i));
                        break;
                    }
                }
            }
        }
    }

    @Override
    public List<Item> items(){
        return doll.items();
    }
    @Override
    public void putOn(Item item) throws FileNotFoundException {
        doll.putOn(item);
    }
    @Override
    public Item takeOff(int category) throws FileNotFoundException {
        return doll.takeOff(category);
    }
    @Override
    public boolean isOn(int category) {
        return doll.isOn(category);
    }

}
