package pl.put.poznan.sqc.scenario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class which represents single actor in scenario
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Actor {

    /**
     * Name of given actor
     */
    @JsonProperty("actor")
    private String name;

    /**
     * Getter for name variable
     * @return String this is name variable
     */
    public String getName() {
        return name;
    }

    /**
     *  Setter for content variable
     * @param name new name for actor
     */
    public void setName(String name) {
        this.name = name;
    }
}
