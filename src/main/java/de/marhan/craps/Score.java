package de.marhan.craps;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public enum Score {

    Crap(2, 3, 12),
    Natural(7, 11),
    Point(4, 5, 6, 8, 9, 10);

    private Integer[] pips;

    Score(Integer... pips) {
        this.pips = pips;
    }

    public static Score determineScore(int pip) {
        Predicate<Score> scorePredicate = score -> Arrays.asList(score.pips).contains(pip);
        Optional<Score> scoreOptional = Stream.of(Score.values()).filter(scorePredicate).findAny();
        if (scoreOptional.isPresent()) {
            return scoreOptional.get();
        }

        throw new IllegalArgumentException(pip + " is not in range");
    }

}
