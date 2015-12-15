package de.marhan.craps;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Round {

    private int number;
    private int sum;
    private Player player;

    public Round(int number, Player player, int sum) {
        this.number = number;
        this.player = player;
        this.sum = sum;
    }

    public Player getPlayer() {
        return player;
    }

    public int getSum() {
        return sum;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String buildMessage() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("round %s: ", number));
        builder.append(player.buildMessage());
        builder.append(String.format(" throws %s", sum));
        return builder.toString();
    }
}
