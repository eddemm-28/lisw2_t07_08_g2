package co.edu.unicauca.saberpro.adapter;

import co.edu.unicauca.saberpro.domain.model.Question;
import java.util.List;

/** Interfaz esperada por el Banco de Preguntas (Target). */
public interface QuestionProvider {
    List<Question> getQuestions();
}
