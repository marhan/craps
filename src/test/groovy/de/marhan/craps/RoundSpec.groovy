package de.marhan.craps

import spock.lang.Specification

class RoundSpec extends Specification {

    def "Round prints its player and sum in its message"() {

        given: "round with player and sum"
        def subject = new Round(new Player(1), 2, Score.Crap)

        when: "the message is build"
        def message = subject.buildMessage()

        then: "the message has player and sum within"
        message == "player 1 throws 2 and has a Crap"
    }

    def "Player and sum can be retrieved after initiation of round"() {

        given: "player and a sum"
        def player = new Player(1)
        def sum = 2
        def score = Score.Crap

        when: "are set into round"
        def subject = new Round(player, sum, score)

        then: "the both can the retrieved"
        subject.player == player
        subject.sum == sum
        subject.score == score
    }

}