package de.marhan.craps.round;

import de.marhan.craps.ComeOut;
import de.marhan.craps.Dice;
import de.marhan.craps.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public class Rounds {

    private Scoring scoring;
    private List<Round> playedRounds = new ArrayList<>();

    public Scoring getScoring() {
        return scoring;
    }

    public List<Round> getPlayedRounds() {
        return playedRounds;
    }

    public void play(Player shooter, Set<Dice> dices) {
        playedRounds = playRecursive(shooter, dices, new ArrayList<>());
        scoring = Scoring.determine(playedRounds);
    }

    private List<Round> playRecursive(Player shooter, Set<Dice> dices, List<Round> rounds) {
        Round round = createNewRound(rounds);
        rounds.add(round.play(shooter, dices));

        if (Scoring.OPEN.equals(Scoring.determine(rounds))) {
            playRecursive(shooter, dices, rounds);
        }

        return rounds;
    }

    private static Round createNewRound(List<Round> rounds) {
        if (!hasComeOutRound(rounds)) {
            return new ComeOutRound();
        }
        return new ShootingRound(getSumOfPoint(rounds));
    }

    private static boolean hasComeOutRound(List<Round> rounds) {
        return !rounds.isEmpty() && rounds.stream().anyMatch(r -> r.isComeOut());
    }

    private static int getSumOfPoint(List<Round> rounds) {
        Predicate<Round> predicate = r -> ComeOut.POINT.equals(r.getComeOut());
        Optional<Integer> optional = rounds.stream().filter(predicate).findFirst().map(r -> r.getSum());
        return optional.get();
    }

}
