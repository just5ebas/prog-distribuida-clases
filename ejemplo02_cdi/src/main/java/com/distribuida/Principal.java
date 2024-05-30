package com.distribuida;

import com.distribuida.db.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class Principal {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu-distribuida");
        EntityManager em = emf.createEntityManager();

        var p = new Persona();
        p.setId(1);
        p.setNombre("Sebas");
        p.setDireccion("Calderon");
        p.setEdad(22);

//        try{
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
//        } catch (Exception e){
//            em.getTransaction().rollback();
//        }

        //listar personas
        System.out.println("Listando personas...");
        TypedQuery<Persona> qry = em.createQuery("select p from Persona p order by id asc", Persona.class);

        qry.getResultStream()
                .map(Persona::getNombre)
                .forEach(System.out::println);

//        qry.getResultList().stream().map(Persona::getNombre)
//                .forEach(System.out::println);

        // Cerrar EntityManager y EntityManagerFactory
        em.close();
        emf.close();
    }
}
