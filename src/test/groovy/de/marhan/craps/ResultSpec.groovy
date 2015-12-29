package de.marhan.craps

import de.marhan.craps.round.ComeOutRound
import de.marhan.craps.round.Scoring
import de.marhan.craps.round.ShootingRound
import spock.lang.Specification

class ResultSpec extends Specification {

    def "Result with round prints message"() {

        given: "result has a round"
        def round1 = Mock(ComeOutRound)
        def round2 = Mock(ShootingRound)
        def subject = new Result([round1, round2], Scoring.WINS)

        when: "round returns message"
        round1.buildMessage() >> "ANY_MESSAGE"
        round2.buildMessage() >> "ANY_OTHER_MESSAGE"

        and: "message is used to build it own"
        def message = subject.buildMessage()

        then: "round, shooter and sum is printed"
        message == "2 rounds played, shooter WINS:\nRound 1: ANY_MESSAGE\nRound 2: ANY_OTHER_MESSAGE"
    }

}