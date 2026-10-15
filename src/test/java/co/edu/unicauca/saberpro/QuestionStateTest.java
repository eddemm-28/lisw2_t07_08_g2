package co.edu.unicauca.saberpro;

import static org.junit.jupiter.api.Assertions.*;

import co.edu.unicauca.saberpro.domain.model.Question;
import co.edu.unicauca.saberpro.state.*;
import org.junit.jupiter.api.Test;

class QuestionStateTest {

    @Test
    void startsInDraft() {
        assertInstanceOf(DraftState.class, TestFixtures.standard().getState());
    }

    @Test
    void fullHappyPath() {
        Question q = TestFixtures.standard();
        q.submit();
        assertInstanceOf(PendingReviewState.class, q.getState());
        q.startReview();
        assertInstanceOf(ReviewState.class, q.getState());
        q.approve();
        assertInstanceOf(ApprovedState.class, q.getState());
        q.publish();
        assertInstanceOf(PublishedState.class, q.getState());
    }

    @Test
    void draftAllowsEditing() {
        Question q = TestFixtures.standard();
        q.editStatement("Nuevo enunciado");
        assertEquals("Nuevo enunciado", q.getStatement());
    }

    @Test
    void pendingReviewBlocksEditing() {
        Question q = TestFixtures.standard();
        q.submit();
        assertThrows(IllegalStateException.class, () -> q.editStatement("X"));
    }

    @Test
    void requestChangesReturnsToDraft() {
        Question q = TestFixtures.standard();
        q.submit();
        q.startReview();
        q.requestChanges();
        assertInstanceOf(DraftState.class, q.getState());
    }

    @Test
    void invalidTransitionsAreRejected() {
        Question q = TestFixtures.standard();
        assertThrows(IllegalStateException.class, q::approve);
        assertThrows(IllegalStateException.class, q::publish);
        q.submit();
        assertThrows(IllegalStateException.class, q::submit);
    }

    @Test
    void publishedIsFinal() {
        Question q = TestFixtures.standard();
        q.submit(); q.startReview(); q.approve(); q.publish();
        assertThrows(IllegalStateException.class, q::requestChanges);
        assertThrows(IllegalStateException.class, () -> q.editStatement("X"));
    }
}
