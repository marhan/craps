package de.marhan.craps.game.round;

import de.marhan.craps.Die;
import de.marhan.craps.Player;
import de.marhan.craps.util.DomainObject;

import java.util.Set;

import static java.lang.String.format;

public class ComeOutRound extends DomainObject implements Round {

    private final Set<Die> dice;
    private int sum;
    private ComeOutResult comeOutResult;

    public ComeOutRound(Set<Die> dice) {
        this.dice = dice;
    }

    @Override
    public int getSum() {
        return sum;
    }

    public ComeOutResult getComeOutResult() {
        return comeOutResult;
    }

    public Round play(Player shooter) {
        this.sum = shooter.rollDice(this.dice);
        this.comeOutResult = ComeOutResult.determine(sum);
        return this;
    }

    @Override
    public String buildMessage() {
        return "Shooter" + format(" come-out with %s (%s)", sum, comeOutResult);
    }

}
