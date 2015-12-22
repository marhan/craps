package de.marhan.craps;

public enum GameScoring {

    WINS,
    LOSE,
    OPEN;

    public static GameScoring determineFirstRound(RoundScoring roundScoring) {

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
}
