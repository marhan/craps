package de.marhan.craps

import spock.lang.Specification
import spock.lang.Unroll

class DieSpec extends Specification {

    @Unroll
    def "Dice throws values in defined range"() {
        given: "an initiated dice"
        Die subject = new Die()

        when: "the next value is rolled"
        def value = subject.nextValue()

        then: "the value is in range"
        value << 1..6
    }

}