package de.marhan.craps;

import de.marhan.craps.round.Round;
import de.marhan.craps.round.Scoring;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Result {

    private List<Round> rounds = new ArrayList<>();
    private final Scoring scoring;

    public Result(List<Round> rounds, Scoring scoring) {
        this.rounds = rounds;
        this.scoring = scoring;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String buildMessage() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s rounds played, shooter %s:%n", rounds.size(), scoring));
        builder.append(rounds.stream().map(r -> buildRoundString(r)).collect(Collectors.joining("\n")));
        return builder.toString();

    }

    private String buildRoundString(Round round) {
        return String.format("Round %s: %s", rounds.indexOf(round) + 1, round.buildMessage());
    }

}
