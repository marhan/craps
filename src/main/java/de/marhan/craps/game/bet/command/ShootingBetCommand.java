package de.marhan.craps.game.bet.command;

import de.marhan.craps.game.bet.BetResult;
import de.marhan.craps.game.bet.BetType;
import de.marhan.craps.game.round.Round;
import de.marhan.craps.game.round.ShootingResult;
import de.marhan.craps.game.round.ShootingRound;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class ShootingBetCommand implements BetCommand<ShootingRound> {

    private final ShootingBetDefinition[] definitions = {
            new ShootingBetDefinition(BetResult.LOSES, BetType.PASS_LINE, ShootingResult.SEVEN_OUT),
            new ShootingBetDefinition(BetResult.WINS, BetType.PASS_LINE, ShootingResult.HIT_THE_POINT),
            new ShootingBetDefinition(BetResult.LOSES, BetType.DO_NOT_PASS_LINE, ShootingResult.HIT_THE_POINT),
            new ShootingBetDefinition(BetResult.WINS, BetType.DO_NOT_PASS_LINE, ShootingResult.SEVEN_OUT)
    };

    @Override
    public BetResult determineBetResult(BetType betType, ShootingRound round) {

        ShootingResult shootingResult = round.getShootingResult();

        return Stream.of(definitions)
                .filter(criteriaFulFilled(betType, shootingResult))
                .findFirst()
                .orElseThrow(() -> createException(betType, shootingResult))
                .getBetResult();

    }

    private Predicate<ShootingBetDefinition> criteriaFulFilled(BetType betType, ShootingResult shootingResult) {
        return definition
                -> definition.getBetType().equals(betType)
                && definition.getShootingResult().equals(shootingResult);
    }

    private IllegalArgumentException createException(BetType betType, ShootingResult shootingResult) {
        return new IllegalArgumentException(String.format("betType: %s and shootingResult: %s is not supported!", betType, shootingResult));
    }

    @Override
    public boolean isResponsible(Round round) {
        return round instanceof ShootingRound;
    }

}
