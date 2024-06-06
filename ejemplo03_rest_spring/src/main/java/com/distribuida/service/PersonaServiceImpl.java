package com.distribuida.service;

import com.distribuida.db.Persona;
import com.distribuida.repository.IPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServiceImpl implements IPersonaService {

    @Autowired
    private IPersonaRepository personaRepository;

    @Override
    public Persona findById(Integer id) {
        return this.personaRepository.findById(id);
    }

    @Override
    public List<Persona> findAll() {
        return this.personaRepository.findAll();
    }

    @Override
    public Persona create(Persona persona) {
        return this.personaRepository.create(persona);
    }

    @Override
    public Persona update(Persona persona) {
        return this.personaRepository.update(persona);
    }

    @Override
    public void delete(Integer id) {
        this.personaRepository.delete(id);
    }
}
