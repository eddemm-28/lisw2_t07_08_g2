package co.edu.unicauca.saberpro.state;

/** Estado final: no permite ninguna operacion. */
public class PublishedState implements IQuestionState {
    @Override public String getName() { return "Publicada"; }
}
