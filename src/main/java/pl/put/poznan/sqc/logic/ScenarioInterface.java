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

    /**
     *
     * Method connects visitor to class that implemented calculate method
     * @param v object that visits
     * @param scenario scenario type variable
     */
    public void accept(Visitor v, Scenario scenario);
}