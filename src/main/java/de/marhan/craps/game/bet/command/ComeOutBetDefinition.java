package de.marhan.craps.game.bet.command;

import de.marhan.craps.game.bet.BetResult;
import de.marhan.craps.game.bet.BetType;

class ComeOutBetDefinition {

    private final BetType betType;
    private final BetResult betResult;
    private final Integer[] sums;

    public ComeOutBetDefinition(BetResult betResult, BetType betType, Integer... sums) {
        this.betType = betType;
        this.betResult = betResult;
        this.sums = sums;
    }

    public BetType getBetType() {
        return betType;
    }

    public BetResult getBetResult() {
        return betResult;
    }

    public Integer[] getSums() {
        return sums;
    }
}
