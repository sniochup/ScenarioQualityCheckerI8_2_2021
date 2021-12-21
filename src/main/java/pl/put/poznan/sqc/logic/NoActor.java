package pl.put.poznan.sqc.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.sqc.rest.ScenarioQualityCheckerController;
import pl.put.poznan.sqc.scenario.Actor;
import pl.put.poznan.sqc.scenario.Scenario;
import pl.put.poznan.sqc.scenario.Step;

import java.util.ArrayList;

/**
 * NoActor class implementation
 */
public class NoActor implements ScenarioInterface {

    /**
     * logging support param in LoggerFactory type
     */
    private static final Logger logger = LoggerFactory.getLogger(ScenarioQualityCheckerController.class);
    /**
     * Title of return scenario
     */
    private final String title;
    /**
     * List of steps which not begin with an actor
     */
    private ArrayList<String> stepsNoActor;

    /**
     * NoActor class constructor
     *
     * @param scenario in Scenario type
     */
    public NoActor(Scenario scenario) {
        this.title = scenario.getTitle();
        this.stepsNoActor = new ArrayList<>();
    }

    /**
     * A method calculating number of sub-steps without any actor in the beginning of a scenario sub-step (and sub-step - recursively)
     *
     * @param step     for which we check the sub-step
     * @param tempList list of the actor name and keywords
     */
    private void checkSubSteps(Step step, ArrayList<String> tempList) {
        if (step.getSubSteps().size() != 0) {
            for (Step s : step.getSubSteps()) {
                String content[] = s.getContent().split(" ");
                if (!tempList.contains(content[0])) {
                    stepsNoActor.add(s.getContent());
                }
                checkSubSteps(s, tempList);
            }
        }
    }

    /**
     * A method calculating number of steps without actor in the beginning of a scenario step
     *
     * @param scenario in Scenario type
     */
    @Override
    public void calculate(Scenario scenario) {
        if (scenario.getSteps().size() == 0) {
            return;
        } else {
            ArrayList<String> tempList = new ArrayList<>();
            for (Actor a : scenario.getActors()) {
                tempList.add(a.getName());
            }
            for (Actor a : scenario.getSystemActor()) {
                tempList.add(a.getName());
            }
            tempList.add("IF");
            tempList.add("IF:");
            tempList.add("ELSE");
            tempList.add("FOR");
            tempList.add("EACH");

            for (Step s : scenario.getSteps()) {
                String content[] = s.getContent().split(" ");
                if (!tempList.contains(content[0])) {
                    stepsNoActor.add(s.getContent());
                }
                checkSubSteps(s, tempList);
            }
        }
        logger.info("NoActor calculate - steps no actor: " + stepsNoActor);
    }

    /**
     * Method connects visitor to class that implemented calculate method
     *
     * @param v        object that visits
     * @param scenario scenario type variable
     */
    @Override
    public void accept(Visitor v, Scenario scenario) {
        v.visit(this, scenario);
    }

    /**
     * Getter for title private variable
     *
     * @return variable title String value
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter for stepsNoActor private variable
     *
     * @return variable stepsNoActor List of Strings value
     */
    public ArrayList<String> getStepsNoActor() {
        return stepsNoActor;
    }
}
