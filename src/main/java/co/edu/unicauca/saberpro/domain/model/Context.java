package co.edu.unicauca.saberpro.domain.model;

public class Context {
    private final String text;

    public Context(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }
}
