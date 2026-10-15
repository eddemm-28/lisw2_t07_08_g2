package co.edu.unicauca.saberpro.state;

import co.edu.unicauca.saberpro.domain.model.Question;

public class DraftState implements IQuestionState {
    @Override public String getName() { return "Borrador"; }

    @Override
    public void editStatement(Question q, String newStatement) {
        q.setStatement(newStatement);
    }

    @Override
    public void submit(Question q) {
        q.setState(new PendingReviewState());
    }
}
