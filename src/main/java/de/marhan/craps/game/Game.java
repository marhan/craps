package de.marhan.craps.game;

import de.marhan.craps.Die;
import de.marhan.craps.Player;
import de.marhan.craps.game.bet.BetType;
import de.marhan.craps.game.bet.Bets;
import de.marhan.craps.game.round.Rounds;
import de.marhan.craps.util.DomainObject;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static java.util.Collections.singletonList;

public class Game extends DomainObject {

    private final Bets bets = new Bets();
    private final Set<Die> dice;

    private Player shooter;
    private Rounds rounds;
    private Scoring scoring;
    private Accounts currentAccounts;

    public Game(Set<Die> dice) {
        this.dice = dice;
    }

    public Scoring getScoring() {
        return scoring;
    }

    public Game play(Player shooter, List<Player> others) {
        this.shooter = shooter;

        currentAccounts = new Accounts(shooter, others);
        currentAccounts.snapshot();

        bets.createBets(singletonList(shooter), BigDecimal.ONE, BetType.PASS_LINE);
        bets.createBets(others, BigDecimal.ONE, BetType.DO_NOT_PASS_LINE);

        rounds = new Rounds(dice);
        scoring = rounds.play(shooter);

        bets.determineAndApplyBets(rounds.getLastPlayedRound());

        return this;
    }

    public String buildMessage() {
        return buildHeadingMessage()
                + currentAccounts.buildMessage()
                + rounds.buildMessage()
                + bets.buildMessage();
    }

    private String buildHeadingMessage() {
        String format = "%s round(s) played, %s %s:%n";

        return String.format(format
                , rounds.getPlayedRounds().size()
                , shooter.buildMessage()
                , scoring);
    }


}
