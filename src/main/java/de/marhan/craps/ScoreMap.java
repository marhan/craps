package de.marhan.craps;

import java.util.HashMap;
import java.util.Map;

import static de.marhan.craps.Score.Crap;
import static de.marhan.craps.Score.Natural;

public class ScoreMap {

    public static Map<Integer, Score> SCORES;

    static {
        SCORES = new HashMap<>();

        put(7, Natural);
        put(11, Natural);

        put(2, Crap);
        put(3, Crap);
        put(12, Crap);

        put(4, Score.Crap);
    }

    private static void put(int key, Score score) {
        SCORES.put(key, score);
    }

}
