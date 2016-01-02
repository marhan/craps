package de.marhan.craps.game.round

import spock.lang.Specification


class RoundSpec extends Specification {

    def subject = new TestRound()

    def "Round provides default in getComeOutResult() method"() {
        expect:
        subject.getComeOutResult() == ComeOutResult.NOT_DEFINED;
    }

    def "Round provides default in getShootingResult() method"() {
        expect:
        subject.getShootingResult() == ShootingResult.NOT_DEFINED;
    }

    def "Round provides buildMessage() method"() {
        expect:
        subject.buildMessage() == "testMessage"

    }

}