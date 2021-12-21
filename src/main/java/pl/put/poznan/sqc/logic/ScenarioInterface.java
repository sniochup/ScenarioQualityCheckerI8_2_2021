package pl.put.poznan.sqc.logic;

import pl.put.poznan.sqc.scenario.Scenario;

/**
 *  Interface containing method declarations that allow the implementation of new functionalities
 */
public interface ScenarioInterface {
    /**
     * Method which implements new functionality
     * @param scenario scenario type variable
     */
    void calculate(Scenario scenario);
}