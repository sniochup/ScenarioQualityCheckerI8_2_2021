package pl.put.poznan.sqc.scenario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Step {
    @JsonProperty("content")
    private String content;
    @JsonProperty("subSteps")
    private ArrayList<Step> subSteps;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<Step> getSubSteps() {
        return subSteps;
    }

    public void setSubSteps(ArrayList<Step> subSteps) {
        this.subSteps = subSteps;
    }
}
