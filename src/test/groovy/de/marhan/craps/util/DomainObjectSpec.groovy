package de.marhan.craps.util

import spock.lang.Specification


class DomainObjectSpec extends Specification {

    def subject = new TestDomainObject()

    def "Domain object provides toString() method"() {

        expect:
        subject.toString() == "TestDomainObject[testField=testValue]"

    }

    def "Domain object provides buildMessage() method"() {

        expect:
        subject.buildMessage() == "testValue"

    }

}