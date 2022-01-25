package pl.put.poznan.sqc.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.sqc.rest.ScenarioQualityCheckerController;
import pl.put.poznan.sqc.scenario.Actor;
import pl.put.poznan.sqc.scenario.Scenario;
import pl.put.poznan.sqc.scenario.Step;

import java.util.ArrayList;

/**
 * Interaction counting class implementation
 */
public class Interaction implements ScenarioInterface {

    /**
     * logging support param in LoggerFactory type
     */
    private static final Logger logger = LoggerFactory.getLogger(ScenarioQualityCheckerController.class);

    /**
     * Title of return scenario
     */
    private final String title;

    /**
     * List of steps with interactions between actors
     */
    private ArrayList<String> StepsWithInteractions;

    /**
     * Getter for StepsWithInteractions private variable
     *
     * @return variable StepsWithInteractions List of Strings value
     */
    public ArrayList<String> getStepsWithInteractions() {
        return StepsWithInteractions;
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
     * Interaction class constructor
     *
     * @param scenario in Scenario type
     */
    public Interaction(Scenario scenario) {

        this.title = scenario.getTitle();
        this.StepsWithInteractions = new ArrayList<>();

    }

    /**
     * Interactions checking method for sub-steps and its sub-steps recursively
     *
     * @param step      for which we check the sub-step
     * @param ActorList list of the actor names
     */
    public void checkSubSteps(Step step, ArrayList<String> ActorList) {
        if (step.getSubSteps().size() != 0) {
            for (Step subs : step.getSubSteps()) {
                String content[] = subs.getContent().split(" ");
                int checkValue = 0;
                for (String Oneword : content) {
                    if (ActorList.contains(Oneword)) {
                        checkValue++;
                    }
                }
                if (checkValue > 1) {
                    StepsWithInteractions.add(subs.getContent());
                }
                checkSubSteps(subs, ActorList);
            }

        }
    }

    /**
     * Interactions checking method
     *
     * @param scenario scenario type variable
     */
    @Override
    public void calculate(Scenario scenario) {

        if (scenario.getSteps().size() == 0) {
            return;
        } else {
            ArrayList<String> temp = new ArrayList<>();
            for (Actor actor : scenario.getActors()) {
                temp.add(actor.getName());
            }
            for (Actor actor : scenario.getSystemActor()) {
                temp.add(actor.getName());
            }

            for (Step s : scenario.getSteps()) {
                String content[] = s.getContent().split(" ");
                int checkValue = 0;
                for (String Oneword : content) {
                    if (temp.contains(Oneword)) {
                        checkValue++;
                    }
                }
                if (checkValue > 1) {
                    StepsWithInteractions.add(s.getContent());
                }
                checkSubSteps(s, temp);
            }
        }
        logger.info("Interactions calculate - StepsWithInteractions: " + StepsWithInteractions.toString());
    }

    /**
     * Method connects visitor to class that implemented calculate method
     *
     * @param v        object that visits
     * @param scenario Scenario type variable
     */
    @Override
    public void accept(Visitor v, Scenario scenario) {
        v.visit(this, scenario);
    }

}
