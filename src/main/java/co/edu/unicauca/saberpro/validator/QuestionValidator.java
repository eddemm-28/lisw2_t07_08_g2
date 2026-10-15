package co.edu.unicauca.saberpro.validator;

import co.edu.unicauca.saberpro.domain.model.Option;
import co.edu.unicauca.saberpro.domain.model.Question;
import java.util.ArrayList;
import java.util.List;

/** Reglas adicionales de calidad (RF-12). La estructura la valida el Builder. */
public class QuestionValidator {
    private static final List<String> FORBIDDEN =
            List.of("todas las anteriores", "ninguna de las anteriores");

    public List<String> validate(Question q) {
        List<String> errors = new ArrayList<>();
        for (Option o : q.getOptions()) {
            String text = o.getText().toLowerCase();
            for (String bad : FORBIDDEN) {
                if (text.contains(bad)) {
                    errors.add("Opcion no permitida: \"" + o.getText() + "\"");
                }
            }
        }
        return errors;
    }
}
