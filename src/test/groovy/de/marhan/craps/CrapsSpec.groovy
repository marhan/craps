package de.marhan.craps

import spock.lang.Specification
import spock.lang.Unroll

class CrapsSpec extends Specification {

    def die01
    def die02
    Set<Die> dice

    def setup() {
        die01 = Stub(Die)
        die02 = Stub(Die)
        dice = [die01, die02]
    }

    def "Start craps via main method and no exception is thrown"() {
        Craps.main();
    }

    def "Play with default configuration"() {

        given: "A new craps game"
        def subject = new Craps();

        when: "the game is played"
        def games = subject.play()

        then: "Rounds are played"
        !games.buildMessage().empty
    }

    @Unroll
    def "Player plays one round with #round and #gameScoring"() {

        given: "the game craps"
        def subject = new Craps()

        and: "with players"
        List<Player> players = [new Player(1), new Player(2), new Player(3)]

        and: "with dice which return the prepared sum"
        die01.nextValue() >> 1
        die02.nextValue() >>> round[0] - 1 >> round[1] - 1 >> round[2] - 1

        when: "craps is played"
        def games = subject.play(players, dice)

        then: "the games is as expected"
        games.buildMessage() == new TestFiles().read("craps", expectedMessage)

        where:
        round     || expectedMessage
        [7, 7, 7] || "03_players_01_round_natural_07_wins"
        [2, 2, 2] || "03_players_01_round_craps_02_loses"
    }

}