package co.edu.unicauca.saberpro.repository;

import co.edu.unicauca.saberpro.domain.model.Question;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryQuestionRepository {
    private final List<Question> questions = new ArrayList<>();
    private int sequence = 1;

    public Question save(Question q) {
        q.setId("Q" + sequence++);
        questions.add(q);
        return q;
    }

    public List<Question> findAll() {
        return new ArrayList<>(questions);
    }

    public List<Question> findByTopic(String topic) {
        return questions.stream()
                .filter(q -> topic.equalsIgnoreCase(q.getTopic()))
                .collect(Collectors.toList());
    }
}
