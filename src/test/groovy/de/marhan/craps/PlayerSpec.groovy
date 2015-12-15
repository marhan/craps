package de.marhan.craps

import spock.lang.Specification

class PlayerSpec extends Specification {

    def "Player throws dices"() {

        given: "Player has two dices"
        Dice dice1 = Mock(Dice)
        Dice dice2 = Mock(Dice)
        Player player = new Player(1);

        when: "dices will throw 3 in sum"
        dice1.nextValue() >> 1
        dice2.nextValue() >> 2
        Set<Dice> dices = [dice1, dice2]

        and: "player throws the dices"
        int sum = player.throwDices(dices)

        then: "the sum is as expected"
        sum == 3
    }

    def "Player has his number in its message"() {

        given: "Player with a number"
        def subject = new Player(1);

        when: "message is build"
        def message = subject.buildMessage();

        then: "the number can be found within the message"
        message == "player 1"
    }


}