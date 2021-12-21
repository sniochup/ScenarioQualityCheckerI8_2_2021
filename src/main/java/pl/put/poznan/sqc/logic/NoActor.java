package pl.put.poznan.sqc.logic;

import pl.put.poznan.sqc.scenario.Actor;
import pl.put.poznan.sqc.scenario.Scenario;
import pl.put.poznan.sqc.scenario.Step;

import java.util.ArrayList;

public class NoActor implements ScenarioInterface {

    /**
     * Title of return scenario
     */
    private final String title;
    private ArrayList<String> stepsNoActor;

    public NoActor(Scenario scenario) {
        this.title = scenario.getTitle();
        this.stepsNoActor = new ArrayList<>();
        calculate(scenario);
    }

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
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<String> getStepsNoActor() {
        return stepsNoActor;
    }
}
