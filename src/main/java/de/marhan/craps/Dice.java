package de.marhan.craps;

public class Dice {

    private RandomMachine randomMachine;

    private static final int MAX_VALUE = 6;

    public Dice() {
        this(new RandomMachine());
    }

    public Dice(RandomMachine randomMachine) {
        this.randomMachine = randomMachine;
    }

    public int nextValue() {
        return randomMachine.random(MAX_VALUE);
    }
}
