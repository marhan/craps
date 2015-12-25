package de.marhan.craps;

import java.util.List;

public enum GameScoring {

    WINS,
    LOSE,
    OPEN;

    public static GameScoring determine(List<Round> rounds) {

        if (rounds.size() == 1) {
            Round firstRound = rounds.get(0);
            RoundScoring scoring = firstRound.getRoundScoring();
            return GameScoring.determineForFirstRound(scoring);
        }

        if (getLastRound(rounds).getSum() == 7) {
            return GameScoring.LOSE;
        }

        if (areLastToRoundsSumEqual(rounds)) {
            return GameScoring.WINS;
        }

        return GameScoring.OPEN;
    }

    private static GameScoring determineForFirstRound(RoundScoring roundScoring) {

        switch (roundScoring) {
            case CRAP:
                return GameScoring.LOSE;
            case NATURAL:
                return GameScoring.WINS;
            case POINT:
                return GameScoring.OPEN;
        }

        throw new IllegalArgumentException(roundScoring + " is not supported!");
    }

    private static boolean areLastToRoundsSumEqual(List<Round> rounds) {
        Round lastRound = getLastRound(rounds);
        Round nextToLastRound = rounds.get(rounds.size() - 2);
        return lastRound.getSum() == nextToLastRound.getSum();
    }

    private static Round getLastRound(List<Round> rounds) {
        return rounds.get(rounds.size() - 1);
    }
}
