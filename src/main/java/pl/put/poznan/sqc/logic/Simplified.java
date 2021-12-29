package pl.put.poznan.sqc.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.sqc.rest.ScenarioQualityCheckerController;
import pl.put.poznan.sqc.scenario.Scenario;

public class Simplified implements ScenarioInterface {

    private static final Logger logger = LoggerFactory.getLogger(ScenarioQualityCheckerController.class);

    public String getTitle() {
        return title;
    }

    public Integer getLevel() {
        return level;
    }

    private final String title;
    private final Integer level;

    public Simplified(Scenario scenario, Integer level) {
        this.title = scenario.getTitle();
        this.level = level;
    }

    @Override
    public void calculate(Scenario scenario) {

    }

    @Override
    public void accept(Visitor v, Scenario scenario) {
        v.visit(this, scenario);
    }

}