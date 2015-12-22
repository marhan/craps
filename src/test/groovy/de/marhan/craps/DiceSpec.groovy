package de.marhan.craps

import spock.lang.Specification
import spock.lang.Unroll

class DiceSpec extends Specification {

    @Unroll
    def "Dice throws values in defined range"() {
        given: "an initiated dice"
        Dice subject = new Dice()

        when: "the next value is rolled"
        def value = subject.nextValue()

        then: "the value is in range"
        value << 1..6
    }

}