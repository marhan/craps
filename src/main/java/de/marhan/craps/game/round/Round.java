package de.marhan.craps.game.round;

import de.marhan.craps.Player;

public interface Round {

    String buildMessage();

    int getSum();

    Round play(Player shooter);

    default ComeOutResult getComeOutResult() {
        return ComeOutResult.NOT_DEFINED;
    }

    default ShootingResult getShootingResult() {
        return ShootingResult.NOT_DEFINED;
    }

}
