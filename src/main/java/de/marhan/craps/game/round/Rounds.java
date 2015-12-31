package de.marhan.craps.game.round;

import de.marhan.craps.Die;
import de.marhan.craps.Player;
import de.marhan.craps.game.Scoring;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Rounds {

    private Scoring scoring;
    private List<Round> playedRounds = new ArrayList<>();
    private final Set<Die> dice;

    public Rounds(Set<Die> dice) {
        this.dice = dice;
    }

    public Scoring getScoring() {
        return scoring;
    }

    public List<Round> getPlayedRounds() {
        return playedRounds;
    }

    public void play(Player shooter, Set<Die> dice) {
        playedRounds = playRecursive(shooter, dice, new ArrayList<>());
        scoring = Scoring.determine(playedRounds);
    }

    private List<Round> playRecursive(Player shooter, Set<Die> dice, List<Round> rounds) {
        Round round = createNewRound(rounds);
        rounds.add(round.play(shooter));

        if (Scoring.OPEN.equals(Scoring.determine(rounds))) {
            playRecursive(shooter, dice, rounds);
        }

        return rounds;
    }

    private Round createNewRound(List<Round> rounds) {
        if (!hasComeOutRound(rounds)) {
            return new ComeOutRound(this.dice);
        }
        return new ShootingRound(determineShootingForSum(rounds), this.dice);
    }

    private boolean hasComeOutRound(List<Round> rounds) {
        return !rounds.isEmpty()
                && rounds.stream()
                .anyMatch(round -> (round instanceof ComeOutRound));
    }

    private int determineShootingForSum(List<Round> rounds) {
        Optional<Integer> optional = rounds.stream()
                .filter(round -> ComeOutResult.POINT.equals(round.getComeOutResult()))
                .findFirst()
                .map(round -> round.getSum());

        optional.orElseThrow(() -> new IllegalArgumentException("ShootingForSum not found!"));

        return optional.get();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
