package de.marhan.craps;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Result {

    private List<Round> rounds = new ArrayList<>();

    public Result(List<Round> rounds) {
        this.rounds = rounds;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String buildMessage() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s rounds played:%n", rounds.size()));
        builder.append(rounds.stream().map(r -> buildRoundString(r)).collect(Collectors.joining()));
        return builder.toString();

    }

    private String buildRoundString(Round r) {
        return String.format("round %s: %s%n", rounds.indexOf(r) + 1, r.buildMessage());
    }

}
