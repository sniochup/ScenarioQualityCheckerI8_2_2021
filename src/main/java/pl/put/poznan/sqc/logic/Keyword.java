package pl.put.poznan.sqc.logic;

import pl.put.poznan.sqc.scenario.Scenario;
import pl.put.poznan.sqc.scenario.Step;

/**
 * Keyword counting class implementation
 */
public class Keyword implements ScenarioInterface {

    /**
     * Title of return scenario
     */
    private final String title;
    /**
     * Number of steps with keywords IF, ELSE and FOR EACH in a scenario
     */
    private Integer numberOfStepsWithKeywords = 0;

    /**
     * Keyword class constructor
     *
     * @param scenario in Scenario type
     */
    public Keyword(Scenario scenario) {
        this.title = scenario.getTitle();
        calculate(scenario);
    }

    /**
     * A method checking and counting keywords in subSteps od each scenario step (and subStep - recursively)
     *
     * @param step in Step type
     */
    private void checkSubSteps(Step step) {
        if (step.getSubSteps().size() != 0) {
            for (Step subs : step.getSubSteps()) {
                String content[] = subs.getContent().split(" ");
                if (content[0].contains("IF") || content[0].contains("ELSE") || (content[0].equals("FOR") && content[1].contains("EACH"))) {
                    numberOfStepsWithKeywords += 1;
                }
                checkSubSteps(subs);
            }
        }
    }

    /**
     * A method calculating number of steps with keywords IF, ELSE and FOR EACH in a scenario
     *
     * @param scenario in Scenario type
     */
    @Override
    public void calculate(Scenario scenario) {
        if (scenario.getSteps().size() == 0) {
            numberOfStepsWithKeywords = 0;
        } else {
            for (Step s : scenario.getSteps()) {
                String content[] = s.getContent().split(" ");
                if (content[0].contains("IF") || content[0].contains("ELSE") || (content[0].equals("FOR") && content[1].contains("EACH"))) {
                    numberOfStepsWithKeywords += 1;
                }
                checkSubSteps(s);
            }
        }
    }

    /**
     * Getter for numberOfStepsWithKeywords private variable
     *
     * @return variable numberOfStepsWithKeywords integer value
     */
    public Integer getNumberOfStepsWithKeywords() {
        return numberOfStepsWithKeywords;
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
