package co.edu.unicauca.saberpro.domain.model;

public class Option {
    private final String text;
    private final boolean correct;

    public Option(String text, boolean correct) {
        this.text = text;
        this.correct = correct;
    }

    public String getText() {
        return text;
    }

    public boolean isCorrect() {
        return correct;
    }
}
