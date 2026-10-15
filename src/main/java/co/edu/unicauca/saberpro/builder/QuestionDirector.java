package co.edu.unicauca.saberpro.builder;

import co.edu.unicauca.saberpro.domain.model.Question;
import java.util.List;

/** Define el orden de construccion; el builder define el como. */
public class QuestionDirector {
    private final IQuestionBuilder builder;

    public QuestionDirector(IQuestionBuilder builder) {
        this.builder = builder;
    }

    public Question construct(String context, String statement, List<String> options,
                              int correctIndex, String justification,
                              String competency, String topic, String subtopic, String difficulty) {
        builder.reset()
               .setContext(context)
               .setStatement(statement);
        for (int i = 0; i < options.size(); i++) {
            builder.addOption(options.get(i), i == correctIndex);
        }
        return builder.setJustification(justification)
                      .setClassification(competency, topic, subtopic, difficulty)
                      .build();
    }
}
