package de.marhan.craps;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.EMPTY;


public class Craps {

    private static Logger LOG = LogManager.getLogger(Craps.class);

    private List<Round> rounds = new ArrayList<>();

    public Result play() {

        Set<Dice> dices = new HashSet<>();
        dices.add(new Dice());
        dices.add(new Dice());

        Set<Player> players = new HashSet<>();
        players.add(new Player(1));
        players.add(new Player(2));

        return playWith(dices, players);

    }

    public Result playWith(Set<Dice> dices, Set<Player> players) {

        Player player = players.stream().findFirst().get();
        Round round = playRound(player, dices);
        rounds.add(round);

        return createResult();
    }

    public Round playRound(Player player, Set<Dice> dices) {
        int sum = player.throwDices(dices);
        return new Round(player, sum, Score.determineScore(sum));
    }

    private Result createResult() {
        return new Result(rounds);
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
