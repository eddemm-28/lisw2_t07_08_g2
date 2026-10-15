package co.edu.unicauca.saberpro.domain.model;

import co.edu.unicauca.saberpro.state.DraftState;
import co.edu.unicauca.saberpro.state.IQuestionState;
import java.util.Collections;
import java.util.List;

public class Question {
    private String id;
    private final Context context;
    private String statement;
    private IQuestionState state = new DraftState();
    private final List<Option> options;
    private final String justification;
    private final String bibliography;
    private final String competency;
    private final String topic;
    private final String subtopic;
    private final String difficulty;

    public Question(Context context, String statement, List<Option> options,
                    String justification, String bibliography,
                    String competency, String topic, String subtopic, String difficulty) {
        this.context = context;
        this.statement = statement;
        this.options = Collections.unmodifiableList(options);
        this.justification = justification;
        this.bibliography = bibliography;
        this.competency = competency;
        this.topic = topic;
        this.subtopic = subtopic;
        this.difficulty = difficulty;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Context getContext() { return context; }
    public String getStatement() { return statement; }
    public List<Option> getOptions() { return options; }
    public String getJustification() { return justification; }
    public String getBibliography() { return bibliography; }
    public String getCompetency() { return competency; }
    public String getTopic() { return topic; }
    public String getSubtopic() { return subtopic; }
    public String getDifficulty() { return difficulty; }

    /** Uso interno de los estados; para editar desde fuera usar editStatement(). */
    public void setStatement(String statement) { this.statement = statement; }
    public IQuestionState getState() { return state; }
    public void setState(IQuestionState state) { this.state = state; }

    // Operaciones del ciclo de vida: se delegan al estado actual
    public void editStatement(String newStatement) { state.editStatement(this, newStatement); }
    public void submit() { state.submit(this); }
    public void startReview() { state.startReview(this); }
    public void approve() { state.approve(this); }
    public void requestChanges() { state.requestChanges(this); }
    public void publish() { state.publish(this); }

    public Option getCorrectOption() {
        return options.stream().filter(Option::isCorrect).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return "[" + id + "] " + statement + " (" + topic + " / " + difficulty + ")";
    }
}
