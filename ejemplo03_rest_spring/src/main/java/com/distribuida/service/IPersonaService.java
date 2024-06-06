package com.distribuida.service;

import com.distribuida.db.Persona;

import java.util.List;

public interface IPersonaService {

    Persona findById(Integer id);

    List<Persona> findAll();

    Persona create(Persona persona);

    Persona update(Persona persona);

    void delete(Integer id);

}
