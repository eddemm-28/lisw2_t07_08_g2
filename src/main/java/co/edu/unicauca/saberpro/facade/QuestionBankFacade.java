package co.edu.unicauca.saberpro.facade;

import co.edu.unicauca.saberpro.adapter.QuestionProvider;
import co.edu.unicauca.saberpro.builder.IQuestionBuilder;
import co.edu.unicauca.saberpro.builder.QuestionBuilder;
import co.edu.unicauca.saberpro.builder.QuestionDirector;
import co.edu.unicauca.saberpro.domain.model.Question;
import co.edu.unicauca.saberpro.repository.InMemoryQuestionRepository;
import co.edu.unicauca.saberpro.validator.QuestionValidator;
import java.util.List;

/** Interfaz unica para la capa de presentacion. */
public class QuestionBankFacade {
    private final InMemoryQuestionRepository repository = new InMemoryQuestionRepository();
    private final QuestionValidator validator = new QuestionValidator();
    private final QuestionProvider externalProvider;

    public QuestionBankFacade(QuestionProvider externalProvider) {
        this.externalProvider = externalProvider;
    }

    public Question createQuestion(String context, String statement, List<String> options,
                                   int correctIndex, String justification,
                                   String competency, String topic, String subtopic, String difficulty) {
        IQuestionBuilder builder = new QuestionBuilder();
        Question q = new QuestionDirector(builder).construct(
                context, statement, options, correctIndex, justification,
                competency, topic, subtopic, difficulty);
        return validateAndSave(q);
    }

    /** Importa preguntas externas; retorna cuantas se guardaron. */
    public int importExternalQuestions() {
        int saved = 0;
        for (Question q : externalProvider.getQuestions()) {
            validateAndSave(q);
            saved++;
        }
        return saved;
    }

    public List<Question> listQuestions() {
        return repository.findAll();
    }

    public List<Question> findByTopic(String topic) {
        return repository.findByTopic(topic);
    }

    private Question validateAndSave(Question q) {
        List<String> errors = validator.validate(q);
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join("; ", errors));
        }
        return repository.save(q);
    }
}
