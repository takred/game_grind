package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AllItems {
    private List<Item> items = new ArrayList<>();

    public AllItems(List<Item> allItems){
        items = allItems;
    }

    public List<Item> itemsList(){
        return Collections.unmodifiableList(items);
    }
}
