package de.marhan.craps;


import de.marhan.craps.game.Games;
import org.apache.commons.collections4.ListUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.String.format;
import static java.util.Collections.singletonList;
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
        players.stream().forEach(shooter -> {
            List<Player> others = buildOthers(players, shooter);
            games.play(shooter, others);
        });
        return games;
    }

    public static void main(String[] args) {
        LOG.info(format("%n---> Start Game <---"));
        LOG.info(EMPTY);

        Craps craps = new Craps();
        Games games = craps.play();

        LOG.info(games.buildMessage());
        LOG.info(format("---> Game Over <---"));

    }

    private List<Player> buildOthers(List<Player> players, Player shooter) {
        return ListUtils.subtract(players, singletonList(shooter));
    }

}
