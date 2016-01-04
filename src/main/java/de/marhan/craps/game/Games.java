package de.marhan.craps.game;

import de.marhan.craps.Die;
import de.marhan.craps.Player;
import de.marhan.craps.util.DomainObject;
import org.apache.commons.collections4.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;

public class Games extends DomainObject {

    private final List<Game> gamesPlayed = new ArrayList<>();
    private final Set<Die> dice;
    private final List<Player> players;
    private final Accounts accounts;

    public Games(Set<Die> dice, List<Player> players) {
        this.dice = dice;
        this.players = players;
        accounts = new Accounts(players);
    }

    public List<Game> getGamesPlayed() {
        return gamesPlayed;
    }

    public void playGames(int numberOfGames) {

        List<Player> shooters = createShooters(numberOfGames);

        shooters.stream().map(shooter -> {
            play(shooter, buildOthers(players, shooter));
            return accounts.haveAllSufficientOnAccount();

        }).filter(sufficientOnAccount -> !sufficientOnAccount).findAny();

        accounts.snapshot();

    }

    private List<Player> createShooters(int numberOfGames) {
        List<Player> shooters = new ArrayList<>(numberOfGames);
        for (int gameNumber = 0; gameNumber < numberOfGames; gameNumber++) {
            int listPosition = gameNumber % players.size();
            shooters.add(players.get(listPosition));
        }
        return shooters;
    }

    private void play(Player shooter, List<Player> others) {
        Game game = new Game(this.dice);
        game.play(shooter, others);
        gamesPlayed.add(game);
    }

    public String buildMessage() {
        return buildGamesMessage() + buildAccountMessage();
    }

    private List<Player> buildOthers(List<Player> players, Player shooter) {
        return ListUtils.subtract(players, singletonList(shooter));
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
