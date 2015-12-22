package de.marhan.craps

import spock.lang.Specification
import spock.lang.Unroll

import static GameScoring.*

class CrapsSpec extends Specification {

    def "Play with default configuration"() {

        given: "A new craps game"
        def subject = new Craps();

        when: "the game is played"
        subject.play()

        then: "Three players are initialized"
        subject.players.size() == 3

        and: "some rounds are played"
        subject.rounds.size() > 0
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
        Result result = subject.playWith(players, dices)

        then:
        result.rounds.size() == 1
        result.rounds[0].sum == 3
    }

    @Unroll
    def "Play first round with #preparedSum of dices and shooter #expectedGameScoring"() {

        given: "the game craps"
        def subject = new Craps()

        and: "with players"
        Set<Player> players = [new Player(1), new Player(2), new Player(3)]

        and: "with dices"
        def dice1 = Mock(Dice)
        def dice2 = Mock(Dice)
        Set<Dice> dices = [dice1, dice2]

        dice1.nextValue() >> 1
        dice2.nextValue() >> preparedSum - 1

        when: "craps is played"
        def result = subject.playWith(players, dices)

        then: "the result is as expected"
        result.rounds.size() == 1
        result.rounds[0].sum == preparedSum
        result.gameScoring == expectedGameScoring

        where:
        preparedSum | expectedGameScoring
        2           | LOSE
        3           | LOSE
        4           | OPEN
        5           | OPEN
        6           | OPEN
        7           | WINS
        8           | OPEN
        9           | OPEN
        10          | OPEN
        11          | WINS
        12          | LOSE
    }

}