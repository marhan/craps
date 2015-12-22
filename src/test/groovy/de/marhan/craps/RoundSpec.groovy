package de.marhan.craps

import spock.lang.Specification
import spock.lang.Unroll

import static RoundScoring.CRAP
import static RoundScoring.NATURAL
import static RoundScoring.POINT

class RoundSpec extends Specification {

    def "Round prints its properties"() {

        given: "properties of round"
        def player = Mock(Player)
        player.buildMessage() >> "shooter 1"

        and: "round is initialized with given properties"
        def subject = new Round()

        subject.roundScoring = RoundScoring.CRAP
        subject.shooter = player
        subject.sum = 2

        when: "the message is build"
        def message = subject.buildMessage()

        then: "the message has shooter and sum within"
        message == "Shooter (shooter 1) rolls 2 and has a CRAP"
    }

    def "Round plays the round with shooter"() {

        given: "a shooter"
        def shooter = new Player(1)

        and: "two dices"
        def dice1 = Mock(Dice)
        def dice2 = Mock(Dice)
        Set<Dice> dices = [dice1, dice2]

        dice1.nextValue() >> 1
        dice2.nextValue() >> 2

        when: "the round to play"
        def subject = new Round()
        subject.play(shooter, dices)

        then: "the round has set its properties"
        subject.sum == 3
        subject.roundScoring == RoundScoring.CRAP
        subject.shooter == shooter
    }

    @Unroll
    def "Play a round with #sum of dices and get a #expectedScore"() {

        given: "the game craps"
        def subject = new Round()

        and: "and shooter with dices"
        def shooter = new Player(1)
        def dice1 = Mock(Dice)
        def dice2 = Mock(Dice)
        Set<Dice> dices = [dice1, dice2]

        when: "dice sum is #sum"
        dice1.nextValue() >> 1
        dice2.nextValue() >> sum - 1

        and: "shooter plays one round"
        def round = subject.play(shooter, dices)

        then: "the roundScoring is expected"
        round.roundScoring == expectedScore

        where:
        sum | expectedScore
        2   | CRAP
        3   | CRAP
        4   | POINT
        5   | POINT
        6   | POINT
        7   | NATURAL
        8   | POINT
        9   | POINT
        10  | POINT
        11  | NATURAL
        12  | CRAP
    }

}