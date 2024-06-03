package com.distribuida.servicios;

import com.distribuida.db.Persona;

import java.util.List;

public interface ServicioPersona {

    Persona findById(Integer id);

    List<Persona> findAll();

    void create(Persona persona);

    void update(Persona persona);

    void delete(Integer id);
}
