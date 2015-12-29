package de.marhan.craps

import spock.lang.Specification
import spock.lang.Unroll

import static de.marhan.craps.round.Scoring.LOSE
import static de.marhan.craps.round.Scoring.WINS

class CrapsSpec extends Specification {

    def "Play with default configuration"() {

        given: "A new craps game"
        def subject = new Craps();

        when: "the game is played"
        Result result = subject.play()

        then: "Rounds are played"
        result.rounds.size() > 0
    }

    @Unroll
    def "Player plays sequence #round01, #round02, #round03 and #gameScoring due to rolling the Point twice"() {

        given: "the game craps"
        def subject = new Craps()

        and: "with players"
        List<Player> players = [new Player(1), new Player(2), new Player(3)]

        and: "with dices which return the prepared sum"
        def dice1 = Stub(Dice)
        def dice2 = Stub(Dice)
        Set<Dice> dices = [dice1, dice2]

        dice1.nextValue() >> 1
        dice2.nextValue() >>> round01 - 1 >> round02 - 1 >> round03 - 1

        when: "craps is played"
        def result = subject.playWith(players, dices)

        then: "the result is as expected"
        result.scoring == gameScoring
        result.buildMessage() == new TestFiles().read(expectedMessage)

        where:
        round01 | round02 | round03 || gameScoring | expectedMessage
        4       | 2       | 4       || WINS | "03_rounds_point_04_wins"
        5       | 3       | 5       || WINS | "03_rounds_point_05_wins"
        6       | 5       | 6       || WINS | "03_rounds_point_06_wins"
        8       | 6       | 8       || WINS | "03_rounds_point_08_wins"
        9       | 8       | 9       || WINS | "03_rounds_point_09_wins"
        10      | 9       | 10      || WINS | "03_rounds_point_10_wins"
        8       | 6       | 7       || LOSE | "03_rounds_point_07_loses"
    }

    @Unroll
    def "Player plays sequence #round01, #round02 and #gameScoring due to rolling the Point twice"() {

        given: "the game craps"
        def subject = new Craps()

        and: "with players"
        List<Player> players = [new Player(1), new Player(2), new Player(3)]

        and: "with dices which return the prepared sum"
        def dice1 = Stub(Dice)
        def dice2 = Stub(Dice)
        Set<Dice> dices = [dice1, dice2]

        dice1.nextValue() >> 1
        dice2.nextValue() >>> round01 - 1 >> round02 - 1

        when: "craps is played"
        def result = subject.playWith(players, dices)

        then: "the result is as expected"
        result.scoring == gameScoring
        result.buildMessage() == new TestFiles().read(expectedMessage)

        where:
        round01 | round02 || gameScoring | expectedMessage
        4       | 4       || WINS | "02_rounds_point_04_wins"
        5       | 5       || WINS | "02_rounds_point_05_wins"
        6       | 6       || WINS | "02_rounds_point_06_wins"
        8       | 8       || WINS | "02_rounds_point_08_wins"
        9       | 9       || WINS | "02_rounds_point_09_wins"
        10      | 10      || WINS | "02_rounds_point_10_wins"
        8       | 7       || LOSE | "02_rounds_point_07_loses"
    }

    @Unroll
    def "Player plays one round with #round and #gameScoring"() {

        given: "the game craps"
        def subject = new Craps()

        and: "with players"
        List<Player> players = [new Player(1), new Player(2), new Player(3)]

        and: "with dices which return the prepared sum"
        def dice1 = Stub(Dice)
        def dice2 = Stub(Dice)
        Set<Dice> dices = [dice1, dice2]

        dice1.nextValue() >> 1
        dice2.nextValue() >> round - 1

        when: "craps is played"
        def result = subject.playWith(players, dices)

        then: "the result is as expected"
        result.scoring == gameScoring
        result.buildMessage() == new TestFiles().read(expectedMessage)

        where:
        round || gameScoring | expectedMessage
        7     || WINS | "01_round_natural_07_wins"
        11    || WINS | "01_round_natural_11_wins"
        2     || LOSE | "01_round_craps_02_loses"
        3     || LOSE | "01_round_craps_03_loses"
        12    || LOSE | "01_round_craps_12_loses"
    }

}