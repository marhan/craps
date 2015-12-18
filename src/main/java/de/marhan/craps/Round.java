package de.marhan.craps;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Round {

    private int sum;
    private Player player;
    private Score score;

    public Round(Player player, int sum, Score score) {
        this.player = player;
        this.sum = sum;
        this.score = score;
    }

    public Player getPlayer() {
        return player;
    }

    public int getSum() {
        return sum;
    }

    public Score getScore() {
        return score;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String buildMessage() {
        StringBuilder builder = new StringBuilder();
        builder.append(player.buildMessage());
        builder.append(String.format(" throws %s and has a %s", sum, score));
        return builder.toString();
    }
}
