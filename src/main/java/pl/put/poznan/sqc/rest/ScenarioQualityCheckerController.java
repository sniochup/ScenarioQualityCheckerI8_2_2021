package pl.put.poznan.sqc.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sqc.logic.*;
import pl.put.poznan.sqc.scenario.Scenario;

/**
 * Rest controller class
 */
@RestController
@RequestMapping("/sqc")
public class ScenarioQualityCheckerController {

    /**
     *  logging support param in LoggerFactory type
     */
    private static final Logger logger = LoggerFactory.getLogger(ScenarioQualityCheckerController.class);

    /**
     * steps-count endpoint service
     *
     * @param json scenario received in request body
     * @return count of steps in scenario in json format
     */
    @GetMapping(path = "/steps-count", produces = "application/json")
    public StepsCount getStepsCount(@RequestBody Scenario json) {
        logger.debug("REST getStepsCount with body: "+ json);
        logger.info("Start steps-count function");
        StepsCount stepsCount = new StepsCount(json);
        stepsCount.accept(new Visitor(), json);
        return stepsCount;
    }

    /**
     * keyword endpoint service
     *
     * @param json scenario received in request body
     * @return number of steps in the scenario which begin with the keyword (json format)
     */
    @GetMapping(path = "/keyword", produces = "application/json")
    public Keyword getKeyword(@RequestBody Scenario json) {
        logger.debug("REST getKeyword with body: "+ json);
        logger.info("Start keyword function");
        Keyword keyword = new Keyword(json);
        keyword.accept(new Visitor(), json);
        return keyword;
    }

    /**
     * no-actor endpoint service
     *
     * @param json scenario received in request body
     * @return list of steps in the scenario which not begin with the actor name (json format)
     */
    @GetMapping(path = "/no-actor", produces = "application/json")
    public NoActor getNoActor(@RequestBody Scenario json) {
        logger.debug("REST getNoActor with body: "+ json);
        logger.info("Start noActor function");
        NoActor noActor = new NoActor(json);
        noActor.accept(new Visitor(), json);
        return noActor;
    }

    /**
     * numbered endpoint service
     *
     * @param json scenario received in request body
     * @return numbered scenario (json format)
     */
    @GetMapping(path = "/numbered", produces = "application/json")
    public Numbered getNumbered(@RequestBody Scenario json) {
        logger.debug("REST getNumbered with body: "+ json);
        logger.info("Start numbered function");
        Numbered numbered = new Numbered(json);
        numbered.accept(new Visitor(), json);
        return numbered;
    }

    /**
     * simplified endpoint service
     *
     * @param json scenario received in request body
     * @param level selected level up to which will be returned sub-scenarios (default = 1 which means that application returns scenario without sub-scenarios)
     * @return simplified scenario containing only sub-scenarios up to a certain level (json format)
     */
    @GetMapping(path = "/simplified", produces = "application/json")
    public Simplified getSimplified(@RequestBody Scenario json, @RequestParam(value="level", defaultValue = "1") Integer level) {
        logger.debug("REST getSimplified with: body - "+ json + "; level (param) - "+level);
        logger.info("Start simplified function");
        Simplified simplified = new Simplified(json, level);
        simplified.accept(new Visitor(), json);
        return simplified;
    }

    /**
     * interaction endpoint service
     *
     * @param json scenario received in request body
     * @return list of steps in the scenario which contains more than one actor (json format)
     */
    @GetMapping(path = "/interaction", produces = "application/json")
    public Interaction getInteraction(@RequestBody Scenario json) {
        logger.debug("REST getInteraction with body: "+ json);
        logger.info("Start interaction function");
        Interaction interaction = new Interaction(json);
        interaction.accept(new Visitor(), json);
        return interaction;
    }

}