package de.marhan.craps

import spock.lang.Specification
import spock.lang.Unroll

import static de.marhan.craps.Score.*

class CrapsSpec extends Specification {

    def "Play with default configuration"() {

        given:
        def subject = new Craps();

        when:
        Result result = subject.play()

        then:
        result.rounds[0].sum > 0;
    }

    def "Play with two dices and get a sum"() {

        given:
        def subject = new Craps()

        when:
        def dice1 = Mock(Dice)
        def dice2 = Mock(Dice)
        dice1.nextValue() >> 1
        dice2.nextValue() >> 2
        Set<Dice> dices = [dice1, dice2]

        and:
        def player1 = new Player(1)
        def player2 = new Player(2)
        Set<Player> players = [player1, player2]

        and:
        Result result = subject.playWith(dices, players)

        then:
        result.rounds.size() == 1
        result.rounds[0].sum == 3
    }

    @Unroll
    def "Play a round with #sum and get a #expectedScore"() {

        given: "the game craps"
        def subject = new Craps()

        and: "and player with dices"
        def player = new Player(1)
        def dice1 = Mock(Dice)
        def dice2 = Mock(Dice)
        Set<Dice> dices = [dice1, dice2]

        when: "dice sum is #sum"
        dice1.nextValue() >> 1
        dice2.nextValue() >> sum - 1

        and: "player plays one round"
        def round = subject.playRound(player, dices)

        then: "the player wins"
        round.score == expectedScore

        where:
        sum | expectedScore
        2   | Crap
        3   | Crap
        4   | Point
        5   | Point
        6   | Point
        7   | Natural
        8   | Point
        9   | Point
        10  | Point
        11  | Natural
        12  | Crap
    }
}