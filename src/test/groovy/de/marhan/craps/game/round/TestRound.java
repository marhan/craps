package de.marhan.craps.game.round;

import de.marhan.craps.Player;

class TestRound implements Round {

    @Override
    public int getSum() {
        return 0;
    }

    @Override
    public Round play(Player shooter) {
        return null;
    }

    @Override
    public String buildMessage() {
        return "testMessage";
    }
}
