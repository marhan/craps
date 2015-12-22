package de.marhan.craps;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public enum RoundScoring {

    CRAP(2, 3, 12),
    NATURAL(7, 11),
    POINT(4, 5, 6, 8, 9, 10);

    private Integer[] sums;

    RoundScoring(Integer... sums) {
        this.sums = sums;
    }

    public static RoundScoring determine(int sum) {
        Predicate<RoundScoring> predicate = score -> Arrays.asList(score.sums).contains(sum);
        Optional<RoundScoring> optional = Stream.of(RoundScoring.values()).filter(predicate).findAny();
        if (optional.isPresent()) {
            return optional.get();
        }

        throw new IllegalArgumentException(sum + " is not in range");
    }

}
