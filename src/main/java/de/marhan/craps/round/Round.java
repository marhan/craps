package de.marhan.craps.round;

import de.marhan.craps.ComeOut;
import de.marhan.craps.Dice;
import de.marhan.craps.Player;

import java.util.Set;

public abstract class Round {

    int sum;
    Player shooter;

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getSum() {
        return sum;
    }

    public void setShooter(Player shooter) {
        this.shooter = shooter;
    }

    public Player getShooter() {
        return shooter;
    }

    public abstract String buildMessage();

    public abstract ComeOut getComeOut();

    public abstract Round play(Player shooter, Set<Dice> dices);

    public abstract boolean isComeOut();

    public abstract boolean hasPassThePoint();

}
