package pl.put.poznan.sqc.logic;

import pl.put.poznan.sqc.scenario.Scenario;

public class Visitor {
    public void visit(Keyword key, Scenario s) {
        key.calculate(s);
    }

    public void visit(NoActor act, Scenario s) {
        act.calculate(s);
    }

    public void visit(StepsCount sCount, Scenario s) {
        sCount.calculate(s);
    }

    public void visit(Numbered num, Scenario s) {
        num.calculate(s);
    }

    public void visit(Simplified simplified, Scenario s) {
        simplified.calculate(s);
    }

    public void visit(Interaction interaction, Scenario s) {
        interaction.calculate(s);
    }
}