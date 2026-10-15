package co.edu.unicauca.saberpro.templatemethod;

import co.edu.unicauca.saberpro.domain.model.Question;

/**
 * Respuesta esperada: V o F. Se compara contra el texto de la opcion
 * correcta ("Verdadero" / "Falso").
 */
public class TrueFalseEvaluator extends QuestionEvaluator {

    @Override
    protected String receiveAnswer(String rawAnswer) {
        return super.receiveAnswer(rawAnswer).toUpperCase();
    }

    @Override
    protected boolean isValidFormat(String answer) {
        return answer.matches("[VF]");
    }

    @Override
    protected double calculateScore(Question question, String answer) {
        String expected = question.getCorrectOption().getText().substring(0, 1).toUpperCase();
        return expected.equals(answer) ? 1.0 : 0.0;
    }
}
