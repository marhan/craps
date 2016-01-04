package de.marhan.craps.game;

import de.marhan.craps.Player;
import de.marhan.craps.util.DomainObject;
import org.apache.commons.collections4.ListUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static de.marhan.craps.util.Constants.LINE_ENDING;
import static java.util.Collections.singletonList;
import static java.util.Comparator.comparing;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toMap;

public class Accounts extends DomainObject {

    private Map<Player, BigDecimal> accountOverview;
    private final List<Player> players;

    public Accounts(Player shooter, List<? extends Player> others) {
        players = ListUtils.union(others, singletonList(shooter));
    }

    public Accounts(List<Player> players) {
        this.players = players;
    }

    public Accounts snapshot() {
        accountOverview = players.stream().collect(toMap(identity(), Player::getAccount));
        return this;
    }

    public String buildMessage() {
        String accountMessage = accountOverview.keySet()
                .stream()
                .sorted(comparing(Player::getNumber))
                .map(player -> buildAccountMessageFor(player, accountOverview.get(player)))
                .collect(joining(LINE_ENDING));

        return accountMessage + LINE_ENDING;
    }

    public boolean haveAllSufficientOnAccount() {
        return players.stream()
                .filter(player -> player.isAccountSufficientForBet())
                .count() == players.size();
    }

    private String buildAccountMessageFor(Player player, BigDecimal account) {
        String messageFormat = "%s has currently %s euro on his account";

        return String.format(messageFormat
                , player.buildMessage()
                , account);
    }

}
