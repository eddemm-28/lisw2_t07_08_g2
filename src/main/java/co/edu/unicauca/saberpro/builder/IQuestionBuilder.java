package co.edu.unicauca.saberpro.builder;

import co.edu.unicauca.saberpro.domain.model.Question;

public interface IQuestionBuilder {
    IQuestionBuilder setContext(String text);
    IQuestionBuilder setStatement(String statement);
    IQuestionBuilder addOption(String text, boolean correct);
    IQuestionBuilder setJustification(String justification);
    IQuestionBuilder setBibliography(String bibliography);
    IQuestionBuilder setClassification(String competency, String topic, String subtopic, String difficulty);
    IQuestionBuilder reset();

    /** Valida y entrega la pregunta. Lanza IllegalStateException si es invalida. */
    Question build();
}
