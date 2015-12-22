package de.marhan.craps

import spock.lang.Specification

import static RoundScoring.*

class ScoreSpec extends Specification {

    def "Score returns #score for #sum"() {

        when: "expectedScore determines the sum"
        def determinedScore = RoundScoring.determine(sum)

        then: "the sum is as expected"
        determinedScore == expectedScore

        where:
        sum | expectedScore
        2   | CRAP
        3   | CRAP
        4   | POINT
        5   | POINT
        6   | POINT
        7   | NATURAL
        8   | POINT
        9   | POINT
        10  | POINT
        11  | NATURAL
        12  | CRAP
    }

    def "Score throws exception if sum is not in range"() {

        when: "expectedScore determines the sum"
        RoundScoring.determine(sum)

        then: "the sum is as expected"
        def e = thrown(IllegalArgumentException)
        e.message == "${sum} is not in range"

        where:
        sum << [1,13]
    }

}