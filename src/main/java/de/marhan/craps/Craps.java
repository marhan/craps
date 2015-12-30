package de.marhan.craps;


import de.marhan.craps.game.Games;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.apache.commons.lang3.StringUtils.EMPTY;


public class Craps {

    private static final Logger LOG = LogManager.getLogger(Craps.class);

    public Games play() {

        Set<Die> dice = new HashSet<>();
        dice.add(new Die());
        dice.add(new Die());

        List<Player> players = new ArrayList<>();
        players.add(new Player(1));
        players.add(new Player(2));
        players.add(new Player(3));

        return play(players, dice);
    }

    public Games play(List<Player> players, Set<Die> dice) {
        Games games = new Games(dice);
        players.stream().forEach(p -> games.playGame(p));
        return games;
    }

    public static void main(String[] args) {
        LOG.info("---> Start Game <---");
        LOG.info(EMPTY);

        Craps craps = new Craps();
        Games games = craps.play();

        LOG.info(games.buildMessage());
        LOG.info("---> Game Over <---");

    }

}
