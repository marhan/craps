package de.marhan.craps.game.round;

public enum ShootingResult {

    SEVEN_OUT,
    HIT_THE_POINT,
    MISS_THE_POINT,
    NOT_DEFINED;

    public static ShootingResult determine(int sum, int shootingForSum) {
        if (sum == 7) {
            return SEVEN_OUT;
        }
        if (sum == shootingForSum) {
            return HIT_THE_POINT;
        }
        return MISS_THE_POINT;
    }
}
