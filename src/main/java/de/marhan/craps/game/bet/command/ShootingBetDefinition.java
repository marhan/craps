package de.marhan.craps.game.bet.command;

import de.marhan.craps.game.bet.BetResult;
import de.marhan.craps.game.bet.BetType;
import de.marhan.craps.game.round.ShootingResult;

class ShootingBetDefinition {

    private final BetType betType;
    private final BetResult betResult;
    private final ShootingResult shootingResult;

    public ShootingBetDefinition(BetResult betResult, BetType betType, ShootingResult shootingResult) {
        this.betType = betType;
        this.betResult = betResult;
        this.shootingResult = shootingResult;
    }

    public BetType getBetType() {
        return betType;
    }

    public BetResult getBetResult() {
        return betResult;
    }

    public ShootingResult getShootingResult() {
        return shootingResult;
    }
}
