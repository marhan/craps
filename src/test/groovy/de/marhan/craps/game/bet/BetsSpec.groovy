package de.marhan.craps.game.bet

import de.marhan.craps.Player
import de.marhan.craps.game.round.ComeOutRound
import de.marhan.craps.game.round.ShootingResult
import de.marhan.craps.game.round.ShootingRound
import spock.lang.Specification
import spock.lang.Unroll

class BetsSpec extends Specification {

    def subject
    def player

    def setup() {
        subject = new Bets()
        player = new Player(1)
        player.account = BigDecimal.ONE
    }

    @Unroll
    def "#moneyWithAppliedBetDebt when betType: #betType and sum: #sum for ComeOutRound"() {

        given:
        def round = Stub(ComeOutRound)
        round.sum >> sum

        when:
        subject.createBets([player], BigDecimal.ONE, betType)
        subject.determineAndApplyBets(round)

        then:
        player.account == moneyWithAppliedBetDebt

        where:
        betType                  | sum || moneyWithAppliedBetDebt
        BetType.PASS_LINE        | 7   || 2
        BetType.PASS_LINE        | 11  || 2
        BetType.PASS_LINE        | 2   || 0
        BetType.PASS_LINE        | 3   || 0
        BetType.PASS_LINE        | 12  || 0
        BetType.DO_NOT_PASS_LINE | 7   || 0
        BetType.DO_NOT_PASS_LINE | 11  || 0
        BetType.DO_NOT_PASS_LINE | 2   || 2
        BetType.DO_NOT_PASS_LINE | 3   || 2
        BetType.DO_NOT_PASS_LINE | 12  || 1
    }

    @Unroll
    def "#moneyWithAppliedBetDebt when betType: #betType and shootingResult: #shootingResult for ShootingRound"() {

        given:
        def round = Stub(ShootingRound)
        round.shootingResult >> shootingResult

        when:
        subject.createBets([player], BigDecimal.ONE, betType)
        subject.determineAndApplyBets(round)

        then:
        player.account == moneyWithAppliedBetDebt

        where:
        betType                  | shootingResult               || moneyWithAppliedBetDebt
        BetType.PASS_LINE        | ShootingResult.HIT_THE_POINT || 2
        BetType.PASS_LINE        | ShootingResult.SEVEN_OUT     || 0
        BetType.DO_NOT_PASS_LINE | ShootingResult.HIT_THE_POINT || 0
        BetType.DO_NOT_PASS_LINE | ShootingResult.SEVEN_OUT     || 2

    }

}