package co.edu.unicauca.saberpro.state;

import co.edu.unicauca.saberpro.domain.model.Question;

/** Por defecto toda operacion es invalida; cada estado sobrescribe solo las que permite. */
public interface IQuestionState {
    String getName();

    default void editStatement(Question q, String newStatement) { deny("editar"); }
    default void submit(Question q)         { deny("enviar a revision"); }
    default void startReview(Question q)    { deny("iniciar revision"); }
    default void approve(Question q)        { deny("aprobar"); }
    default void requestChanges(Question q) { deny("solicitar correcciones"); }
    default void publish(Question q)        { deny("publicar"); }

    private void deny(String action) {
        throw new IllegalStateException(
            "No se puede " + action + " una pregunta en estado " + getName());
    }
}
