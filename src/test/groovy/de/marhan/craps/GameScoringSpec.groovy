package de.marhan.craps

import spock.lang.Specification
import spock.lang.Unroll


class GameScoringSpec extends Specification {

    @Unroll
    def "Determine #expectedGameScoring with one round"() {

        given: "prepared round scoring"
        def round1 = Mock(Round)
        round1.getRoundScoring() >> preparedRoundScoring

        when: "game scoring is determined"
        def gameScoring = GameScoring.determine([round1])

        then: "the result is as expected"
        gameScoring == expectedGameScoring

        where:
        preparedRoundScoring | expectedGameScoring
        RoundScoring.NATURAL | GameScoring.WINS
        RoundScoring.CRAP    | GameScoring.LOSE
        RoundScoring.POINT   | GameScoring.OPEN

    }

    @Unroll
    def "Determine #expectedGameScoring with two rounds"() {

        given: "prepared rounds"
        def round1 = Stub(Round)
        round1.getRoundScoring() >> RoundScoring.POINT
        round1.getSum() >> firstRoundSum

        def round2 = Stub(Round)
        round2.getRoundScoring() >> secondRoundScoring
        round2.getSum() >> secondRoundSum

        when: "game scoring is determined"
        def gameScoring = GameScoring.determine([round1, round2])

        then: "the result is as expected"
        gameScoring == expectedGameScoring

        where:
        firstRoundSum | secondRoundScoring | secondRoundSum || expectedGameScoring
        4             | RoundScoring.CRAP  | 4              || GameScoring.WINS
        4             | RoundScoring.POINT | 7              || GameScoring.LOSE
        4             | RoundScoring.POINT | 1              || GameScoring.OPEN
    }

}