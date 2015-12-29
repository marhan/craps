package de.marhan.craps;

import java.util.Random;

public class Dice {

    private final Random random = new Random();

    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 6;

    public int nextValue() {
        return random.nextInt((MAX_VALUE - MIN_VALUE) + 1) + MIN_VALUE;
    }
}
