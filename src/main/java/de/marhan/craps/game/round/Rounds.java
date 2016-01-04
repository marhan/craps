package de.marhan.craps.game.round;

import de.marhan.craps.Die;
import de.marhan.craps.Player;
import de.marhan.craps.game.Scoring;
import de.marhan.craps.util.DomainObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static de.marhan.craps.game.round.ComeOutResult.POINT;
import static de.marhan.craps.util.Constants.LINE_ENDING;

public class Rounds extends DomainObject {

    private List<Round> playedRounds = new ArrayList<>();
    private final Set<Die> dice;

    public Rounds(Set<Die> dice) {
        this.dice = dice;
    }

    public List<Round> getPlayedRounds() {
        return playedRounds;
    }

    public Scoring play(Player shooter) {
        playedRounds = playRecursive(shooter, new ArrayList<>());
        return Scoring.determine(playedRounds);
    }

    public Round getLastPlayedRound() {
        return playedRounds.get(playedRounds.size() - 1);
    }

    public String buildMessage() {

        String message = playedRounds.stream()
                .map(round -> buildRoundMessage(round))
                .collect(Collectors.joining(LINE_ENDING));

        return message + LINE_ENDING;
    }

    private List<Round> playRecursive(Player shooter, List<Round> rounds) {

        Round round = createNewRound(rounds);
        rounds.add(round.play(shooter));

        if (isNextRoundNeeded(rounds)) {
            playRecursive(shooter, rounds);
        }

        return rounds;
    }

    private boolean isNextRoundNeeded(List<Round> rounds) {
        return Scoring.NEXT_ROUND.equals(Scoring.determine(rounds));
    }

    private Round createNewRound(List<Round> rounds) {
        if (!hasComeOutRound(rounds)) {
            return new ComeOutRound(dice);
        }
        return new ShootingRound(determineShootingForSum(rounds), dice);
    }

    private boolean hasComeOutRound(List<Round> rounds) {
        return !rounds.isEmpty()
                && rounds.stream()
                .anyMatch(round -> (round instanceof ComeOutRound));
    }

    private int determineShootingForSum(List<Round> rounds) {

        Optional<Integer> optional = rounds.stream()
                .filter(round -> POINT.equals(round.getComeOutResult()))
                .findFirst()
                .map(round -> round.getSum());

        optional.orElseThrow(() -> createException());

        return optional.get();
    }

    private IllegalArgumentException createException() {
        return new IllegalArgumentException("ShootingForSum not found!");
    }

    private String buildRoundMessage(Round round) {
        return String.format("Round %s: %s", determineIndex(playedRounds, round), round.buildMessage());
    }

    private int determineIndex(List<Round> rounds, Round round) {
        return rounds.indexOf(round) + 1;
    }


}
