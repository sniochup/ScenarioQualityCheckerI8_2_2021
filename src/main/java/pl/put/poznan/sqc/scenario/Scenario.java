package pl.put.poznan.sqc.scenario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Class responsible for conversion file in json format
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Scenario {

    /**
     * Title of given scenario
     */
    @JsonProperty("title")
    protected String title;
    /**
     * System Actor of given scenario
     */
    @JsonProperty("systemActor")
    protected ArrayList<Actor> systemActor;
    /**
     * List with all actors occurring in a given scenario
     */
    @JsonProperty("actors")
    protected ArrayList<Actor> actors;
    /**
     * List with all steps and sub-steps in a given scenario
     */
    @JsonProperty("steps")
    protected ArrayList<Step> steps;

    /**
     *  Getter for title variable
     * @return variable title String value
     */
    public String getTitle() {
        return title;
    }

    /**
     *  Setter for title variable
     * @param title This scenario new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for systemActors variable
     * @return ArrayList with systemActors variables
     */
    public ArrayList<Actor> getSystemActor() {
        return systemActor;
    }

    /**
     *  Setter for systemActors variable
     * @param systemActor New List with all systemActors for given scenario
     */
    public void setSystemActor(ArrayList<Actor> systemActor) {
        this.systemActor = systemActor;
    }

    /**
     *  Getter for actors variable
     * @return ArrayList with Actors variables
     */
    public ArrayList<Actor> getActors() {
        return actors;
    }

    /**
     *  Setter for actors List
     * @param actors New List with all actors for given scenario
     */
    public void setActors(ArrayList<Actor> actors) {
        this.actors = actors;
    }

    /**
     *  Getter for steps variable
     * @return ArrayList with all step and sub-steps in given scenario
     */
    public ArrayList<Step> getSteps() {
        return steps;
    }

    /**
     * Setter for steps List
     * @param steps New List with all steps for given scenario
     */
    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }
}
