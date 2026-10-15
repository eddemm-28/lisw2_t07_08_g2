package co.edu.unicauca.saberpro.templatemethod;

import co.edu.unicauca.saberpro.domain.model.Question;
import java.util.List;

/** Respuesta abierta: puntaje parcial segun palabras clave presentes. */
public class CaseStudyEvaluator extends QuestionEvaluator {
    private final List<String> keywords;

    public CaseStudyEvaluator(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    protected double calculateScore(Question question, String answer) {
        String text = answer.toLowerCase();
        long hits = keywords.stream().filter(k -> text.contains(k.toLowerCase())).count();
        return (double) hits / keywords.size();
    }

    @Override
    protected String generateFeedback(Question question, double score) {
        return String.format("Cobertura de conceptos clave: %.0f%%. %s",
                score * 100, question.getJustification());
    }
}
