package com.company.doll;

import com.company.GrindInventory;
import com.company.Inventory;
import com.company.items.AllItems;
import com.company.items.Item;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileDoll implements Doll {
    String nameHero;
    Doll doll;
    private List<Item> equippedItem = new ArrayList<>();

    public FileDoll(List<String> listItem, GrindInventory inv, Doll doll) throws FileNotFoundException {
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

    public FileDoll(String fileName, String saveName , AllItems inv, Doll doll ) throws IOException {
        this.doll = doll;

        File file = new File("saves/" + saveName + fileName);
        if (!file.exists()) {
            System.out.println("no");
        } else {
            InputStream inputStream = new FileInputStream(file);
            Reader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            List<String> strings = bufferedReader.lines().collect(Collectors.toList());
            inputStream.close();

            for (int j = 0; j < strings.size(); j++) {
                for (int i = 0; i < inv.itemsList().size(); i++) {
                    boolean cont = strings.get(j).contains(inv.itemsList().get(i).name);
                    if (cont) {
                        doll.putOn(inv.itemsList().get(i));
                        break;
                    }
                }
            }
        }
    }

    public FileDoll(Doll doll) {
        this.doll = doll;
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


    public void writeInFile(String saveName){
    }

}
