package de.marhan.craps.game.bet;

import de.marhan.craps.Player;
import de.marhan.craps.game.bet.command.BetCommand;
import de.marhan.craps.game.bet.command.ComeOutBetCommand;
import de.marhan.craps.game.bet.command.ShootingBetCommand;
import de.marhan.craps.game.round.Round;
import de.marhan.craps.util.DomainObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static de.marhan.craps.util.Constants.LINE_ENDING;

public class Bets extends DomainObject {

    @SuppressWarnings("unchecked")
    private final BetCommand<Round>[] commands = new BetCommand[]{
            new ComeOutBetCommand(),
            new ShootingBetCommand()
    };

    private final List<Bet> placedBets = new ArrayList<>();

    public void createBets(List<Player> players, BigDecimal money, BetType betType) {
        placedBets.addAll(createBetsFor(players, money, betType));
    }

    public void determineAndApplyBets(Round lastRound) {
        placedBets.stream().forEach(bet -> determineAndApplyBet(bet, lastRound));
    }

    private void determineAndApplyBet(Bet bet, Round lastRound) {
        BetResult betResult = determineBetResult(lastRound, bet.getBetType());
        BigDecimal bettingDebt = determineBettingDebt(betResult, bet.getMoney());

        bet.update(betResult, bettingDebt);

    }

    private BigDecimal determineBettingDebt(BetResult result, BigDecimal money) {

        switch (result) {
            case WINS:
                return money;
            case LOSES:
                return money.negate();
            case NETHER_WIN_NOR_LOSE:
                return BigDecimal.ZERO;
        }

        throw new IllegalStateException(result + "is not supported!");
    }

    private BetResult determineBetResult(Round lastRound, BetType betType) {

        return Stream.of(commands)
                .filter(command -> command.isResponsible(lastRound))
                .findFirst().orElseThrow(() -> createException(lastRound))
                .determineBetResult(betType, lastRound);
    }

    private IllegalArgumentException createException(Round lastRound) {
        return new IllegalArgumentException(lastRound.getClass() + " is not supported");
    }


    private List<Bet> createBetsFor(List<Player> players, BigDecimal money, BetType betType) {
        return players.stream()
                .map(player -> new Bet(betType, money, player))
                .collect(Collectors.toList());
    }

    public String buildMessage() {
        return placedBets.stream()
                .map(bet -> bet.buildMessage())
                .collect(Collectors.joining(LINE_ENDING));
    }
}
