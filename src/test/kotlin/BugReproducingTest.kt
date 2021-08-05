import com.justai.jaicf.template.scenario.mainScenario
import com.justai.jaicf.test.ScenarioTest
import org.junit.jupiter.api.Test

class BugReproducingTest : ScenarioTest(mainScenario) {

    @Test
    fun reproduce() {
        query("/start") responds "i'm in modal"
        event("modal-event") responds "i'm in modal"
    }
}