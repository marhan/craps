package de.marhan.craps.round;

import de.marhan.craps.ComeOut;
import de.marhan.craps.Dice;
import de.marhan.craps.Player;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Set;

import static java.lang.String.format;

public class ShootingRound extends Round {

    private int shootingForSum;

    public ShootingRound(int shootingForSum) {
        this.shootingForSum = shootingForSum;
    }

    public void setShootingForSum(int shootingForSum) {
        this.shootingForSum = shootingForSum;
    }

    @Override
    public Round play(Player shooter, Set<Dice> dices) {
        this.sum = shooter.rollDices(dices);
        this.shooter = shooter;
        return this;
    }

    @Override
    public boolean isComeOut() {
        return false;
    }

    public boolean hasPassThePoint() {
        return sum == shootingForSum;
    }

    private boolean isSeven() {
        return sum == 7;
    }

    public String buildMessage() {
        StringBuilder builder = new StringBuilder();
        builder.append(format("Shooter (%s)", shooter.buildMessage()));
        builder.append(format(" is shooting for %s and the roll is %s", shootingForSum, sum));

        if (hasPassThePoint()) {
            builder.append(" (Pass)");
        }

        if (isSeven()) {
            builder.append(" (Don't Pass)");
        }

        return builder.toString();
    }

    @Override
    public ComeOut getComeOut() {
        return null;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
