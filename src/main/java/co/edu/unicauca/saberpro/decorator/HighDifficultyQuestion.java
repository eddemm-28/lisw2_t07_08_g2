package co.edu.unicauca.saberpro.decorator;

public class HighDifficultyQuestion extends QuestionDecorator {
    private final double multiplier;

    public HighDifficultyQuestion(QuestionComponent wrapped, double multiplier) {
        super(wrapped);
        this.multiplier = multiplier;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " [ALTA DIFICULTAD x" + multiplier + "]";
    }

    @Override
    public double getMaxScore() {
        return super.getMaxScore() * multiplier;
    }
}
