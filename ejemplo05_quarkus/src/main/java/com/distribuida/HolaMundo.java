package com.distribuida;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.time.LocalDateTime;

@Path("/hola")
public class HolaMundo {

    @GET
    public String hola() {
        return "Hola " + LocalDateTime.now();
    }

}