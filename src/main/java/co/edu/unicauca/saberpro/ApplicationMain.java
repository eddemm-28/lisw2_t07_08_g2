package co.edu.unicauca.saberpro;

import co.edu.unicauca.saberpro.adapter.ExternalQuestionAdapter;
import co.edu.unicauca.saberpro.adapter.ExternalQuestionService;
import co.edu.unicauca.saberpro.decorator.*;
import co.edu.unicauca.saberpro.domain.model.Question;
import co.edu.unicauca.saberpro.facade.QuestionBankFacade;
import co.edu.unicauca.saberpro.templatemethod.*;
import java.util.List;

public class ApplicationMain {
    public static void main(String[] args) {
        QuestionBankFacade bank = new QuestionBankFacade(
                new ExternalQuestionAdapter(new ExternalQuestionService()));

        // PARTE 1: Builder + Facade + Adapter
        Question q = bank.createQuestion(
                "Una API recibe miles de peticiones por minuto.",
                "Que patron desacopla al cliente de varios subsistemas?",
                List.of("Facade", "Singleton", "Prototype", "Flyweight"),
                0, "Facade ofrece una interfaz unificada.",
                "Diseno de software", "Patrones", "Estructurales", "MEDIA");
        System.out.println("Importadas: " + bank.importExternalQuestions());
        bank.listQuestions().forEach(System.out::println);

        // PARTE 2: State
        System.out.println("Estado: " + q.getState().getName());
        q.submit();
        q.startReview();
        q.approve();
        System.out.println("Estado: " + q.getState().getName());
        try {
            q.editStatement("No deberia permitirse");
        } catch (IllegalStateException e) {
            System.out.println("Rechazado: " + e.getMessage());
        }
        q.publish();

        // PARTE 2: Template Method
        EvaluationResult r = new MultipleChoiceEvaluator().evaluate(q, "A");
        System.out.println("Puntaje: " + r.score() + " - " + r.feedback());

        // PARTE 2: Decorator
        QuestionComponent c = new FeedbackQuestion(
                new HighDifficultyQuestion(new BasicQuestion(q), 2.0),
                "Facade reduce el acoplamiento cliente-subsistemas.");
        System.out.println(c.getDescription() + " | max=" + c.getMaxScore());
        System.out.println(c.getExplanation());
    }
}
