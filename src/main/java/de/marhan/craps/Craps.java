package de.marhan.craps;


import de.marhan.craps.round.Rounds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.apache.commons.lang3.StringUtils.EMPTY;


public class Craps {

    private static final Logger LOG = LogManager.getLogger(Craps.class);

    public Result play() {

        Set<Dice> dices = new HashSet<>();
        dices.add(new Dice());
        dices.add(new Dice());

        List<Player> players = new ArrayList<>();
        players.add(new Player(1));
        players.add(new Player(2));
        players.add(new Player(3));

        return playWith(players, dices);

    }

    public Result playWith(List<Player> players, Set<Dice> dices) {
        Rounds rounds = new Rounds();
        rounds.play(players.get(0), dices);
        return new Result(rounds.getPlayedRounds(), rounds.getScoring());
    }

    public static void main(String[] args) {
        LOG.info("---> Start Game <---");
        LOG.info(EMPTY);

        Craps craps = new Craps();
        Result result = craps.play();

        LOG.info(result.buildMessage());
        LOG.info("---> Game Over <---");

    }

}
