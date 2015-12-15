package de.marhan.craps;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Set;

public class Player {

    private int number;

    public Player(int number) {
        this.number = number;
    }

    public int throwDices(Set<Dice> dices) {
        return dices.stream().map(dice -> dice.nextValue()).reduce(0, (a, b) -> a + b);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String buildMessage() {
        return String.format("player %s", number);
    }
}
