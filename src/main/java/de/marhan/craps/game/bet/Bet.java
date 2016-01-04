package de.marhan.craps.game.bet;

import de.marhan.craps.Player;
import de.marhan.craps.util.DomainObject;

import java.math.BigDecimal;

class Bet extends DomainObject {

    private final BigDecimal money;
    private final BetType betType;
    private final Player player;

    private BetResult betResult;

    public Bet(BetType betType, BigDecimal money, Player player) {
        this.betType = betType;
        this.money = money;
        this.player = player;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public BetType getBetType() {
        return betType;
    }

    public int getPlayerNumber() {
        return player.getNumber();
    }

    public void update(BetResult betResult, BigDecimal bettingDebt) {
        this.betResult = betResult;
        player.applyBettingDebt(bettingDebt);
    }

    public String buildMessage() {
        String messageFormat = "%s bets %s euro on %s and %s";

        return String.format(messageFormat
                , player.buildMessage()
                , money
                , betType
                , betResult);
    }

}
