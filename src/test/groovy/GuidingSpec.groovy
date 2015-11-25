import de.marhan.craps.Craps
import de.marhan.craps.Result

class GuidingSpec extends spock.lang.Specification {

    def "Craps is playable"() {

        given:
        def subject = new Craps();

        when:
        Result result = subject.play()

        then:
        result.gameOver() == true;

    }
}  