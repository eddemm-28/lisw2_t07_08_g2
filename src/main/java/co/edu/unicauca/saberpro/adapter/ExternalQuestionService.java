package co.edu.unicauca.saberpro.adapter;

import java.util.List;

/**
 * Servicio externo simulado (Adaptee). Retorna cadenas crudas con formato:
 * contexto|enunciado|opcion1|opcion2|opcion3|opcion4|indiceCorrecta(0-3)|justificacion|competencia|tema
 */
public class ExternalQuestionService {
    public List<String> fetchRawQuestions() {
        return List.of(
            "Un sistema web debe soportar 500 usuarios concurrentes.|"
                + "Que tactica favorece la escalabilidad horizontal?|"
                + "Balanceador de carga|Aumentar el tamano de fuente|Eliminar el cache|Usar una sola instancia|"
                + "0|El balanceador distribuye la carga entre varias instancias.|"
                + "Diseno de software|Arquitectura",
            "Un equipo necesita separar logica de negocio, interfaz y persistencia.|"
                + "Que estilo arquitectonico aplica?|"
                + "Arquitectura en capas|Programacion lineal|Monolito sin modulos|Scripts aislados|"
                + "0|La arquitectura en capas separa responsabilidades.|"
                + "Diseno de software|Arquitectura"
        );
    }
}
