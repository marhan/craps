package de.marhan.craps;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public enum ComeOut {

    CRAPS(2, 3, 12),
    NATURAL(7, 11),
    POINT(4, 5, 6, 8, 9, 10);

    private final Integer[] sums;

    ComeOut(Integer... sums) {
        this.sums = sums;
    }

    public static ComeOut determine(int sum) {
        Predicate<ComeOut> predicate = score -> Arrays.asList(score.sums).contains(sum);
        Optional<ComeOut> optional = Stream.of(ComeOut.values()).filter(predicate).findAny();
        if (optional.isPresent()) {
            return optional.get();
        }

        throw new IllegalArgumentException(sum + " is not in range");
    }

}
