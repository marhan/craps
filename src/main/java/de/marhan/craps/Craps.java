package de.marhan.craps;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.apache.commons.lang3.StringUtils.EMPTY;


public class Craps {

    private static Logger LOG = LogManager.getLogger(Craps.class);

    private List<Round> rounds = new ArrayList<>();
    private Set<Player> players = new HashSet<>();

    public List<Round> getRounds() {
        return rounds;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public Result play() {

        Set<Dice> dices = new HashSet<>();
        dices.add(new Dice());
        dices.add(new Dice());

        players.add(new Player(1));
        players.add(new Player(2));
        players.add(new Player(3));

        return playWith(players, dices);

    }

    public Result playWith(Set<Player> players, Set<Dice> dices) {
        Player shooter = players.stream().findFirst().get();
        Round round = new Round().play(shooter, dices);
        GameScoring gameScoring = determineScoringBy(round);
        return new Result(rounds, gameScoring);
    }

    private GameScoring determineScoringBy(Round round) {
        rounds.add(round);
        if (rounds.size() == 1) {
            return GameScoring.determineFirstRound(round.getRoundScoring());
        }
        return GameScoring.OPEN;
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
