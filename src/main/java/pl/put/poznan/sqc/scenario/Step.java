package pl.put.poznan.sqc.scenario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Class which represents single step in scenario
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Step {

    /**
     * Content of given step
     */
    @JsonProperty("content")
    private String content;

    /**
     *  List with all sub-steps for a given step
     */
    @JsonProperty("subSteps")
    private ArrayList<Step> subSteps;

    /**
     *  Getter for content variable
     * @return variable content String value
     */
    public String getContent() {
        return content;
    }

    /**
     *  Setter for content variable
     * @param content new content for this step
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Getter for sub-steps variable
     * @return ArrayList with all sub-steps for given step
     */
    public ArrayList<Step> getSubSteps() {
        return subSteps;
    }

    /**
     *  Setter for sub-steps variable
     * @param subSteps new ArrayList with all sub-steps
     */
    public void setSubSteps(ArrayList<Step> subSteps) {
        this.subSteps = subSteps;
    }
}