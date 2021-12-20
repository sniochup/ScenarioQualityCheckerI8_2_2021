package pl.put.poznan.sqc.logic;
import com.fasterxml.jackson.annotation.JsonProperty;
import pl.put.poznan.sqc.scenario.Actor;
import pl.put.poznan.sqc.scenario.Scenario;
import pl.put.poznan.sqc.scenario.Step;

import java.util.ArrayList;


public interface ScenarioAbstractClass{

    public void calculate(Scenario scenario);


}

