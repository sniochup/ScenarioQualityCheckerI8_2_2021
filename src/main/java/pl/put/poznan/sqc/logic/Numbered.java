package pl.put.poznan.sqc.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.sqc.rest.ScenarioQualityCheckerController;
import pl.put.poznan.sqc.scenario.Scenario;

public class Numbered implements ScenarioInterface {

    private static final Logger logger = LoggerFactory.getLogger(ScenarioQualityCheckerController.class);

    private final String title;

    public String getTitle() {
        return title;
    }

    public Numbered(Scenario scenario) {
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