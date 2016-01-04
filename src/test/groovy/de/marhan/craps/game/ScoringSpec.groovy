package de.marhan.craps.game

import de.marhan.craps.game.round.ComeOutResult
import de.marhan.craps.game.round.ComeOutRound
import de.marhan.craps.game.round.ShootingResult
import spock.lang.Specification
import spock.lang.Unroll

class ScoringSpec extends Specification {

    @Unroll
    def "Determine #expectedGameScoring with one round"() {

        given: "prepared round scoring"
        def round1 = Mock(ComeOutRound)
        round1.getComeOutResult() >> preparedRoundScoring

        when: "game scoring is determined"
        def gameScoring = Scoring.determine([round1])

        then: "the result is as expected"
        gameScoring == expectedGameScoring

        where:
        preparedRoundScoring  | expectedGameScoring
        ComeOutResult.NATURAL | Scoring.WINS
        ComeOutResult.CRAPS   | Scoring.LOSES
        ComeOutResult.POINT   | Scoring.NEXT_ROUND

    }

    @Unroll
    def "Scoring throws exception, if argument is #notDefined"() {

        when: "try to determine"
        Scoring.determineFor(notDefined)

        then: "exception is thrown"
        def e = thrown(IllegalArgumentException)
        e.message == "${notDefined} is not supported!"

        where:
        notDefined                 | message
        ShootingResult.NOT_DEFINED | "${ShootingResult.NOT_DEFINED} is not supported!"
        ComeOutResult.NOT_DEFINED  | "${ComeOutResult.NOT_DEFINED} is not supported!"
    }

}