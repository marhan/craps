package de.marhan.craps.game;

import de.marhan.craps.util.DomainObject;
import de.marhan.craps.Die;
import de.marhan.craps.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Games extends DomainObject {

    private final List<Game> gamesPlayed = new ArrayList<>();
    private final Set<Die> dice;
    private Accounts accounts;

    public Games(Set<Die> dice) {
        this.dice = dice;
    }

    public void play(Player shooter, List<Player> others) {

        accounts = new Accounts(shooter, others);

        Game game = new Game(this.dice);
        game.play(shooter, others);
        gamesPlayed.add(game);

        accounts.snapshot();
    }

    public String buildMessage() {
        return buildGamesMessage() + buildAccountMessage();
    }

    private String buildAccountMessage() {
        return String.format("%n%n---> Final account of players <---%n%n")
                + accounts.buildMessage();
    }

    private String buildGamesMessage() {
        return gamesPlayed.stream()
                .map(game -> game.buildMessage())
                .collect(Collectors.joining(String.format("%n%n---> New Game <---%n%n")));
    }

}
