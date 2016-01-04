package de.marhan.craps;


import de.marhan.craps.game.Games;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.EMPTY;


public class Craps {

    private static final Logger LOG = LogManager.getLogger(Craps.class);
    private final Config config;

    public Craps(Config config) {
        this.config = config;
    }

    public Games play() {

        Set<Die> dice = new HashSet<>();
        dice.add(new Die());
        dice.add(new Die());

        List<Player> players = new ArrayList<>();
        players.add(new Player(1, config.getInitialAccount()));
        players.add(new Player(2, config.getInitialAccount()));
        players.add(new Player(3, config.getInitialAccount()));

        return play(players, dice);
    }

    public Games play(List<Player> players, Set<Die> dice) {

        Games games = new Games(dice, players);
        games.playGames(config.getMaxGames());

        return games;
    }

    public static void main(String[] args) {
        LOG.info(format("%n---> Start Game <---"));
        LOG.info(EMPTY);

        Craps craps = new Craps(new Config());
        Games games = craps.play();

        LOG.info(games.buildMessage());
        LOG.info(format("---> Game Over <---"));

    }

}
