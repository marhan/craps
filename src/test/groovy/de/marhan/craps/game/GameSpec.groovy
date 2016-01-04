package de.marhan.craps.game

import de.marhan.craps.Die
import de.marhan.craps.Player
import de.marhan.craps.TestFiles
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import static de.marhan.craps.game.Scoring.LOSES
import static de.marhan.craps.game.Scoring.WINS

class GameSpec extends Specification {

    @Shared
    def testFiles = new TestFiles("game")

    def die01, die02
    Set<Die> dice
    def other01, other02

    def setup() {
        die01 = Stub(Die)
        die02 = Stub(Die)
        dice = [die01, die02]

        other01 = new Player(2, 30)
        other02 = new Player(3, 30)
    }

    @Unroll
    def "Player plays one round with #round and #gameScoring"() {

        given: "one game"
        def subject = new Game(dice)

        and: "with players"
        def shooter = new Player(1, 30)

        and: "with others"
        def others = [other01, other02]

        and: "with dice which return the prepared sum"
        die01.nextValue() >> 1
        die02.nextValue() >> round - 1

        when: "craps is played"
        subject.play(shooter, others)

        then: "the games is as expected"
        subject.scoring == gameScoring
        subject.buildMessage().contains(testFiles.read(expectedMessage))

        where:
        round || gameScoring | expectedMessage
        7     || WINS | "01_round_natural_07_wins"
        11    || WINS | "01_round_natural_11_wins"
        2     || LOSES | "01_round_craps_02_loses"
        3     || LOSES | "01_round_craps_03_loses"
        12    || LOSES | "01_round_craps_12_loses"
    }

    @Unroll
    def "Player plays sequence #round01, #round02 and #gameScoring due to rolling the Point twice"() {

        given: "one game"
        def subject = new Game(dice)

        and: "with shooter"
        def shooter = new Player(1, 30)

        and: "with others"
        def others = [other01, other02]

        and: "with dice which return the prepared sum"
        die01.nextValue() >> 1
        die02.nextValue() >>> round01 - 1 >> round02 - 1

        when: "craps is played"
        subject.play(shooter, others)

        then: "the result is as expected"
        subject.scoring == gameScoring
        subject.buildMessage().contains(testFiles.read(expectedMessage))

        where:
        round01 | round02 || gameScoring | expectedMessage
        4       | 4       || WINS | "02_rounds_point_04_wins"
        5       | 5       || WINS | "02_rounds_point_05_wins"
        6       | 6       || WINS | "02_rounds_point_06_wins"
        8       | 8       || WINS | "02_rounds_point_08_wins"
        9       | 9       || WINS | "02_rounds_point_09_wins"
        10      | 10      || WINS | "02_rounds_point_10_wins"
        8       | 7       || LOSES | "02_rounds_point_07_loses"
    }

    @Unroll
    def "Player rolls point twice with sequence #round01, #round02, #round03 and #gameScoring"() {

        given: "one game"
        def subject = new Game(dice)

        and: "with shooter"
        def shooter = new Player(1, 30)

        and: "with others"

        def others = [other01, other02]

        and: "with dice which return the prepared sum"
        die01.nextValue() >> 1
        die02.nextValue() >>> round01 - 1 >> round02 - 1 >> round03 - 1

        when: "craps is played"
        subject.play(shooter, others)

        then: "the result is as expected"
        subject.scoring == gameScoring
        subject.buildMessage().contains(testFiles.read(expectedMessage))

        where:
        round01 | round02 | round03 || gameScoring | expectedMessage
        4       | 2       | 4       || WINS | "03_rounds_point_04_wins"
        5       | 3       | 5       || WINS | "03_rounds_point_05_wins"
        6       | 5       | 6       || WINS | "03_rounds_point_06_wins"
        8       | 6       | 8       || WINS | "03_rounds_point_08_wins"
        9       | 8       | 9       || WINS | "03_rounds_point_09_wins"
        10      | 9       | 10      || WINS | "03_rounds_point_10_wins"
        8       | 6       | 7       || LOSES | "03_rounds_point_07_loses"
    }

}