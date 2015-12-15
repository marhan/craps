package de.marhan.craps

import spock.lang.Specification
import spock.lang.Unroll

class DiceSpec extends Specification {

    @Unroll
    def "Dice throws value between 0 and 6"() {
        given: " An initiated dice"
        Dice subject = new Dice()

        when: "Next value is thrown"
        def value = subject.nextValue()

        then: "The value is between 0 and 6"
        value >= 0
        value <= 6
    }

}