package com.recomovie.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PeliculaDAO {

    @PersistenceContext
    EntityManager em;

    @Transactional(propagation=Propagation.REQUIRED)
    public void createFilm(){

    }

    @Transactional
    public String getFilm(){
        return "em.find(Film.class,123456";

    }



}
