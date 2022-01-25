package pl.put.poznan.testing.mocks;

import org.junit.jupiter.api.Test;
import pl.put.poznan.sqc.logic.Keyword;
import pl.put.poznan.sqc.scenario.Scenario;
import pl.put.poznan.sqc.scenario.Step;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class KeywordTest {

    Keyword keyword;

    @Test
    void getTitle() {
        Scenario scenario = mock(Scenario.class);
        when(scenario.getTitle()).thenReturn("Title");

        keyword = new Keyword(scenario);
        assertEquals(keyword.getTitle(), "Title");
    }

    @Test
    void emptyScenario() {
        Scenario scenario = mock(Scenario.class);
        when(scenario.getSteps()).thenReturn(new ArrayList<Step>());

        keyword = new Keyword(scenario);
        keyword.calculate(scenario);
        assertEquals(keyword.getNumberOfStepsWithKeywords(), 0);
    }

    @Test
    void stepWithoutKeyword() {
        Scenario scenario = mock(Scenario.class);
        Step step = mock(Step.class);

        ArrayList<Step> steps = new ArrayList<Step>();
        steps.add(step);

        when(step.getContent()).thenReturn("Sprzedający wystawia produkt na aukcję.");

        when(scenario.getSteps()).thenReturn(steps);

        keyword = new Keyword(scenario);
        keyword.calculate(scenario);
        assertEquals(keyword.getNumberOfStepsWithKeywords(), 0);
    }

    @Test
    void stepWithIf() {
        Scenario scenario = mock(Scenario.class);
        Step step = mock(Step.class);

        ArrayList<Step> steps = new ArrayList<Step>();
        steps.add(step);

        when(step.getContent()).thenReturn("IF wystawia produkt na aukcję.");

        when(scenario.getSteps()).thenReturn(steps);

        keyword = new Keyword(scenario);
        keyword.calculate(scenario);
        assertEquals(keyword.getNumberOfStepsWithKeywords(), 1);
    }

    @Test
    void stepWithElse() {
        Scenario scenario = mock(Scenario.class);
        Step step = mock(Step.class);

        ArrayList<Step> steps = new ArrayList<Step>();
        steps.add(step);

        when(step.getContent()).thenReturn("ELSE kupujący odsyła produkt.");

        when(scenario.getSteps()).thenReturn(steps);

        keyword = new Keyword(scenario);
        keyword.calculate(scenario);
        assertEquals(keyword.getNumberOfStepsWithKeywords(), 1);
    }

    @Test
    void stepWithForEach() {
        Scenario scenario = mock(Scenario.class);
        Step step = mock(Step.class);

        ArrayList<Step> steps = new ArrayList<Step>();
        steps.add(step);

        when(step.getContent()).thenReturn("FOR EACH sprzedający wysyła produkt.");

        when(scenario.getSteps()).thenReturn(steps);

        keyword = new Keyword(scenario);
        keyword.calculate(scenario);
        assertEquals(keyword.getNumberOfStepsWithKeywords(), 1);
    }

    @Test
    void stepWithForWithoutEach() {
        Scenario scenario = mock(Scenario.class);
        Step step = mock(Step.class);

        ArrayList<Step> steps = new ArrayList<Step>();
        steps.add(step);

        when(step.getContent()).thenReturn("FOR sprzedający wysyła produkt.");

        when(scenario.getSteps()).thenReturn(steps);

        keyword = new Keyword(scenario);
        keyword.calculate(scenario);
        assertEquals(keyword.getNumberOfStepsWithKeywords(), 0);
    }

    @Test
    void subStepWithOneKeyword() {
        Scenario scenario = mock(Scenario.class);
        Step step = mock(Step.class);

        Step subStep1 = mock(Step.class);
        Step subStep2 = mock(Step.class);
        Step subStep3 = mock(Step.class);

        ArrayList<Step> steps = new ArrayList<Step>();
        steps.add(step);

        ArrayList<Step> sSteps = new ArrayList<Step>();

        sSteps.add(subStep1);
        sSteps.add(subStep2);
        sSteps.add(subStep3);

        when(step.getSubSteps()).thenReturn(sSteps);
        when(step.getContent()).thenReturn("Sprzedający wystawia produkt na aukcję.");

        when(subStep1.getSubSteps()).thenReturn(new ArrayList<Step>());
        when(subStep1.getContent()).thenReturn("Sprzedający wysłał produkt.");
        when(subStep2.getSubSteps()).thenReturn(new ArrayList<Step>());
        when(subStep2.getContent()).thenReturn("IF kupujący odebrał produkt.");
        when(subStep3.getSubSteps()).thenReturn(new ArrayList<Step>());
        when(subStep3.getContent()).thenReturn("Sprzedający otrzymuje zapłatę.");

        when(scenario.getSteps()).thenReturn(steps);

        keyword = new Keyword(scenario);
        keyword.calculate(scenario);
        assertEquals(keyword.getNumberOfStepsWithKeywords(), 1);
    }

    @Test
    void subStepWithMultipleKeywords() {
        Scenario scenario = mock(Scenario.class);
        Step step = mock(Step.class);

        Step subStep1 = mock(Step.class);
        Step subStep2 = mock(Step.class);
        Step subStep3 = mock(Step.class);

        ArrayList<Step> steps = new ArrayList<Step>();
        steps.add(step);

        ArrayList<Step> sSteps = new ArrayList<Step>();

        sSteps.add(subStep1);
        sSteps.add(subStep2);
        sSteps.add(subStep3);

        when(step.getSubSteps()).thenReturn(sSteps);
        when(step.getContent()).thenReturn("Sprzedający wystawia produkt na aukcję.");

        when(subStep1.getSubSteps()).thenReturn(new ArrayList<Step>());
        when(subStep1.getContent()).thenReturn("Sprzedający wysłał produkt.");
        when(subStep2.getSubSteps()).thenReturn(new ArrayList<Step>());
        when(subStep2.getContent()).thenReturn("IF kupujący odebrał produkt.");
        when(subStep3.getSubSteps()).thenReturn(new ArrayList<Step>());
        when(subStep3.getContent()).thenReturn("FOR EACH sprzedający otrzymuje zapłatę.");

        when(scenario.getSteps()).thenReturn(steps);

        keyword = new Keyword(scenario);
        keyword.calculate(scenario);
        assertEquals(keyword.getNumberOfStepsWithKeywords(), 2);
    }

    @Test
    void subStepWithForEach() {
        Scenario scenario = mock(Scenario.class);
        Step step = mock(Step.class);

        Step subStep1 = mock(Step.class);

        ArrayList<Step> steps = new ArrayList<Step>();
        steps.add(step);

        ArrayList<Step> sSteps = new ArrayList<Step>();

        sSteps.add(subStep1);

        when(step.getSubSteps()).thenReturn(sSteps);
        when(step.getContent()).thenReturn("Sprzedający wystawia produkt na aukcję.");

        when(subStep1.getSubSteps()).thenReturn(new ArrayList<Step>());
        when(subStep1.getContent()).thenReturn("FOR EACH kupujący zakupił produkt.");

        when(scenario.getSteps()).thenReturn(steps);

        keyword = new Keyword(scenario);
        keyword.calculate(scenario);
        assertEquals(keyword.getNumberOfStepsWithKeywords(), 1);
    }

    @Test
    void stepAndSubStepWithKeywords() {
        Scenario scenario = mock(Scenario.class);
        Step step = mock(Step.class);

        Step subStep1 = mock(Step.class);
        Step subStep2 = mock(Step.class);
        Step subStep3 = mock(Step.class);

        ArrayList<Step> steps = new ArrayList<Step>();
        steps.add(step);

        ArrayList<Step> sSteps = new ArrayList<Step>();

        sSteps.add(subStep1);
        sSteps.add(subStep2);
        sSteps.add(subStep3);

        when(step.getSubSteps()).thenReturn(sSteps);
        when(step.getContent()).thenReturn("ELSE sprzedający wystawia produkt na aukcję.");

        when(subStep1.getSubSteps()).thenReturn(new ArrayList<Step>());
        when(subStep1.getContent()).thenReturn("Sprzedający wysłał produkt.");
        when(subStep2.getSubSteps()).thenReturn(new ArrayList<Step>());
        when(subStep2.getContent()).thenReturn("IF kupujący odebrał produkt.");
        when(subStep3.getSubSteps()).thenReturn(new ArrayList<Step>());
        when(subStep3.getContent()).thenReturn("FOR EACH sprzedający otrzymuje zapłatę.");

        when(scenario.getSteps()).thenReturn(steps);

        keyword = new Keyword(scenario);
        keyword.calculate(scenario);
        assertEquals(keyword.getNumberOfStepsWithKeywords(), 3);
    }

    @Test
    void notEmptyWithoutKeywordsWithFor() {
        Scenario scenario = mock(Scenario.class);
        Step step = mock(Step.class);

        Step subStep1 = mock(Step.class);
        Step subStep2 = mock(Step.class);
        Step subStep3 = mock(Step.class);

        ArrayList<Step> steps = new ArrayList<Step>();
        steps.add(step);

        ArrayList<Step> sSteps = new ArrayList<Step>();

        sSteps.add(subStep1);
        sSteps.add(subStep2);
        sSteps.add(subStep3);

        when(step.getSubSteps()).thenReturn(sSteps);
        when(step.getContent()).thenReturn("Sprzedający wystawia produkt na aukcję.");

        when(subStep1.getSubSteps()).thenReturn(new ArrayList<Step>());
        when(subStep1.getContent()).thenReturn("Sprzedający wysłał produkt.");
        when(subStep2.getSubSteps()).thenReturn(new ArrayList<Step>());
        when(subStep2.getContent()).thenReturn("Kupujący odebrał produkt.");
        when(subStep3.getSubSteps()).thenReturn(new ArrayList<Step>());
        when(subStep3.getContent()).thenReturn("FOR sprzedający otrzymuje zapłatę.");

        when(scenario.getSteps()).thenReturn(steps);

        keyword = new Keyword(scenario);
        keyword.calculate(scenario);
        assertEquals(keyword.getNumberOfStepsWithKeywords(), 0);
    }
}