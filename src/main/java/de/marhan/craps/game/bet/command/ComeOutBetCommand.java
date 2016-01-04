package de.marhan.craps.game.bet.command;

import de.marhan.craps.game.bet.BetResult;
import de.marhan.craps.game.bet.BetType;
import de.marhan.craps.game.round.ComeOutRound;
import de.marhan.craps.game.round.Round;

import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class ComeOutBetCommand implements BetCommand<ComeOutRound> {

    private final ComeOutBetDefinition[] definitions = {
            new ComeOutBetDefinition(BetResult.WINS, BetType.PASS_LINE, 7, 11),
            new ComeOutBetDefinition(BetResult.LOSES, BetType.PASS_LINE, 2, 3, 12),
            new ComeOutBetDefinition(BetResult.LOSES, BetType.DO_NOT_PASS_LINE, 7, 11),
            new ComeOutBetDefinition(BetResult.WINS, BetType.DO_NOT_PASS_LINE, 2, 3),
            new ComeOutBetDefinition(BetResult.NETHER_WIN_NOR_LOSE, BetType.DO_NOT_PASS_LINE, 12)
    };

    @Override
    public BetResult determineBetResult(BetType betType, ComeOutRound round) {

        int sum = round.getSum();

        return Stream.of(definitions)
                .filter(criteriaFulfilled(betType, sum))
                .findFirst()
                .orElseThrow(() -> createException(betType, sum))
                .getBetResult();
    }

    private IllegalArgumentException createException(BetType betType, int sum) {
        return new IllegalArgumentException(String.format("betType:%s and sum:%s is not supported!", betType, sum));
    }

    private Predicate<ComeOutBetDefinition> criteriaFulfilled(BetType betType, int sum) {
        return definition -> definition.getBetType().equals(betType) && asList(definition.getSums()).contains(sum);
    }

    @Override
    public boolean isResponsible(Round round) {
        return round instanceof ComeOutRound;
    }
}
