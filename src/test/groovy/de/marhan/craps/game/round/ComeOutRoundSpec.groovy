package de.marhan.craps.game.round

import de.marhan.craps.Config
import de.marhan.craps.Die
import de.marhan.craps.Player
import spock.lang.Specification
import spock.lang.Unroll

import static de.marhan.craps.game.round.ComeOutResult.*

class ComeOutRoundSpec extends Specification {

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

    @Unroll
    def "Round prints #expectedMessage"() {

        given: "a shooter"
        def player = new Player(1, config.getInitialAccount());

        and: "dice with values"
        die01.nextValue() >> 1
        die02.nextValue() >> sum - 1

        and: "the test subject"
        def subject = new ComeOutRound(dice)

        when: "the round is played"
        subject.play(player)

        and: "the message is build"
        def message = subject.buildMessage()

        then: "the come-out result is as expected"
        subject.comeOutResult == comeOut

        and: "the message has shooter and sum within"
        message.contains(expectedMessage)

        where:
        sum || comeOut | expectedMessage
        2   || ComeOutResult.CRAPS | "come-out with 2 (CRAPS)"
        7   || ComeOutResult.NATURAL | "come-out with 7 (NATURAL)"
        10  || ComeOutResult.POINT | "come-out with 10 (POINT)"

    }

    @Unroll
    def "Play a round with #sum of dices and get a #expectedScore"() {

        given: "the game craps"
        def subject = new ComeOutRound(dice)

        and: "shooter"
        def shooter = new Player(1, config.getInitialAccount())

        and: "dice with values"
        die01.nextValue() >> 1
        die02.nextValue() >> sum - 1

        when: "shooter plays one round"
        def round = subject.play(shooter)

        then: "the comeOutResult is expected"
        round.comeOutResult == expectedScore

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