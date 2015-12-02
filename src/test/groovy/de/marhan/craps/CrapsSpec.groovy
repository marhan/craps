package de.marhan.craps

import spock.lang.Specification

class CrapsSpec extends Specification {

    def "Play with default configuration"() {

        given:
        def subject = new Craps();

        when:
        Result result = subject.play()

        then:
        result.rounds[0].sum > 0;
    }

    def "Play one round with two dices and get a sum"() {

        given:
        def subject = new Craps();

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
        Result result = subject.play(dices, players)

        then:
        result.rounds.size() == 1
        result.rounds[0].sum == 3
    }
}  