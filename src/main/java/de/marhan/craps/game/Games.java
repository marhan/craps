package de.marhan.craps.game;

import de.marhan.craps.Die;
import de.marhan.craps.Player;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Games {

    private List<Game> gamesPlayed = new ArrayList<>();
    private final Set<Die> dice;

    public Games(Set<Die> dice) {
        this.dice = dice;
    }

    public String buildMessage() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(buildMessageWith(gamesPlayed));
        return buffer.toString();
    }

    private String buildMessageWith(List<Game> gamesPlayed) {
        return gamesPlayed.stream()
                .map(game -> game.buildMessage())
                .collect(Collectors.joining("\n"));
    }

    public Game playGame(Player shooter) {
        Game game = new Game(this.dice).play(shooter);
        gamesPlayed.add(game);
        return game;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
