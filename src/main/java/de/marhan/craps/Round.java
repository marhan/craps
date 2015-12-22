package de.marhan.craps;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Set;

import static java.lang.String.format;

public class Round {

    private int sum;
    private Player shooter;
    private RoundScoring roundScoring;

    public void setSum(int sum) {
        this.sum = sum;
    }

    public void setShooter(Player shooter) {
        this.shooter = shooter;
    }

    public void setRoundScoring(RoundScoring roundScoring) {
        this.roundScoring = roundScoring;
    }

    public Player getShooter() {
        return shooter;
    }

    public int getSum() {
        return sum;
    }

    public RoundScoring getRoundScoring() {
        return roundScoring;
    }

    public Round play(Player shooter, Set<Dice> dices) {
        this.sum = shooter.rollDices(dices);
        this.roundScoring = RoundScoring.determine(sum);
        this.shooter = shooter;
        return this;
    }

    public String buildMessage() {
        StringBuilder builder = new StringBuilder();
        builder.append(format("Shooter (%s)", getShooter().buildMessage()));
        builder.append(format(" rolls %s and has a %s", getSum(), getRoundScoring()));
        return builder.toString();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }


}
