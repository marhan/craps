package de.marhan.craps.game.round

import spock.lang.Specification

import static ShootingResult.*

class ShootingResultSpec extends Specification {

    def "Determine Shooting after round is played"() {

        when: "shootingResult is determined"
        ShootingResult result = ShootingResult.determine(sum, shootingForSum)

        then: "result is as expected"
        result == expectedResult

        where:
        shootingForSum | sum || expectedResult
        10             | 10  || HIT_THE_POINT
        10             | 9   || MISS_THE_POINT
        10             | 7   || SEVEN_OUT

    }

}