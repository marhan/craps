package de.marhan.craps

import spock.lang.Specification
import spock.lang.Unroll

import static de.marhan.craps.GameScoring.LOSE
import static de.marhan.craps.GameScoring.WINS

class CrapsSpec extends Specification {

    def "Play with default configuration"() {

        given: "A new craps game"
        def subject = new Craps();

        when: "the game is played"
        Result result = subject.play()

        then: "Rounds are played"
        result.rounds.size() > 0
    }

    def "Play with two dices and get a sum"() {

        given: "the game craps"
        def subject = new Craps()

        and: "dices return 3 as sum"
        def dice1 = Mock(Dice)
        def dice2 = Mock(Dice)
        dice1.nextValue() >> 1
        dice2.nextValue() >> 2
        Set<Dice> dices = [dice1, dice2]

        and: "three players are playing"
        List<Player> players = [new Player(1), new Player(2), new Player(3)]

        when: "craps will be played"
        Result result = subject.playWith(players, dices)

        then: "one round is played"
        result.rounds.size() == 1

        and: "the result contains the prepared sum"
        result.rounds[0].sum == 3
    }

    @Unroll
    def "Play one round with #preparedSum of dices and get #expectedGameScoring as result"() {

        given: "the game craps"
        def subject = new Craps()

        and: "with players"
        List<Player> players = [new Player(1), new Player(2), new Player(3)]

        and: "with dices which return the prepared sum"
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
        7           | WINS
        11          | WINS
        2           | LOSE
        3           | LOSE
        12          | LOSE
    }

    @Unroll
    def "Play second round with roll of #secondSum and get #expectedGameScoring as game scoring"() {

        given: "the game craps"
        def subject = new Craps()

        and: "with players"
        List<Player> players = [new Player(1), new Player(2), new Player(3)]

        and: "with dices which return the prepared sum"
        def dice1 = Stub(Dice)
        def dice2 = Stub(Dice)
        Set<Dice> dices = [dice1, dice2]

        dice1.nextValue() >> 1
        dice2.nextValue() >>> firstSum - 1 >> secondSum - 1

        when: "craps is played"
        def result = subject.playWith(players, dices)

        then: "the result is as expected"
        result.rounds.size() == 2
        result.gameScoring == expectedGameScoring

        where:

        firstSum | secondSum || expectedGameScoring
        4        | 4         || WINS
        5        | 5         || WINS
        6        | 6         || WINS
        8        | 8         || WINS
        9        | 9         || WINS
        10       | 10        || WINS

        10       | 7         || LOSE
    }




}