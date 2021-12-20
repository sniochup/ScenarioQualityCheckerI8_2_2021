package pl.put.poznan.sqc.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sqc.scenario.Scenario;

/**
 * Rest controller class
 */
@RestController
@RequestMapping("/sqc")
public class ScenarioQualityCheckerController {

    private static final Logger logger = LoggerFactory.getLogger(ScenarioQualityCheckerController.class);

    /**
     * steps-count endpoint service
     *
     * @param json - scenario received in request body
     * @return count of steps in scenario in json format
     */
    @GetMapping(path = "/steps-count", produces = "application/json")
    public Scenario getStepsCount(@RequestBody Scenario json) {

        logger.debug(json.toString());
        json.setTitle("Zmiana steps-count");

        return json;
    }

    /**
     * keyword endpoint service
     *
     * @param json - scenario received in request body
     * @return number of steps in the scenario which begin with the keyword (json format)
     */
    @GetMapping(path = "/keyword", produces = "application/json")
    public Scenario getKeyword(@RequestBody Scenario json) {

        logger.debug(json.toString());
        json.setTitle("Zmiana keyword");

        return json;
    }

    /**
     * no-actor endpoint service
     *
     * @param json - scenario received in request body
     * @return list of steps in the scenario which not begin with the actor name (json format)
     */
    @GetMapping(path = "/no-actor", produces = "application/json")
    public Scenario getNoActor(@RequestBody Scenario json) {

        logger.debug(json.toString());
        json.setTitle("Zmiana no-actor");

        return json;
    }

    /**
     * send-scenario endpoint service
     *
     * @param json - scenario received in request body
     * @return numbered scenario (json format)
     */
    @GetMapping(path = "/send-scenario", produces = "application/json")
    public Scenario getSendScenario(@RequestBody Scenario json) {

        logger.debug(json.toString());
        json.setTitle("Zmiana send-scenario");

        return json;
    }

}