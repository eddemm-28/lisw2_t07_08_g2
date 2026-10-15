package co.edu.unicauca.saberpro.adapter;

import co.edu.unicauca.saberpro.builder.IQuestionBuilder;
import co.edu.unicauca.saberpro.builder.QuestionBuilder;
import co.edu.unicauca.saberpro.builder.QuestionDirector;
import co.edu.unicauca.saberpro.domain.model.Question;
import java.util.ArrayList;
import java.util.List;

/** Adapta ExternalQuestionService a QuestionProvider. */
public class ExternalQuestionAdapter implements QuestionProvider {
    private static final int FIELDS = 10;

    private final ExternalQuestionService service;

    public ExternalQuestionAdapter(ExternalQuestionService service) {
        this.service = service;
    }

    @Override
    public List<Question> getQuestions() {
        IQuestionBuilder builder = new QuestionBuilder();
        QuestionDirector director = new QuestionDirector(builder);
        List<Question> result = new ArrayList<>();

        for (String raw : service.fetchRawQuestions()) {
            String[] f = raw.split("\\|", -1);
            if (f.length != FIELDS) {
                throw new IllegalArgumentException("Formato externo invalido: " + raw);
            }
            int correctIndex = Integer.parseInt(f[6].trim());
            result.add(director.construct(
                f[0], f[1], List.of(f[2], f[3], f[4], f[5]), correctIndex,
                f[7], f[8], f[9], "General", "MEDIA"));
        }
        return result;
    }
}
