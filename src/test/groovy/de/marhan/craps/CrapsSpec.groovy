package de.marhan.craps

import spock.lang.Specification
import spock.lang.Unroll

class CrapsSpec extends Specification {

    def die01
    def die02
    Set<Die> dice
    Config config

    def setup() {
        die01 = Stub(Die)
        die02 = Stub(Die)
        dice = [die01, die02]
        config = new Config()
    }

    def "Start craps via main method and no exception is thrown"() {
        Craps.main();
    }

    def "Play with default configuration"() {

        given: "configuration"
        config.maxGames = 5;
        config.initialAccount = 30

        when: "the game is played"
        def subject = new Craps(config);

        then: "Rounds are played"
        subject.play().gamesPlayed.size() == 5
    }

    @Unroll
    def "One sequence with with expected output in #expectedMessage"() {

        given: "configuration"
        config.maxGames = maxGames
        config.initialAccount = initialAccount

        and: "craps"
        def subject = new Craps(config)

        and: "with players"
        List<Player> players = [new Player(1, initialAccount)
                                , new Player(2, initialAccount)
                                , new Player(3, initialAccount)]

        and: "with dice which return the prepared sum"
        die01.nextValue() >> 1
        die02.nextValue() >>> sequence[0] - 1 >> sequence[1] - 1 >> sequence[2] - 1

        when: "craps is played"
        def games = subject.play(players, dice)

        then: "the games is as expected"
        games.buildMessage() == new TestFiles("craps").read(expectedMessage)

        where:
        sequence  | maxGames | initialAccount || expectedMessage
        [7, 7, 7] | 3        | 30             || "03_players_01_round_natural_07_wins"
        [2, 2, 2] | 3        | 30             || "03_players_01_round_craps_02_loses"
        [7, 7, 7] | 88       | 1              || "03_players_01_round_insufficient_account"
    }

}