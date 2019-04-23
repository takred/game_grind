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
            int startIndName = strings.get(i).indexOf("\"");
            int endIndName = strings.get(i).indexOf("\"", startIndName + 1);
            int firstBreakPoint = strings.get(i).indexOf(",", endIndName + 2);
            int secondBreakPoint = strings.get(i).indexOf(",", firstBreakPoint + 1);
            int thirdBreakPoint = strings.get(i).indexOf(",", secondBreakPoint + 1);
            int fourthBreakPoint = strings.get(i).indexOf(",", thirdBreakPoint + 1);
//            int fifthBreakPoint = strings.get(i).indexOf(",", fourthBreakPoint + 1);
//            NakedGrindCharacter monster;
            List<WeightDrop> monsterDrops = new ArrayList<>();
            if (strings.get(i).substring(fourthBreakPoint + 1).contains(",")){
                int fifthBreakPoint = strings.get(i).indexOf(",", fourthBreakPoint + 1);
                int startIndexNameItem = strings.get(i).indexOf("\"", fifthBreakPoint);
                int endIndexNameItem = strings.get(i).indexOf("\"",startIndexNameItem + 1);
                while (true) {
                    int lastElement = strings.get(i).indexOf(",",endIndexNameItem + 2);
                    String nameItem = strings.get(i).substring(startIndexNameItem, endIndexNameItem);
                    int weight;
//                    System.out.println(strings.get(i).substring(fourthBreakPoint, fourthBreakPoint + 2));
//                    System.out.println(strings.get(i).substring(endIndexNameItem, endIndexNameItem + 3));
//                    System.out.println(i);
                    if(lastElement > 0) {
                        weight = Integer.valueOf(strings.get(i).substring(endIndexNameItem + 2, lastElement));
                        WeightDrop weightDrop = new WeightDrop(nameItem, weight);
                    }else {
                        weight = Integer.valueOf(strings.get(i).substring(endIndexNameItem + 2));
                        WeightDrop weightDrop = new WeightDrop(nameItem, weight);
                    }
                    WeightDrop drop = new WeightDrop(nameItem, weight);
                    monsterDrops.add(drop);

                    if (lastElement > 0){
                        startIndexNameItem = strings.get(i).indexOf("\"",lastElement - 1) + 1;
                        endIndexNameItem = strings.get(i).indexOf("\"",startIndexNameItem);
                    }else {
                        break;
                    }
                }
                String nameMonster = strings.get(i).substring(startIndName, endIndName);
                int maxHp = Integer.valueOf(strings.get(i).substring(endIndName + 2, firstBreakPoint));
                int hp = Integer.valueOf(strings.get(i).substring(firstBreakPoint + 1, secondBreakPoint));
                int minStr = Integer.valueOf(strings.get(i).substring(secondBreakPoint + 1, thirdBreakPoint));
                int maxStr = Integer.valueOf(strings.get(i).substring(thirdBreakPoint + 1, fourthBreakPoint));
                int exp = 0;
                if (strings.get(i).substring(fourthBreakPoint + 1).contains(",")){
                    exp = Integer.valueOf(strings.get(i).substring(fourthBreakPoint + 1, strings.get(i).indexOf(",", fourthBreakPoint + 1)));
                }else {
                    exp = Integer.valueOf(strings.get(i).substring(fourthBreakPoint + 1));
                }
                NakedGrindCharacter monster = new NakedGrindCharacter(nameMonster, maxHp, hp, minStr, maxStr, exp, monsterDrops);
                monsters.add(monster);
            }
        }
    }
    public List<NakedGrindCharacter> monstersList (){
        return Collections.unmodifiableList(monsters);
    }
}
