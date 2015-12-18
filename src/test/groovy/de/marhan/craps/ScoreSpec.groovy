package de.marhan.craps

import spock.lang.Specification

import static de.marhan.craps.Score.*

class ScoreSpec extends Specification {

    def "Score returns #score for #sum"() {

        when: "expectedScore determines the sum"
        def determinedScore = Score.determineScore(sum)

        then: "the sum is as expected"
        determinedScore == expectedScore

        where:
        sum | expectedScore
        2   | Crap
        3   | Crap
        4   | Point
        5   | Point
        6   | Point
        7   | Natural
        8   | Point
        9   | Point
        10  | Point
        11  | Natural
        12  | Crap
    }

    def "Score throws exception if sum is not in range"() {

        when: "expectedScore determines the sum"
        Score.determineScore(sum)

        then: "the sum is as expected"
        def e = thrown(IllegalArgumentException)
        e.message == "${sum} is not in range"

        where:
        sum << [1,13]
    }

}