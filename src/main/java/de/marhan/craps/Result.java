package de.marhan.craps;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.HashSet;
import java.util.Set;

public class Result {

    private Set<Round> rounds = new HashSet<Round>();

    public void addRound(Player player, int sum) {
        int number = rounds.size() + 1;
        rounds.add(new Round(number, player, sum));
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String buildMessage() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s rounds played:%n", rounds.size()));

        for (Round round : rounds) {
            builder.append(round.buildMessage());
            builder.append(String.format("%n"));
        }

        return builder.toString();
    }

}
