package pl.put.poznan.sqc.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.sqc.rest.ScenarioQualityCheckerController;
import pl.put.poznan.sqc.scenario.Scenario;
import pl.put.poznan.sqc.scenario.Step;

/**
 * Keyword counting class implementation
 */
public class Keyword implements ScenarioInterface {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(ScenarioQualityCheckerController.class);
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
        logger.debug(step.getContent() + numberOfStepsWithKeywords.toString());
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
        logger.debug(numberOfStepsWithKeywords.toString());
    }
    /**
     *
     * Method connects visitor to class that implemented calculate method
     * @param v object that visits
     * @param s scenario type variable
     */
    @Override
    public void accept(Visitor v, Scenario s) {
        v.visit(this, s);
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
