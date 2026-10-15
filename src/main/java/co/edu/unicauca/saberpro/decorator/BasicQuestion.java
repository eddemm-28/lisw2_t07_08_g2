package co.edu.unicauca.saberpro.decorator;

import co.edu.unicauca.saberpro.domain.model.Question;

public class BasicQuestion implements QuestionComponent {
    private final Question question;

    public BasicQuestion(Question question) {
        this.question = question;
    }

    @Override public String getDescription() { return question.getStatement(); }
    @Override public double getMaxScore() { return 1.0; }
    @Override public String getExplanation() { return question.getJustification(); }
}
