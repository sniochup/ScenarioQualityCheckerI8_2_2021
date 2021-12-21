package pl.put.poznan.sqc.logic;

import pl.put.poznan.sqc.scenario.Scenario;
import pl.put.poznan.sqc.scenario.Step;

/**
 * StepsCount class implementation
 */
public class StepsCount implements ScenarioInterface {

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
        calculate(scenario);
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