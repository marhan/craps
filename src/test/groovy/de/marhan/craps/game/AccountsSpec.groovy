package de.marhan.craps.game

import de.marhan.craps.Player
import de.marhan.craps.TestFiles
import spock.lang.Specification


class AccountsSpec extends Specification {

    def "Accounts of players will printed"() {

        given:
        def shooter = Stub(Player)
        shooter.buildMessage() >> "Shooter"
        shooter.account >> 11
        shooter.number >> 1

        def other01 = Stub(Player)
        other01.buildMessage() >> "Other 01"
        other01.account >> 22
        other01.number >> 2

        def other02 = Stub(Player)
        other02.buildMessage() >> "Other 02"
        other02.account >> 33
        other02.number >> 3

        and:
        def subject = new Accounts(shooter, [other01, other02])

        when:
        subject.snapshot()

        then:
        subject.buildMessage() == new TestFiles().read("accounts", "03_players_accounts_snapshot")

    }

}