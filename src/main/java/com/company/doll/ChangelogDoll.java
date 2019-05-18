//package com.company.doll;
//
//import com.company.items.Item;
//
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.OutputStream;
//import java.io.PrintWriter;
//import java.util.List;
//
//public class ChangelogDoll implements Doll {
//    String nameHero;
//    Doll doll;
//
//    public ChangelogDoll(Doll doll){
//        this.doll = doll;
//    }
//    @Override
//    public List<Item> items(){
//        return doll.items();
//    }
//    @Override
//    public void putOn(Item item, String saveName) throws FileNotFoundException {
//        doll.putOn(item);
//        putOnLog(item, saveName);
//    }
//    @Override
//    public Item takeOff(int category, String saveName) throws FileNotFoundException {
//        Item item = doll.takeOff(category);
//        takeOffLog(item, saveName);
//        return item;
//    }
//    @Override
//    public boolean isOn(int category) {
//        return doll.isOn(category);
//    }
//
//    @Override
//    public void writeInFile(String saveName) {
//    }
//
//    public void putOnLog(Item item, String saveName) throws FileNotFoundException {
//        OutputStream outputStream = new FileOutputStream("saves/" + saveName + "/ChangelogDoll.txt", true);
//        PrintWriter writer = new PrintWriter(outputStream);
//        writer.println("Экипирован предмет : " + item.name);
//        writer.close();
//    }
//    public void takeOffLog(Item item, String saveName) throws FileNotFoundException {
//        OutputStream outputStream = new FileOutputStream("saves/" + saveName + "/ChangelogDoll.txt", true);
//        PrintWriter writer = new PrintWriter(outputStream);
//        writer.println("Снят предмет : " + item.name);
//        writer.close();
//    }
//}
