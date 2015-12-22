package de.marhan.craps;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Result {

    private List<Round> rounds = new ArrayList<>();
    private GameScoring gameScoring;

    public Result(List<Round> rounds, GameScoring gameScoring) {
        this.rounds = rounds;
        this.gameScoring = gameScoring;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String buildMessage() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s rounds played, shooter %s:%n", rounds.size(), gameScoring));
        builder.append(rounds.stream().map(r -> buildRoundString(r)).collect(Collectors.joining()));
        return builder.toString();

    }

    private String buildRoundString(Round r) {
        return String.format("Round %s: %s%n", rounds.indexOf(r) + 1, r.buildMessage());
    }

}
