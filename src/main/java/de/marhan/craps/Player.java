package de.marhan.craps;

import de.marhan.craps.util.DomainObject;

import java.math.BigDecimal;
import java.util.Set;

public class Player extends DomainObject {

    private final int number;
    private BigDecimal account = new BigDecimal("30");

    public Player(int number) {
        this.number = number;
    }

    public int rollDice(Set<Die> dices) {
        return dices.stream().map(dice -> dice.nextValue()).reduce(0, (a, b) -> a + b);
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

    public String buildMessage() {
        return String.format("player %s", number);
    }
}
