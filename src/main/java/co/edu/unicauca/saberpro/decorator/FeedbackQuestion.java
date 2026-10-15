package co.edu.unicauca.saberpro.decorator;

public class FeedbackQuestion extends QuestionDecorator {
    private final String technicalDetail;

    public FeedbackQuestion(QuestionComponent wrapped, String technicalDetail) {
        super(wrapped);
        this.technicalDetail = technicalDetail;
    }

    @Override
    public String getExplanation() {
        return super.getExplanation() + " Detalle tecnico: " + technicalDetail;
    }
}
