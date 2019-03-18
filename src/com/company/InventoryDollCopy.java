package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class InventoryDollCopy implements Doll {
    String nameHero;
    Item head;
    Item torso;
    Item legs;
    Item weapon;

    public InventoryDollCopy() {
        head = null;
        torso = null;
        legs = null;
        weapon = null;
    }
    @Override
    public List<Item> items() {
        List<Item> equippedItem = new ArrayList<>();
        equippedItem.add(head);
        equippedItem.add(torso);
        equippedItem.add(legs);
        equippedItem.add(weapon);
        return Collections.unmodifiableList(equippedItem);
    }
    @Override
    public void putOn(Item item) {
        if (item.category == 0) {
            head = item;
        } else if (item.category == 1) {
            torso = item;
        } else if (item.category == 2) {
            legs = item;
        } else if (item.category == 3) {
            weapon = item;
        }
    }
    @Override
    public Item takeOff(int category) {
        Item item = null;
        if (category == 0) {
            item = head;
            head = null;
        } else if (category == 1) {
            item = torso;
            torso = null;
        } else if (category == 2) {
            item = legs;
            legs = null;
        } else if (category == 3) {
            item = weapon;
            weapon = null;
        }
        return item;
    }
    @Override
    public boolean isOn(int category) {
        Item item = null;
        if (category == 0) {
            item = head;
        } else if (category == 1) {
            item = torso;
        } else if (category == 2) {
            item = legs;
        } else if (category == 3) {
            item = weapon;
        }
        return item == null;
    }
}