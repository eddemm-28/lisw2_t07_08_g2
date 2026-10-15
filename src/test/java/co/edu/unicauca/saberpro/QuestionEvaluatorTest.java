package co.edu.unicauca.saberpro;

import static org.junit.jupiter.api.Assertions.*;

import co.edu.unicauca.saberpro.domain.model.Question;
import co.edu.unicauca.saberpro.templatemethod.*;
import java.util.List;
import org.junit.jupiter.api.Test;

class QuestionEvaluatorTest {

    @Test
    void multipleChoiceCorrect() {
        EvaluationResult r = new MultipleChoiceEvaluator().evaluate(TestFixtures.standard(), " b ");
        assertEquals(1.0, r.score());
        assertTrue(r.feedback().startsWith("Correcto"));
    }

    @Test
    void multipleChoiceWrong() {
        EvaluationResult r = new MultipleChoiceEvaluator().evaluate(TestFixtures.standard(), "A");
        assertEquals(0.0, r.score());
        assertTrue(r.feedback().startsWith("Incorrecto"));
    }

    @Test
    void multipleChoiceInvalidFormat() {
        assertThrows(IllegalArgumentException.class,
            () -> new MultipleChoiceEvaluator().evaluate(TestFixtures.standard(), "Z"));
        assertThrows(IllegalArgumentException.class,
            () -> new MultipleChoiceEvaluator().evaluate(TestFixtures.standard(), null));
    }

    @Test
    void trueFalse() {
        Question q = TestFixtures.question("Verdadero", "Falso", "-", "--", 0);
        TrueFalseEvaluator ev = new TrueFalseEvaluator();
        assertEquals(1.0, ev.evaluate(q, "v").score());
        assertEquals(0.0, ev.evaluate(q, "F").score());
        assertThrows(IllegalArgumentException.class, () -> ev.evaluate(q, "X"));
    }

    @Test
    void caseStudyPartialScore() {
        CaseStudyEvaluator ev = new CaseStudyEvaluator(List.of("capas", "acoplamiento", "cohesion", "mvc"));
        EvaluationResult r = ev.evaluate(TestFixtures.standard(), "Uso capas y MVC para bajar el acoplamiento");
        assertEquals(0.75, r.score(), 0.0001);
        assertTrue(r.feedback().contains("75%"));
    }
}
