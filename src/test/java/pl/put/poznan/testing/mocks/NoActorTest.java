package pl.put.poznan.testing.mocks;

import org.junit.jupiter.api.Test;
import pl.put.poznan.sqc.logic.NoActor;
import pl.put.poznan.sqc.scenario.Actor;
import pl.put.poznan.sqc.scenario.Scenario;
import pl.put.poznan.sqc.scenario.Step;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NoActorTest {
    NoActor noActor;

    @Test
    void getTitle() {
        Scenario scenario = mock(Scenario.class);
        when(scenario.getTitle()).thenReturn("Title");

        noActor = new NoActor(scenario);
        assertEquals(noActor.getTitle(), "Title");
    }

    @Test
    void emptyScenario() {
        Scenario scenario = mock(Scenario.class);
        when(scenario.getSteps()).thenReturn(new ArrayList<Step>());

        noActor = new NoActor(scenario);
        noActor.calculate(scenario);
        assertEquals(noActor.getStepsNoActor().size(), 0);
    }

    @Test
    void allStepsHaveActor() {
        Scenario scenario = mock(Scenario.class);
        Step step = mock(Step.class);
        Actor actor = mock(Actor.class);

        ArrayList<Step> steps = new ArrayList<Step>();
        steps.add(step);

        ArrayList<Actor> actors = new ArrayList<Actor>();
        actors.add(actor);

        when(actor.getName()).thenReturn("Sprzedający");

        when(step.getSubSteps()).thenReturn(new ArrayList<Step>());
        when(step.getContent()).thenReturn("Sprzedający wystawia produkt na aukcję.");

        when(scenario.getSteps()).thenReturn(steps);
        when(scenario.getActors()).thenReturn(actors);
        when(scenario.getSystemActor()).thenReturn(actors);

        noActor = new NoActor(scenario);
        noActor.calculate(scenario);
        assertEquals(noActor.getStepsNoActor().size(), 0);
    }

    @Test
    void stepWithoutActor() {
        Scenario scenario = mock(Scenario.class);
        Step step = mock(Step.class);
        Actor actor = mock(Actor.class);

        ArrayList<Step> steps = new ArrayList<Step>();
        steps.add(step);

        when(actor.getName()).thenReturn("");

        when(step.getSubSteps()).thenReturn(new ArrayList<Step>());
        when(step.getContent()).thenReturn("Sprzedający wystawia produkt na aukcję.");

        when(scenario.getSteps()).thenReturn(steps);
        when(scenario.getActors()).thenReturn(new ArrayList<Actor>());
        when(scenario.getSystemActor()).thenReturn(new ArrayList<Actor>());

        noActor = new NoActor(scenario);
        noActor.calculate(scenario);
        assertEquals(noActor.getStepsNoActor().size(), 1);
    }

    @Test
    void subStepsHaveActor() {
        Scenario scenario = mock(Scenario.class);
        Step step = mock(Step.class);

        Step subStep1 = mock(Step.class);
        Step subStep2 = mock(Step.class);
        Step subStep3 = mock(Step.class);

        Actor actor = mock(Actor.class);

        ArrayList<Step> steps = new ArrayList<Step>();
        steps.add(step);

        ArrayList<Step> sSteps = new ArrayList<Step>();

        sSteps.add(subStep1);
        sSteps.add(subStep2);
        sSteps.add(subStep3);

        ArrayList<Actor> actors = new ArrayList<Actor>();
        actors.add(actor);

        when(actor.getName()).thenReturn("Sprzedający");

        when(step.getSubSteps()).thenReturn(sSteps);
        when(step.getContent()).thenReturn("Sprzedający wystawia produkt na aukcję.");

        when(subStep1.getSubSteps()).thenReturn(new ArrayList<Step>());
        when(subStep1.getContent()).thenReturn("Sprzedający wyslal produkt.");
        when(subStep2.getSubSteps()).thenReturn(new ArrayList<Step>());
        when(subStep2.getContent()).thenReturn("Sprzedający odebral produkt.");
        when(subStep3.getSubSteps()).thenReturn(new ArrayList<Step>());
        when(subStep3.getContent()).thenReturn("Sprzedający wystawia produkt na aukcję.");

        when(scenario.getSteps()).thenReturn(steps);
        when(scenario.getActors()).thenReturn(actors);
        when(scenario.getSystemActor()).thenReturn(actors);

        noActor = new NoActor(scenario);
        noActor.calculate(scenario);
        assertEquals(noActor.getStepsNoActor().size(), 0);
    }

    @Test
    void subStepWithoutActor() {
        Scenario scenario = mock(Scenario.class);
        Step step = mock(Step.class);

        Step subStep1 = mock(Step.class);
        Step subStep2 = mock(Step.class);
        Step subStep3 = mock(Step.class);

        Actor actor = mock(Actor.class);

        ArrayList<Step> steps = new ArrayList<Step>();
        steps.add(step);

        ArrayList<Step> sSteps = new ArrayList<Step>();

        sSteps.add(subStep1);
        sSteps.add(subStep2);
        sSteps.add(subStep3);

        ArrayList<Actor> actors = new ArrayList<Actor>();
        actors.add(actor);

        when(actor.getName()).thenReturn("Sprzedający");

        when(step.getSubSteps()).thenReturn(sSteps);
        when(step.getContent()).thenReturn("Sprzedający wystawia produkt na aukcję.");

        when(subStep1.getSubSteps()).thenReturn(new ArrayList<Step>());
        when(subStep1.getContent()).thenReturn("wyslal produkt.");
        when(subStep2.getSubSteps()).thenReturn(new ArrayList<Step>());
        when(subStep2.getContent()).thenReturn("Sprzedający odebral produkt.");
        when(subStep3.getSubSteps()).thenReturn(new ArrayList<Step>());
        when(subStep3.getContent()).thenReturn("Sprzedający wystawia produkt na aukcję.");

        when(scenario.getSteps()).thenReturn(steps);
        when(scenario.getActors()).thenReturn(actors);
        when(scenario.getSystemActor()).thenReturn(actors);

        noActor = new NoActor(scenario);
        noActor.calculate(scenario);
        assertEquals(noActor.getStepsNoActor().size(), 1);
    }

    @Test
    void subStepsWithoutActor() {
        Scenario scenario = mock(Scenario.class);
        Step step = mock(Step.class);

        Step subStep1 = mock(Step.class);
        Step subStep2 = mock(Step.class);
        Step subStep3 = mock(Step.class);

        Actor actor = mock(Actor.class);

        ArrayList<Step> steps = new ArrayList<Step>();
        steps.add(step);

        ArrayList<Step> sSteps = new ArrayList<Step>();

        sSteps.add(subStep1);
        sSteps.add(subStep2);
        sSteps.add(subStep3);

        ArrayList<Actor> actors = new ArrayList<Actor>();
        actors.add(actor);

        when(actor.getName()).thenReturn("Sprzedający");

        when(step.getSubSteps()).thenReturn(sSteps);
        when(step.getContent()).thenReturn("Sprzedający wystawia produkt na aukcję.");

        when(subStep1.getSubSteps()).thenReturn(new ArrayList<Step>());
        when(subStep1.getContent()).thenReturn("wyslal produkt.");
        when(subStep2.getSubSteps()).thenReturn(new ArrayList<Step>());
        when(subStep2.getContent()).thenReturn("odebral produkt.");
        when(subStep3.getSubSteps()).thenReturn(new ArrayList<Step>());
        when(subStep3.getContent()).thenReturn("wystawia produkt na aukcję.");

        when(scenario.getSteps()).thenReturn(steps);
        when(scenario.getActors()).thenReturn(actors);
        when(scenario.getSystemActor()).thenReturn(actors);

        noActor = new NoActor(scenario);
        noActor.calculate(scenario);
        assertEquals(noActor.getStepsNoActor().size(), 3);
    }

    @Test
    void subStepsHaveActors() {
        Scenario scenario = mock(Scenario.class);
        Step step = mock(Step.class);

        Step subStep1 = mock(Step.class);
        Step subStep2 = mock(Step.class);
        Step subStep3 = mock(Step.class);

        Actor actor = mock(Actor.class);
        Actor actor2 = mock(Actor.class);

        ArrayList<Step> steps = new ArrayList<Step>();
        steps.add(step);

        ArrayList<Step> sSteps = new ArrayList<Step>();

        sSteps.add(subStep1);
        sSteps.add(subStep2);
        sSteps.add(subStep3);

        ArrayList<Actor> actors = new ArrayList<Actor>();
        actors.add(actor);
        actors.add(actor2);

        when(actor.getName()).thenReturn("Sprzedający");
        when(actor2.getName()).thenReturn("Kupujacy");

        when(step.getSubSteps()).thenReturn(sSteps);
        when(step.getContent()).thenReturn("Sprzedający wystawia produkt na aukcję.");

        when(subStep1.getSubSteps()).thenReturn(new ArrayList<Step>());
        when(subStep1.getContent()).thenReturn("Kupujacy wyslal produkt.");
        when(subStep2.getSubSteps()).thenReturn(new ArrayList<Step>());
        when(subStep2.getContent()).thenReturn("Sprzedający odebral produkt.");
        when(subStep3.getSubSteps()).thenReturn(new ArrayList<Step>());
        when(subStep3.getContent()).thenReturn("Sprzedający wystawia produkt na aukcję.");

        when(scenario.getSteps()).thenReturn(steps);
        when(scenario.getActors()).thenReturn(actors);
        when(scenario.getSystemActor()).thenReturn(actors);

        noActor = new NoActor(scenario);
        noActor.calculate(scenario);
        assertEquals(noActor.getStepsNoActor().size(), 0);
    }

    @Test
    void conditionalSt() {
        Scenario scenario = mock(Scenario.class);
        Step step = mock(Step.class);

        Step subStep1 = mock(Step.class);
        Step subStep2 = mock(Step.class);
        Step subStep3 = mock(Step.class);
        Step subStep4 = mock(Step.class);

        ArrayList<Step> steps = new ArrayList<Step>();
        steps.add(step);

        ArrayList<Step> sSteps = new ArrayList<Step>();

        sSteps.add(subStep1);
        sSteps.add(subStep2);
        sSteps.add(subStep3);
        sSteps.add(subStep4);

        when(step.getSubSteps()).thenReturn(sSteps);
        when(step.getContent()).thenReturn("IF wystawia produkt na aukcję.");

        when(subStep1.getSubSteps()).thenReturn(new ArrayList<Step>());
        when(subStep1.getContent()).thenReturn("EACH wyslal produkt.");
        when(subStep2.getSubSteps()).thenReturn(new ArrayList<Step>());
        when(subStep2.getContent()).thenReturn("ELSE odebral produkt.");
        when(subStep3.getSubSteps()).thenReturn(new ArrayList<Step>());
        when(subStep3.getContent()).thenReturn("FOR wystawia produkt na aukcję.");
        when(subStep4.getSubSteps()).thenReturn(new ArrayList<Step>());
        when(subStep4.getContent()).thenReturn("IF: wystawia produkt na aukcję.");

        when(scenario.getSteps()).thenReturn(steps);
        when(scenario.getActors()).thenReturn(new ArrayList<Actor>());
        when(scenario.getSystemActor()).thenReturn(new ArrayList<Actor>());

        noActor = new NoActor(scenario);
        noActor.calculate(scenario);
        assertEquals(noActor.getStepsNoActor().size(), 0);
    }

    @Test
    void subsubRec() {
        Scenario scenario = mock(Scenario.class);
        Step step = mock(Step.class);

        Step subStep1 = mock(Step.class);
        Step subStep2 = mock(Step.class);
        Step subStep3 = mock(Step.class);
        Step subStep4 = mock(Step.class);

        ArrayList<Step> steps = new ArrayList<Step>();
        steps.add(step);

        ArrayList<Step> sSteps = new ArrayList<Step>();
        ArrayList<Step> sSteps2 = new ArrayList<Step>();

        sSteps.add(subStep1);
        sSteps.add(subStep2);
        sSteps.add(subStep3);
        sSteps2.add(subStep4);

        when(step.getSubSteps()).thenReturn(sSteps);
        when(step.getContent()).thenReturn("IF wystawia produkt na aukcję.");

        when(subStep1.getSubSteps()).thenReturn(new ArrayList<Step>());
        when(subStep1.getContent()).thenReturn("EACH wyslal produkt.");
        when(subStep2.getSubSteps()).thenReturn(new ArrayList<Step>());
        when(subStep2.getContent()).thenReturn("odebral produkt.");
        when(subStep3.getSubSteps()).thenReturn(sSteps2);
        when(subStep3.getContent()).thenReturn("FOR wystawia produkt na aukcję.");
        when(subStep4.getSubSteps()).thenReturn(new ArrayList<Step>());
        when(subStep4.getContent()).thenReturn("wystawia produkt na aukcję.");

        when(scenario.getSteps()).thenReturn(steps);
        when(scenario.getActors()).thenReturn(new ArrayList<Actor>());
        when(scenario.getSystemActor()).thenReturn(new ArrayList<Actor>());

        noActor = new NoActor(scenario);
        noActor.calculate(scenario);
        assertEquals(noActor.getStepsNoActor().size(), 2);
    }
}