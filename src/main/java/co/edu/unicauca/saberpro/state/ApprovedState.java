package co.edu.unicauca.saberpro.state;

import co.edu.unicauca.saberpro.domain.model.Question;

public class ApprovedState implements IQuestionState {
    @Override public String getName() { return "Aprobada"; }

    @Override
    public void publish(Question q) {
        q.setState(new PublishedState());
    }
}
