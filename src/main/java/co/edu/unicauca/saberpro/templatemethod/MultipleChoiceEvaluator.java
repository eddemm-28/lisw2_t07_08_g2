package co.edu.unicauca.saberpro.templatemethod;

import co.edu.unicauca.saberpro.domain.model.Question;

/** Respuesta esperada: A, B, C o D. */
public class MultipleChoiceEvaluator extends QuestionEvaluator {

    @Override
    protected String receiveAnswer(String rawAnswer) {
        return super.receiveAnswer(rawAnswer).toUpperCase();
    }

    @Override
    protected boolean isValidFormat(String answer) {
        return answer.matches("[A-D]");
    }

    @Override
    protected double calculateScore(Question question, String answer) {
        int index = answer.charAt(0) - 'A';
        return question.getOptions().get(index).isCorrect() ? 1.0 : 0.0;
    }
}
