package pl.put.poznan.sqc.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.sqc.rest.ScenarioQualityCheckerController;
import pl.put.poznan.sqc.scenario.Scenario;
import pl.put.poznan.sqc.scenario.Step;

/**
 * Numbered class implementation
 */
public class Numbered implements ScenarioInterface {

    /**
     * logging support param in LoggerFactory type
     */
    private static final Logger logger = LoggerFactory.getLogger(ScenarioQualityCheckerController.class);

    /**
     * returned scenario with numbered steps
     */
    private final Scenario scenario;

    /**
     * Numbered class constructor
     *
     * @param scenario in Scenario type
     */
    public Numbered(Scenario scenario) {
        this.scenario = scenario;
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
     * sub-steps numbering method and its sub-steps recursively
     *
     * @param step      for which we check the sub-step
     * @param stepBeg   step prefix with its number
     */
    private void checkSubSteps(Step step, String stepBeg) {
        int stepNum = 1;
        String subStepBeg;
        for (Step s : step.getSubSteps()) {
            subStepBeg = stepBeg + stepNum++ + ".";
            s.setContent(subStepBeg + " " + s.getContent());
            if (s.getSubSteps().size() != 0) {
                checkSubSteps(s, subStepBeg);
            }
        }
    }

    /**
     * Steps numbering method
     *
     * @param scenario scenario type variable
     */
    @Override
    public void calculate(Scenario scenario) {
        if (scenario.getSteps().size() == 0) return;

        int stepNum = 1;
        String stepBeg;

        for (Step s : scenario.getSteps()) {
            stepBeg = stepNum++ + ".";
            s.setContent(stepBeg + " " + s.getContent());
            if (s.getSubSteps().size() != 0) {
                checkSubSteps(s, stepBeg);
            }
        }

        logger.info("Numbered calculate - completed");
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