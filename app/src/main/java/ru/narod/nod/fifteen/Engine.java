package ru.narod.nod.fifteen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by otc on 03.08.2016.
 */
public class Engine {
    //in 2d array: the first [] - lines, the second [] - columns
    private String gameField[][];
    private static boolean orientationChanged;
    //Chronometer chronometer;

    // Initializer (constructor)
    public Engine() {
        gameField = new String[4][4];
        shuffle();

        //initialize the orientationChanged variable
        orientationChanged = false;
    }
    // Shuffles the matrix on the playground
    public void shuffle() {
        Random rnd = new Random();
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 1; i < 15; i++) arrayList.add(String.valueOf(i));
        arrayList.add("");
        arrayList.add("15");
        //shuffles ListArray
        Collections.shuffle(arrayList);
        System.out.println(arrayList);
        //puts the shuffled arrayList to the gameField 2d array
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                gameField[i][j] = arrayList.get(count++);
            }
        }
        //System.out.println(gameField[1][3]);
    }

    //region Getters and Setters
    public String[][] getGameField() {
        return gameField;
    }
    public void setGameField(String val, int line, int column) {
        this.gameField[line][column] = val;
    }

    public boolean getOrientationChanged() {
        return orientationChanged;
    }
    public void setOrientationChanged(boolean data) {
        this.orientationChanged = data;
    }
    //endregion
}
