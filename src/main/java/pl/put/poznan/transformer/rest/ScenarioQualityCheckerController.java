package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.ScenarioQualityChecker;

import java.util.Arrays;


@RestController
public class ScenarioQualityCheckerController {

    private static final Logger logger = LoggerFactory.getLogger(ScenarioQualityCheckerController.class);

    @RequestMapping(method = RequestMethod.GET, path = "/steps-count/{filename}", produces = "application/json")
    public String getStepsCount(@PathVariable String filename) {

        logger.debug(filename);

        // perform the transformation, you should run your logic here, below is just a silly example
//        ScenarioQualityChecker transformer = new ScenarioQualityChecker(transforms);

        return "get steps count " + filename;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/keyword/{filename}", produces = "application/json")
    public String getKeyword(@PathVariable String filename) {

        logger.debug(filename);
        return "get keyword "+ filename;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/no-actor/{filename}", produces = "application/json")
    public String getNoActor(@PathVariable String filename) {

        logger.debug(filename);
        return "get no actor "+ filename;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/send-scenario/{filename}", produces = "application/json")
    public String getSendScenario(@PathVariable String filename) {

        logger.debug(filename);
        return "get send scenario "+ filename;
    }



    @RequestMapping(method = RequestMethod.POST, path = "/post/{test}", produces = "application/json")
    public String post(@PathVariable String test, @RequestBody String[] transforms) {

        // log the parameters
        logger.debug(test);
        logger.debug(Arrays.toString(transforms));

        // perform the transformation, you should run your logic here, below is just a silly example
        ScenarioQualityChecker transformer = new ScenarioQualityChecker(transforms);
        return transformer.transform(test);
    }
}


