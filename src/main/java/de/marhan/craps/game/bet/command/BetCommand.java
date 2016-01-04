package de.marhan.craps.game.bet.command;

import de.marhan.craps.game.bet.BetResult;
import de.marhan.craps.game.bet.BetType;
import de.marhan.craps.game.round.Round;

public interface BetCommand<T extends Round> {

    BetResult determineBetResult(BetType betType, T round);

    boolean isResponsible(Round round);
}
