package co.edu.unicauca.saberpro.templatemethod;

import co.edu.unicauca.saberpro.domain.model.Question;

public abstract class QuestionEvaluator {

    /** Metodo plantilla: el orden del algoritmo no se puede alterar. */
    public final EvaluationResult evaluate(Question question, String rawAnswer) {
        String answer = receiveAnswer(rawAnswer);          // 1. recibir
        if (!isValidFormat(answer)) {                      // 2. validar formato
            throw new IllegalArgumentException("Formato de respuesta invalido: " + rawAnswer);
        }
        double score = calculateScore(question, answer);   // 3. puntaje (especifico)
        String feedback = generateFeedback(question, score); // 4. retroalimentacion
        return new EvaluationResult(score, feedback);
    }

    // Pasos con implementacion por defecto (hooks)
    protected String receiveAnswer(String rawAnswer) {
        return rawAnswer == null ? "" : rawAnswer.trim();
    }

    protected boolean isValidFormat(String answer) {
        return !answer.isEmpty();
    }

    protected String generateFeedback(Question question, double score) {
        return score > 0
            ? "Correcto. " + question.getJustification()
            : "Incorrecto. " + question.getJustification();
    }

    // Paso que cada subclase debe definir
    protected abstract double calculateScore(Question question, String answer);
}
