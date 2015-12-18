package de.marhan.craps;

import java.util.Random;

public class Dice {

    private Random r = new Random();

    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 6;

    public int nextValue() {
        return r.nextInt((MAX_VALUE - MIN_VALUE) + 1) + MIN_VALUE;
    }
}
