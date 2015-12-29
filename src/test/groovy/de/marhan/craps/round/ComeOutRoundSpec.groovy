package de.marhan.craps.round

import de.marhan.craps.ComeOut
import de.marhan.craps.Dice
import de.marhan.craps.Player
import spock.lang.Specification
import spock.lang.Unroll

import static de.marhan.craps.ComeOut.*

class ComeOutRoundSpec extends Specification {

    @Unroll
    def "Round prints #expectedMessage"() {

        given: "properties of round"
        def player = Mock(Player)
        player.buildMessage() >> "player 1"

        and: "round is initialized with given properties"
        def subject = new ComeOutRound()

        subject.comeOut = comeOut
        subject.shooter = player
        subject.sum = sum

        when: "the message is build"
        def message = subject.buildMessage()

        then: "the message has shooter and sum within"
        message == expectedMessage

        where:
        sum | comeOut         | expectedMessage
        2   | ComeOut.CRAPS   | "Shooter (player 1) come-out with 2 (CRAPS)"
        7   | ComeOut.NATURAL | "Shooter (player 1) come-out with 7 (NATURAL)"
        10  | ComeOut.POINT   | "Shooter (player 1) come-out with 10 (POINT)"

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
        def subject = new ComeOutRound()
        subject.play(shooter, dices)

        then: "the round has set its properties"
        subject.sum == 3
        subject.comeOut == ComeOut.CRAPS
        subject.shooter == shooter
    }

    @Unroll
    def "Play a round with #sum of dices and get a #expectedScore"() {

        given: "the game craps"
        def subject = new ComeOutRound()

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

        then: "the comeOut is expected"
        round.comeOut == expectedScore

        where:
        sum | expectedScore
        2   | CRAPS
        3   | CRAPS
        4   | POINT
        5   | POINT
        6   | POINT
        7   | NATURAL
        8   | POINT
        9   | POINT
        10  | POINT
        11  | NATURAL
        12  | CRAPS
    }

}