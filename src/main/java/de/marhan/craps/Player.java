package de.marhan.craps;

import de.marhan.craps.util.DomainObject;

import java.math.BigDecimal;
import java.util.Set;

public class Player extends DomainObject {

    private final BigDecimal betAmount = BigDecimal.ONE;
    private final int number;
    private BigDecimal account = BigDecimal.ZERO;


    public Player(int number, BigDecimal account) {
        this.number = number;
        this.account = account;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public int getNumber() {
        return number;
    }

    public boolean isAccountSufficientForBet() {
        return account.subtract(betAmount).compareTo(BigDecimal.ZERO) >= 0;
    }

    public int rollDice(Set<Die> dices) {
        return dices.stream().map(dice -> dice.nextValue()).reduce(0, (a, b) -> a + b);
    }

    public String buildMessage() {
        return String.format("player %s", number);
    }

    public void applyBettingDebt(BigDecimal bettingDebt) {
        account = account.add(bettingDebt);
    }
}
