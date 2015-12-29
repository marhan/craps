package de.marhan.craps.round;

import de.marhan.craps.ComeOut;

import java.util.List;

public enum Scoring {

    WINS,
    LOSE,
    OPEN;

    public static Scoring determine(List<Round> rounds) {

        if (rounds.size() == 1) {
            Round firstRound = rounds.get(0);
            ComeOut scoring = firstRound.getComeOut();
            return Scoring.determineForFirstRound(scoring);
        }

        if (getLastRound(rounds).getSum() == 7) {
            return Scoring.LOSE;
        }

        if (areLastToRoundsSumEqual(rounds)) {
            return Scoring.WINS;
        }

        return Scoring.OPEN;
    }

    private static Scoring determineForFirstRound(ComeOut comeOut) {

        switch (comeOut) {
            case CRAPS:
                return Scoring.LOSE;
            case NATURAL:
                return Scoring.WINS;
            case POINT:
                return Scoring.OPEN;
        }

        throw new IllegalArgumentException(comeOut + " is not supported!");
    }

    private static boolean areLastToRoundsSumEqual(List<Round> rounds) {
        Round lastRound = getLastRound(rounds);
        return lastRound.hasPassThePoint();
    }

    private static Round getLastRound(List<Round> rounds) {
        return rounds.get(rounds.size() - 1);
    }
}
