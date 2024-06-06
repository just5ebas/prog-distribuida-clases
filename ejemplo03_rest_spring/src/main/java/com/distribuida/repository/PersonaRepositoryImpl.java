package com.distribuida.repository;

import com.distribuida.db.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class PersonaRepositoryImpl implements IPersonaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Persona findById(Integer id) {
        return entityManager.find(Persona.class, id);
    }

    @Override
    public List<Persona> findAll() {
        return entityManager.createQuery("SELECT p FROM Persona p ORDER BY p.id ASC", Persona.class).getResultList();
    }

    @Override
    public Persona create(Persona persona) {
        entityManager.persist(persona);
        return persona;
    }

    @Override
    public Persona update(Persona persona) {
        entityManager.merge(persona);
        return persona;
    }

    @Override
    public void delete(Integer id) {
        entityManager.remove(this.findById(id));
    }
}
