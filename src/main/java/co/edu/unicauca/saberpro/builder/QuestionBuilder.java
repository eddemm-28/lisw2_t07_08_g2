package co.edu.unicauca.saberpro.builder;

import co.edu.unicauca.saberpro.domain.model.Context;
import co.edu.unicauca.saberpro.domain.model.Option;
import co.edu.unicauca.saberpro.domain.model.Question;
import java.util.ArrayList;
import java.util.List;

public class QuestionBuilder implements IQuestionBuilder {
    private static final int REQUIRED_OPTIONS = 4;

    private Context context;
    private String statement;
    private List<Option> options = new ArrayList<>();
    private String justification;
    private String bibliography;
    private String competency;
    private String topic;
    private String subtopic;
    private String difficulty;

    @Override
    public IQuestionBuilder setContext(String text) {
        this.context = new Context(text);
        return this;
    }

    @Override
    public IQuestionBuilder setStatement(String statement) {
        this.statement = statement;
        return this;
    }

    @Override
    public IQuestionBuilder addOption(String text, boolean correct) {
        this.options.add(new Option(text, correct));
        return this;
    }

    @Override
    public IQuestionBuilder setJustification(String justification) {
        this.justification = justification;
        return this;
    }

    @Override
    public IQuestionBuilder setBibliography(String bibliography) {
        this.bibliography = bibliography;
        return this;
    }

    @Override
    public IQuestionBuilder setClassification(String competency, String topic,
                                              String subtopic, String difficulty) {
        this.competency = competency;
        this.topic = topic;
        this.subtopic = subtopic;
        this.difficulty = difficulty;
        return this;
    }

    @Override
    public IQuestionBuilder reset() {
        context = null;
        statement = null;
        options = new ArrayList<>();
        justification = null;
        bibliography = null;
        competency = null;
        topic = null;
        subtopic = null;
        difficulty = null;
        return this;
    }

    @Override
    public Question build() {
        if (context == null || context.getText() == null || context.getText().isBlank()) {
            throw new IllegalStateException("La pregunta debe tener contexto.");
        }
        if (statement == null || statement.isBlank()) {
            throw new IllegalStateException("La pregunta debe tener enunciado.");
        }
        if (options.size() != REQUIRED_OPTIONS) {
            throw new IllegalStateException(
                "Deben existir exactamente " + REQUIRED_OPTIONS + " opciones, hay " + options.size() + ".");
        }
        long correct = options.stream().filter(Option::isCorrect).count();
        if (correct != 1) {
            throw new IllegalStateException(
                "Debe existir exactamente 1 respuesta correcta, hay " + correct + ".");
        }
        Question q = new Question(context, statement, new ArrayList<>(options),
                justification, bibliography, competency, topic, subtopic, difficulty);
        reset();
        return q;
    }
}
