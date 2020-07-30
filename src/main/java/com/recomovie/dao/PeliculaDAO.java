package com.recomovie.dao;

import com.recomovie.entity.Pelicula;
import com.recomovie.entity.Visualizacion;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Repository
public class PeliculaDAO {

    @PersistenceContext
    EntityManager em;

    //@Transactional(propagation=Propagation.REQUIRED)

    @Transactional
    public Pelicula getPelicula(int idPelicula){
        return em.find(Pelicula.class,idPelicula);

    }

    @Transactional
    public List<Pelicula> getPeliculas(){
        List<Pelicula> lista = em.createQuery("SELECT p FROM Pelicula p",Pelicula.class).getResultList();
        return lista;
    }

    @Transactional
    public Set<Visualizacion> obtenerVisualizaciones(int idPelicula){

        return em.find(Pelicula.class,idPelicula).getVisualizaciones();

    }




}
