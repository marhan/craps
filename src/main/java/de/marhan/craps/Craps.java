package de.marhan.craps;


import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

import static org.apache.commons.lang3.StringUtils.EMPTY;


public class Craps {

    private static Logger LOG = LogManager.getLogger(Craps.class);

    public Result play() {

        Set<Dice> dices = new HashSet<>();
        dices.add(new Dice());
        dices.add(new Dice());

        Set<Player> players = new HashSet<>();
        players.add(new Player(1));
        players.add(new Player(2));

        return play(dices, players);

    }

    public Result play(Set<Dice> dices, Set<Player> players) {

        Result result = new Result();

        Player player = players.stream().findFirst().get();
        int sum = player.throwDices(dices);
        result.addRound(player, sum);

        return result;
    }

    public static void main(String[] args) {
        LOG.info("---> Start Playing Craps <---");
        LOG.info(EMPTY);

        Craps craps = new Craps();
        Result result = craps.play();

        LOG.info(result.buildMessage());
        LOG.info("--> End Playing Craps <---");

    }

}
