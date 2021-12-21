package pl.put.poznan.sqc.logic;

import pl.put.poznan.sqc.scenario.Scenario;

public class Visitor {
    public void visit(Keyword key, Scenario s)
    {
        key.calculate(s);
    }
    public void visit(NoActor act, Scenario s)
    {
        act.calculate(s);
    }
    public void visit(StepsCount scount, Scenario s)
    {
        scount.calculate(s);
    }

}
