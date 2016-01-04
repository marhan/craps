package de.marhan.craps.game.round

import de.marhan.craps.Config
import de.marhan.craps.Die
import de.marhan.craps.Player
import spock.lang.Specification
import spock.lang.Unroll

class ShootingRoundSpec extends Specification {

    def dice1
    def dice2
    Set<Die> dice
    Config config

    def setup() {
        dice1 = Stub(Die)
        dice2 = Stub(Die)
        dice = [dice1, dice2]
        config = new Config()
    }

    @Unroll
    def "Round prints its properties"() {

        given: "shooter"
        def shooter = new Player(1, config.getInitialAccount());

        and: "dice"
        dice1.nextValue() >> 1
        dice2.nextValue() >> sum - 1

        and: "the test subject"
        def subject = new ShootingRound(shootingForSum, dice)

        when: "the round is played"
        subject.play(shooter)

        and: "the message is build"
        def message = subject.buildMessage()

        then: "the message is as expected"
        message.contains(expectedMessage)

        and: "the shootingResult is as expected"
        subject.shootingResult == expectedShooting

        where:
        sum | shootingForSum || expectedShooting | expectedMessage
        10  | 10             || ShootingResult.HIT_THE_POINT | "is shooting for 10 and the roll is 10 (Pass)"
        10  | 2              || ShootingResult.MISS_THE_POINT | "is shooting for 2 and the roll is 10"
        7   | 2              || ShootingResult.SEVEN_OUT | "is shooting for 2 and the roll is 7 (Don't Pass)"
    }


}