package pl.put.poznan.sqc.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.sqc.rest.ScenarioQualityCheckerController;
import pl.put.poznan.sqc.scenario.Scenario;
import pl.put.poznan.sqc.scenario.Step;

/**
 * StepsCount class implementation
 */
public class StepsCount implements ScenarioInterface {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(ScenarioQualityCheckerController.class);
    /**
     * Title of return scenario
     */
    private final String title;
    /**
     * Number of steps in a scenario
     */
    private Integer numberOfSteps = 0;

    /**
     * StepsCount class constructor
     *
     * @param scenario in Scenario type
     */
    public StepsCount(Scenario scenario) {
        this.title = scenario.getTitle();
    }

    /**
     * A method checking and counting subSteps of each scenario step (and subStep - recursively)
     *
     * @param step in Step type
     */
    private void checkSubSteps(Step step) {
        if (step.getSubSteps().size() != 0) {
            numberOfSteps += step.getSubSteps().size();
            for (Step subs : step.getSubSteps()) {
                checkSubSteps(subs);
            }
        }
        logger.debug(step.getContent() + numberOfSteps.toString());
    }

    /**
     * A method calculating number of steps in a scenario
     *
     * @param scenario in Scenario type
     */
    @Override
    public void calculate(Scenario scenario) {
        if (scenario.getSteps().size() == 0) {
            numberOfSteps = 0;
        } else {
            numberOfSteps += scenario.getSteps().size();
            for (Step s : scenario.getSteps()) {
                checkSubSteps(s);
            }
        }
        logger.debug(numberOfSteps.toString());
    }
    /**
     *
     * Method connects visitor to class that implemented calculate method
     * @param v object that visits
     * @param scenario scenario type variable
     */
    @Override
    public void accept(Visitor v, Scenario scenario) {
        v.visit(this, scenario);
    }

    /**
     * Getter for numberOfSteps private variable
     *
     * @return variable numberOfSteps integer value
     */
    public Integer getNumberOfSteps() {
        return numberOfSteps;
    }

    /**
     * Getter for title private variable
     *
     * @return variable title String value
     */
    public String getTitle() {
        return title;
    }

}