package de.marhan.craps

import spock.lang.Specification

class ResultSpec extends Specification {

    def "Result with round prints message"() {

        given: "result has a round"
        def round1 = Mock(Round)
        def round2 = Mock(Round)
        def subject = new Result([round1, round2])

        when: "round returns message"
        round1.buildMessage() >> "ANY_MESSAGE"
        round2.buildMessage() >> "ANY_OTHER_MESSAGE"

        and: "message is used to build it own"
        def message = subject.buildMessage()

        then: "round, player and sum is printed"
        message == "2 rounds played:\nround 1: ANY_MESSAGE\nround 2: ANY_OTHER_MESSAGE\n"
    }

}