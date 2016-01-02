package de.marhan.craps.game.round;

import de.marhan.craps.Die;
import de.marhan.craps.Player;
import de.marhan.craps.util.DomainObject;

import java.util.Set;

import static java.lang.String.format;

public class ShootingRound extends DomainObject implements Round {

    private final int shootingForSum;
    private final Set<Die> dice;

    private int sum;
    private ShootingResult shootingResult;

    public ShootingRound(int shootingForSum, Set<Die> dice) {
        this.shootingForSum = shootingForSum;
        this.dice = dice;
    }

    @Override
    public int getSum() {
        return sum;
    }

    public ShootingResult getShootingResult() {
        return shootingResult;
    }

    @Override
    public Round play(Player shooter) {
        this.sum = shooter.rollDice(this.dice);
        this.shootingResult = ShootingResult.determine(sum, shootingForSum);
        return this;
    }

    @Override
    public String buildMessage() {
        StringBuilder builder = new StringBuilder();
        builder.append("Shooter");
        builder.append(format(" is shooting for %s and the roll is %s", shootingForSum, sum));


        if (ShootingResult.HIT_THE_POINT.equals(shootingResult)) {
            builder.append(" (Pass)");
        }

        if (ShootingResult.SEVEN_OUT.equals(shootingResult)) {
            builder.append(" (Don't Pass)");
        }

        return builder.toString();
    }

}
