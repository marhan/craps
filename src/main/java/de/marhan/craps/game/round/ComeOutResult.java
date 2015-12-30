package de.marhan.craps.game.round;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public enum ComeOutResult {

    CRAPS(2, 3, 12),
    NATURAL(7, 11),
    POINT(4, 5, 6, 8, 9, 10),
    NOT_DEFINED();

    private final Integer[] sums;

    ComeOutResult(Integer... sums) {
        this.sums = sums;
    }

    public static ComeOutResult determine(int sum) {

        Optional<ComeOutResult> optional = Stream.of(ComeOutResult.values())
                .filter(score -> Arrays.asList(score.sums).contains(sum))
                .findAny();

        optional.orElseThrow(() -> new IllegalArgumentException(sum + " is not in range"));

        return optional.get();
    }

}
