package com.company;

import com.company.character.NakedGrindCharacter;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AllMonsters {
    private List<NakedGrindCharacter> monsters = new ArrayList<>();

    public AllMonsters(String fileName) throws IOException{
        InputStream inputStream = new FileInputStream(fileName);
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        List<String> strings = bufferedReader.lines().collect(Collectors.toList());
        inputStream.close();
        for (int i = 0; i < strings.size(); i++) {
            String string = strings.get(i);
            int startIndName = string.indexOf("\"");
            int endIndName = string.indexOf("\"", startIndName + 1);
            int firstBreakPoint = string.indexOf(",", endIndName + 2);
            int secondBreakPoint = string.indexOf(",", firstBreakPoint + 1);
            int thirdBreakPoint = string.indexOf(",", secondBreakPoint + 1);
            int fourthBreakPoint = string.indexOf(",", thirdBreakPoint + 1);
//            int fifthBreakPoint = strings.get(i).indexOf(",", fourthBreakPoint + 1);
//            NakedGrindCharacter monster;
            List<WeightDrop> monsterDrops = new ArrayList<>();
            if (string.substring(fourthBreakPoint + 1).contains(",")) {
                int fifthBreakPoint = string.indexOf(",", fourthBreakPoint + 1);
                int startIndexNameItem = string.indexOf("\"", fifthBreakPoint);
                int endIndexNameItem = string.indexOf("\"", startIndexNameItem + 1);
                while (true) {
                    int lastElement = string.indexOf(",", endIndexNameItem + 2);
                    String nameItem = string.substring(startIndexNameItem, endIndexNameItem);
                    int weight;
//                    System.out.println(strings.get(i).substring(fourthBreakPoint, fourthBreakPoint + 2));
//                    System.out.println(strings.get(i).substring(endIndexNameItem, endIndexNameItem + 3));
//                    System.out.println(i);
                    if (lastElement > 0) {
                        weight = Integer.valueOf(string.substring(endIndexNameItem + 2, lastElement));
                        WeightDrop weightDrop = new WeightDrop(nameItem, weight);
                    } else {
                        weight = Integer.valueOf(string.substring(endIndexNameItem + 2));
                        WeightDrop weightDrop = new WeightDrop(nameItem, weight);
                    }
                    WeightDrop drop = new WeightDrop(nameItem, weight);
                    monsterDrops.add(drop);

                    if (lastElement > 0) {
                        startIndexNameItem = string.indexOf("\"", lastElement - 1) + 1;
                        endIndexNameItem = string.indexOf("\"", startIndexNameItem);
                    } else {
                        break;
                    }
                }
            }
            String nameMonster = string.substring(startIndName, endIndName);
            int maxHp = Integer.valueOf(string.substring(endIndName + 2, firstBreakPoint));
            int hp = Integer.valueOf(string.substring(firstBreakPoint + 1, secondBreakPoint));
            int minStr = Integer.valueOf(string.substring(secondBreakPoint + 1, thirdBreakPoint));
            int maxStr = Integer.valueOf(string.substring(thirdBreakPoint + 1, fourthBreakPoint));
            int exp;
            if (string.substring(fourthBreakPoint + 1).contains(",")) {
                exp = Integer.valueOf(string.substring(fourthBreakPoint + 1, string.indexOf(",", fourthBreakPoint + 1)));
            } else {
                exp = Integer.valueOf(string.substring(fourthBreakPoint + 1));
            }
            NakedGrindCharacter monster = new NakedGrindCharacter(nameMonster, maxHp, hp, minStr, maxStr, exp, monsterDrops);
            monsters.add(monster);
        }
    }
    public List<NakedGrindCharacter> monstersList (){
        return Collections.unmodifiableList(monsters);
    }
}
