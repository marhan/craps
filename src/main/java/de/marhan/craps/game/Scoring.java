package de.marhan.craps.game;

import de.marhan.craps.game.round.ComeOutResult;
import de.marhan.craps.game.round.Round;
import de.marhan.craps.game.round.ShootingResult;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public enum Scoring {

    WINS(ComeOutResult.NATURAL, ShootingResult.HIT_THE_POINT),
    LOSES(ComeOutResult.CRAPS, ShootingResult.SEVEN_OUT),
    NEXT_ROUND(ComeOutResult.POINT, ShootingResult.MISS_THE_POINT);

    private final ComeOutResult comeOutResult;
    private final ShootingResult shootingResult;

    Scoring(ComeOutResult comeOutResult, ShootingResult shootingResult) {
        this.comeOutResult = comeOutResult;
        this.shootingResult = shootingResult;
    }

    public static Scoring determine(List<Round> rounds) {

        if (rounds.size() == 1) {
            return determineFor(rounds.get(0).getComeOutResult());
        }

        return determineFor(getLastRound(rounds).getShootingResult());

    }

    private static Scoring determineFor(ComeOutResult comeOutResult) {

        Optional<Scoring> optional = Stream.of(Scoring.values())
                .filter(scoring -> scoring.comeOutResult.equals(comeOutResult))
                .findFirst();

        optional.orElseThrow(() -> new IllegalArgumentException(comeOutResult + " is not supported!"));

        return optional.get();


    }

    private static Scoring determineFor(ShootingResult shootingResult) {

        Optional<Scoring> optional = Stream.of(Scoring.values())
                .filter(scoring -> scoring.shootingResult.equals(shootingResult))
                .findFirst();

        optional.orElseThrow(() -> new IllegalArgumentException(shootingResult + " is not supported!"));

        return optional.get();
    }

    private static Round getLastRound(List<Round> rounds) {
        return rounds.get(rounds.size() - 1);
    }
}
