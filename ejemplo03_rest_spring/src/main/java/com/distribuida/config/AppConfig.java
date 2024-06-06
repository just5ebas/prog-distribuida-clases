package com.distribuida.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.distribuida")
public class AppConfig {

    private EntityManagerFactory emf;

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        System.out.println("***** Jpaconfig::entityManagerFactory");
        return Persistence.createEntityManagerFactory("pu-distribuida");
    }

    @Bean
    public EntityManager entityManager(EntityManagerFactory emf) {
        System.out.println("***** Jpaconfig::entityManager");
        return emf.createEntityManager();
    }

}
