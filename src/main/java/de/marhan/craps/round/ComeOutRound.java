package de.marhan.craps.round;

import de.marhan.craps.ComeOut;
import de.marhan.craps.Dice;
import de.marhan.craps.Player;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Set;

import static java.lang.String.format;

public class ComeOutRound extends Round {

    private ComeOut comeOut;

    public void setComeOut(ComeOut comeOut) {
        this.comeOut = comeOut;
    }

    public ComeOut getComeOut() {
        return comeOut;
    }

    public Round play(Player shooter, Set<Dice> dices) {
        this.sum = shooter.rollDices(dices);
        this.comeOut = ComeOut.determine(getSum());
        this.shooter = shooter;
        return this;
    }

    @Override
    public boolean isComeOut() {
        return true;
    }

    @Override
    public boolean hasPassThePoint() {
        return false;
    }

    public String buildMessage() {
        StringBuilder builder = new StringBuilder();
        builder.append(format("Shooter (%s)", shooter.buildMessage()));
        builder.append(format(" come-out with %s (%s)", sum, comeOut));
        return builder.toString();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
