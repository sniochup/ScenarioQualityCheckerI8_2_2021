package pl.put.poznan.sqc.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sqc.scenario.Scenario;


@RestController
@RequestMapping("/sqc")
public class ScenarioQualityCheckerController {

    private static final Logger logger = LoggerFactory.getLogger(ScenarioQualityCheckerController.class);

    @GetMapping(path = "/steps-count", produces = "application/json")
    public Scenario getStepsCount(@RequestBody Scenario json) {

        logger.debug(json.toString());
        json.setTitle("Zmiana steps-count");

        return json;
    }

    @GetMapping(path = "/keyword", produces = "application/json")
    public Scenario getKeyword(@RequestBody Scenario json) {

        logger.debug(json.toString());
        json.setTitle("Zmiana keyword");

        return json;
    }

    @GetMapping(path = "/no-actor", produces = "application/json")
    public Scenario getNoActor(@RequestBody Scenario json) {

        logger.debug(json.toString());
        json.setTitle("Zmiana no-actor");

        return json;
    }

    @GetMapping(path = "/send-scenario", produces = "application/json")
    public Scenario getSendScenario(@RequestBody Scenario json) {

        logger.debug(json.toString());
        json.setTitle("Zmiana send-scenario");

        return json;
    }

}


