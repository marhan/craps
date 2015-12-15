package de.marhan.craps;

import java.util.Random;

public class RandomMachine {

    public int random(int rangeBorder) {
        return new Random().nextInt(rangeBorder);
    }
}
