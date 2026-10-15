package co.edu.unicauca.saberpro;

import static org.junit.jupiter.api.Assertions.*;

import co.edu.unicauca.saberpro.decorator.*;
import org.junit.jupiter.api.Test;

class QuestionDecoratorTest {

    @Test
    void basicQuestion() {
        QuestionComponent c = new BasicQuestion(TestFixtures.standard());
        assertEquals(1.0, c.getMaxScore());
        assertEquals("Enunciado?", c.getDescription());
    }

    @Test
    void highDifficultyMultipliesScore() {
        QuestionComponent c = new HighDifficultyQuestion(new BasicQuestion(TestFixtures.standard()), 2.0);
        assertEquals(2.0, c.getMaxScore());
        assertTrue(c.getDescription().contains("ALTA DIFICULTAD"));
    }

    @Test
    void feedbackAddsDetail() {
        QuestionComponent c = new FeedbackQuestion(new BasicQuestion(TestFixtures.standard()), "ver capitulo 4");
        assertTrue(c.getExplanation().contains("ver capitulo 4"));
        assertTrue(c.getExplanation().contains("Justificacion."));
    }

    @Test
    void decoratorsCompose() {
        QuestionComponent c = new FeedbackQuestion(
            new HighDifficultyQuestion(new BasicQuestion(TestFixtures.standard()), 1.5), "detalle");
        assertEquals(1.5, c.getMaxScore());
        assertTrue(c.getExplanation().contains("detalle"));
    }
}
