package de.marhan.craps.game.round;

import de.marhan.craps.Player;
import de.marhan.craps.util.Message;

public interface Round extends Message {

    int getSum();

    Round play(Player shooter);

    default ComeOutResult getComeOutResult() {
        return ComeOutResult.NOT_DEFINED;
    }

    default ShootingResult getShootingResult() {
        return ShootingResult.NOT_DEFINED;
    }

}
