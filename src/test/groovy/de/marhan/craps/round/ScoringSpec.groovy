package de.marhan.craps.round

import de.marhan.craps.ComeOut
import spock.lang.Specification
import spock.lang.Unroll

class ScoringSpec extends Specification {

    @Unroll
    def "Determine #expectedGameScoring with one round"() {

        given: "prepared round scoring"
        def round1 = Mock(ComeOutRound)
        round1.getComeOut() >> preparedRoundScoring

        when: "game scoring is determined"
        def gameScoring = Scoring.determine([round1])

        then: "the result is as expected"
        gameScoring == expectedGameScoring

        where:
        preparedRoundScoring | expectedGameScoring
        ComeOut.NATURAL      | Scoring.WINS
        ComeOut.CRAPS        | Scoring.LOSE
        ComeOut.POINT        | Scoring.OPEN

    }

}