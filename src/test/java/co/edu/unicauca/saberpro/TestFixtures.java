package co.edu.unicauca.saberpro;

import co.edu.unicauca.saberpro.builder.QuestionBuilder;
import co.edu.unicauca.saberpro.domain.model.Question;

final class TestFixtures {
    private TestFixtures() { }

    static Question question(String a, String b, String c, String d, int correct) {
        String[] t = {a, b, c, d};
        QuestionBuilder qb = new QuestionBuilder();
        qb.setContext("Contexto").setStatement("Enunciado?").setJustification("Justificacion.")
          .setClassification("Comp", "Tema", "Sub", "MEDIA");
        for (int i = 0; i < 4; i++) {
            qb.addOption(t[i], i == correct);
        }
        return qb.build();
    }

    static Question standard() {
        return question("A1", "B1", "C1", "D1", 1);   // correcta: B
    }
}
