package co.edu.unicauca.saberpro.state;

import co.edu.unicauca.saberpro.domain.model.Question;

public class ReviewState implements IQuestionState {
    @Override public String getName() { return "En Revision"; }

    @Override
    public void approve(Question q) {
        q.setState(new ApprovedState());
    }

    @Override
    public void requestChanges(Question q) {
        q.setState(new DraftState());
    }
}
