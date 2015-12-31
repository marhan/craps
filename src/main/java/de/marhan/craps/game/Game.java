package de.marhan.craps.game;

import de.marhan.craps.Die;
import de.marhan.craps.Player;
import de.marhan.craps.game.round.Round;
import de.marhan.craps.game.round.Rounds;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Game {

    private Player shooter;
    private Rounds rounds;
    private final Set<Die> dice;

    public Game(Set<Die> dice) {
        this.dice = dice;
    }

    public Game play(Player shooter) {
        this.shooter = shooter;
        this.rounds = new Rounds(this.dice);
        this.rounds.play(shooter, this.dice);
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String buildMessage() {
        StringBuilder builder = new StringBuilder();
        builder.append(buildHeadingMessage());
        builder.append(buildBodyMessage());
        return builder.toString();

    }

    private String buildHeadingMessage() {
        String format = "%s rounds played, %s %s:%n";

        return String.format(format
                , rounds.getPlayedRounds().size()
                , shooter.buildMessage()
                , rounds.getScoring());
    }

    public Scoring getScoring() {
        return rounds.getScoring();
    }

    private String buildBodyMessage() {
        List<Round> playedRounds = rounds.getPlayedRounds();
        return playedRounds.stream()
                .map(round -> String.format("Round %s: %s", determineIndex(playedRounds, round), round.buildMessage()))
                .collect(Collectors.joining("\n"));
    }

    private int determineIndex(List<Round> rounds, Round round) {
        return rounds.indexOf(round) + 1;
    }

}
