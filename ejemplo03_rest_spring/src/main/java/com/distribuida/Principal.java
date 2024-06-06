package com.distribuida;

import com.distribuida.config.AppConfig;
import com.distribuida.db.Persona;
import com.distribuida.service.GreetingService;
import com.distribuida.service.IPersonaService;
import com.distribuida.service.PersonaServiceImpl;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import spark.Request;
import spark.Response;

import java.util.List;

import static spark.Spark.*;

@Component
public class Principal {

    @Autowired
    private static IPersonaService personaService;

    @Autowired
    private GreetingService greetingService;

    static List<Persona> listarPersonas(Request rq, Response res) {
        res.type("application/json");
        return personaService.findAll();
    }

    static Persona buscar(Request rq, Response res) {
        res.type("application/json");

        String id = rq.params(":id");

        var persona = personaService.findById(Integer.valueOf(id));

        if (persona == null)
            halt(404, "Persona no encontrada");

        return persona;
    }

    static Persona crearPersona(Request rq, Response res) {
        res.type("application/json");

        Gson gson = new Gson();
        Persona persona = gson.fromJson(rq.body(), Persona.class);

        personaService.create(persona);

        return persona;
    }

    static Persona actualizarPersona(Request rq, Response res) {
        res.type("application/json");

        String id = rq.params(":id");
        Gson gson = new Gson();
        Persona persona = gson.fromJson(rq.body(), Persona.class);
        persona.setId(Integer.valueOf(id));

        personaService.update(persona);

        return persona;
    }

    static String eliminarPersona(Request rq, Response res) {
        String id = rq.params(":id");

        personaService.delete(Integer.valueOf(id));

        return "";
    }

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Principal principal = context.getBean(Principal.class);

        port(8080);

        Gson gson = new Gson();

        get("/hello", (req, res) -> "Hello World!!");
        get("/greet", (req, res) -> {
            res.type("application/json");
            return new Gson().toJson(principal.greetingService.getGreeting());
        });

        get("/personas", Principal::listarPersonas, gson::toJson);
        get("/personas/:id", Principal::buscar, gson::toJson);
        post("/personas", Principal::crearPersona, gson::toJson);
        put("/personas/:id", Principal::actualizarPersona, gson::toJson);
        delete("/personas/:id", Principal::eliminarPersona);

    }

}
