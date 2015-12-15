package de.marhan.craps

import spock.lang.Specification

class RoundSpec extends Specification {

    def "Round prints its player and sum in its message"() {

        given: "round with player and sum"
        def subject = new Round(1, new Player(1), 2)

        when: "the message is build"
        def message = subject.buildMessage()

        then: "the message has player and sum within"
        message == "round 1: player 1 throws 2"
    }

    def "Player and sum can be retrieved after initiation of round"() {

        given: "player and a sum"
        def player = new Player(1);
        def sum = 2

        when: "are set into round"
        def subject = new Round(1, player, sum)

        then: "the both can the retrieved"
        subject.player == player
        subject.sum == sum
    }

}