package de.marhan.craps.game;

import de.marhan.craps.Player;
import de.marhan.craps.util.DomainObject;
import org.apache.commons.collections4.ListUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static de.marhan.craps.util.Constants.LINE_ENDING;
import static java.util.Collections.singletonList;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;

public class Accounts extends DomainObject {

    private Map<Player, BigDecimal> accountsOfPlayers;
    private final List<Player> players;

    public Accounts(Player shooter, List<? extends Player> others) {
        players = ListUtils.union(others, singletonList(shooter));
    }

    public void snapshot() {
        accountsOfPlayers = players.stream()
                .collect(Collectors.toMap(Function.identity(), Player::getAccount));
    }

    public String buildMessage() {
        String accountMessage = accountsOfPlayers.keySet()
                .stream()
                .sorted(comparing(Player::getNumber))
                .map(player -> buildAccountMessageFor(player, accountsOfPlayers.get(player)))
                .collect(joining(LINE_ENDING));

        return accountMessage + LINE_ENDING;
    }

    private String buildAccountMessageFor(Player player, BigDecimal account) {
        String messageFormat = "%s has currently %s euro on his account";

        return String.format(messageFormat
                , player.buildMessage()
                , account);
    }
}
