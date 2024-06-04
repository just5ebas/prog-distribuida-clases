package com.distribuida.servicios;

import com.distribuida.db.Persona;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

@ApplicationScoped
public class ServicioPersonaImpl implements ServicioPersona {

    @Inject
    EntityManager em;

    @Override
    public Persona findById(Integer id) {
        return em.find(Persona.class, id);
    }

    @Override
    public List<Persona> findAll() {
        return em.createQuery("select p from Persona p order by id asc", Persona.class)
                .getResultList();
    }

    @Override
    public void create(Persona persona) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(persona);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    @Override
    public void update(Persona persona) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(persona);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    @Override
    public void delete(Integer id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.remove(this.findById(id));
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }
}
