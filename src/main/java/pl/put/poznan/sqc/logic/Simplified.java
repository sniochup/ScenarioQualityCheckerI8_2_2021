package pl.put.poznan.sqc.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.sqc.rest.ScenarioQualityCheckerController;
import pl.put.poznan.sqc.scenario.Scenario;
import pl.put.poznan.sqc.scenario.Step;

import java.util.ArrayList;

/**
 * Simplified class implementation
 */
public class Simplified implements ScenarioInterface {

    /**
     * logging support param in LoggerFactory type
     */
    private static final Logger logger = LoggerFactory.getLogger(ScenarioQualityCheckerController.class);

    /**
     * returned simplified scenario
     */
    private final Scenario scenario;

    /**
     * Max lvl of returned scenario
     */
    private final Integer level;

    /**
     * Simplified class constructor
     *
     * @param scenario in Scenario type
     * @param level max lvl of returned scenario
     */
    public Simplified(Scenario scenario, Integer level) {
        this.scenario = scenario;
        this.level = level;
    }

    /**
     * Getter for scenario private variable
     *
     * @return variable scenario in Scenario type
     */
    public Scenario getScenario() {
        return scenario;
    }

    /**
     * Getter for level private variable
     *
     * @return variable level in Integer type
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * A method that simplifies the scenario sub-steps to a certain level and its sub-steps recursively
     *
     * @param step for which we check the sub-step
     * @param lvl current sub-step level
     */
    private void checkSubSteps(Step step, int lvl) {
        if (lvl > getLevel()) {
            step.setSubSteps(new ArrayList<>());
            return;
        }

        for (Step s : step.getSubSteps()) {
            if (s.getSubSteps().size() != 0) {
                checkSubSteps(s, lvl + 1);
            }
        }
    }

    /**
     * A method that simplifies the scenario steps to a certain level
     *
     * @param scenario scenario type variable
     */
    @Override
    public void calculate(Scenario scenario) {
        if (scenario.getSteps().size() == 0) return;
        int lvl = 1;

        if (lvl > getLevel()) {
            scenario.setSteps(new ArrayList<>());
            return;
        }

        for (Step s : scenario.getSteps()) {
            if (s.getSubSteps().size() != 0) {
                checkSubSteps(s, lvl + 1);
            }
        }

        logger.info("Simplified calculate - completed");
    }

    /**
     * Method connects visitor to class that implemented calculate method
     *
     * @param v object that visits
     * @param scenario scenario type variable
     */
    @Override
    public void accept(Visitor v, Scenario scenario) {
        v.visit(this, scenario);
    }

}