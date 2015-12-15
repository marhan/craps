package de.marhan.craps

import spock.lang.Specification

class ResultSpec extends Specification {

    def "Result with round prints message"() {

        given: "result has a round"
        def subject = new Result();
        subject.addRound(new Player(1), 2)

        when: "message is build"
        def message = subject.buildMessage()

        then: "round, player and sum is printed"
        message == "1 rounds played:\nround 1: player 1 throws 2\n"
    }

}