package pl.put.poznan.sqc.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.sqc.rest.ScenarioQualityCheckerController;
import pl.put.poznan.sqc.scenario.Scenario;

public class Interaction implements ScenarioInterface {

    private static final Logger logger = LoggerFactory.getLogger(ScenarioQualityCheckerController.class);

    public String getTitle() {
        return title;
    }

    private final String title;

    public Interaction(Scenario scenario) {
        this.title = scenario.getTitle();
    }

    @Override
    public void calculate(Scenario scenario) {

    }

    @Override
    public void accept(Visitor v, Scenario scenario) {
        v.visit(this, scenario);
    }

}
