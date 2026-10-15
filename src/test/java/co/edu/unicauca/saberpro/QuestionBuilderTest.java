package co.edu.unicauca.saberpro;

import static org.junit.jupiter.api.Assertions.*;

import co.edu.unicauca.saberpro.builder.IQuestionBuilder;
import co.edu.unicauca.saberpro.builder.QuestionBuilder;
import co.edu.unicauca.saberpro.domain.model.Question;
import org.junit.jupiter.api.Test;

class QuestionBuilderTest {

    private IQuestionBuilder base() {
        return new QuestionBuilder()
                .setContext("Contexto")
                .setStatement("Enunciado?")
                .setJustification("Porque si")
                .setClassification("Comp", "Tema", "Sub", "MEDIA");
    }

    @Test
    void buildsValidQuestion() {
        Question q = base()
                .addOption("A", true).addOption("B", false)
                .addOption("C", false).addOption("D", false)
                .build();
        assertEquals(4, q.getOptions().size());
        assertEquals("A", q.getCorrectOption().getText());
    }

    @Test
    void failsWithThreeOptions() {
        IQuestionBuilder b = base().addOption("A", true).addOption("B", false).addOption("C", false);
        assertThrows(IllegalStateException.class, b::build);
    }

    @Test
    void failsWithoutCorrectAnswer() {
        IQuestionBuilder b = base()
                .addOption("A", false).addOption("B", false)
                .addOption("C", false).addOption("D", false);
        assertThrows(IllegalStateException.class, b::build);
    }

    @Test
    void failsWithTwoCorrectAnswers() {
        IQuestionBuilder b = base()
                .addOption("A", true).addOption("B", true)
                .addOption("C", false).addOption("D", false);
        assertThrows(IllegalStateException.class, b::build);
    }

    @Test
    void failsWithoutContext() {
        IQuestionBuilder b = new QuestionBuilder().setStatement("E")
                .addOption("A", true).addOption("B", false)
                .addOption("C", false).addOption("D", false);
        assertThrows(IllegalStateException.class, b::build);
    }
}
