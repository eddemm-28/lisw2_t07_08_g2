package co.edu.unicauca.saberpro.state;

import co.edu.unicauca.saberpro.domain.model.Question;

/** Bloquea la edicion del autor; solo permite iniciar la revision. */
public class PendingReviewState implements IQuestionState {
    @Override public String getName() { return "Pendiente de Revision"; }

    @Override
    public void startReview(Question q) {
        q.setState(new ReviewState());
    }
}
