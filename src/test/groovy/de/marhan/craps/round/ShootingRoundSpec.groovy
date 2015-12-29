package de.marhan.craps.round

import de.marhan.craps.Player
import spock.lang.Specification
import spock.lang.Unroll

class ShootingRoundSpec extends Specification {

    @Unroll
    def "Round prints its properties"() {

        given: "properties of round"
        def player = Mock(Player)
        player.buildMessage() >> "player 1"

        and: "round is initialized with given properties"
        def subject = new ShootingRound(shootingForSum)

        subject.shooter = player
        subject.sum = sum

        when: "the message is build"
        def message = subject.buildMessage()

        then: "the message has shooter and sum within"
        message == expectedMessage

        where:
        sum | shootingForSum || expectedMessage
        10  | 10             || "Shooter (player 1) is shooting for 10 and the roll is 10 (Pass)"
        10  | 2              || "Shooter (player 1) is shooting for 2 and the roll is 10"
        7   | 2              || "Shooter (player 1) is shooting for 2 and the roll is 7 (Don't Pass)"
    }

    @Unroll
    def "Round is marked as pass=#expectedPass on equal sums"() {

        given:
        def subject = new ShootingRound(shootingForSum)

        subject.sum = sum
        subject.shootingForSum = shootingForSum

        when:
        def pass = subject.hasPassThePoint()

        then:
        pass == expectedPass

        where:
        sum | shootingForSum || expectedPass
        10  | 10             || true
        10  | 1              || false
        10  | 7              || false

    }


}