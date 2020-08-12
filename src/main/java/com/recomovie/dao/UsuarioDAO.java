package com.recomovie.dao;/*
 *
 * @author Jose Maria del Aguila Lopez
 *
 */

import com.recomovie.entity.Usuario;
import com.recomovie.entity.Visualizacion;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Repository
public class UsuarioDAO {

    @PersistenceContext
    EntityManager em;

    public Usuario getUsuario(int idUsuario){
        return em.find(Usuario.class,idUsuario);
    }

    public void creaUsuario(Usuario u){
        em.persist(u);
    }

    public Set<Visualizacion> peliculasVistas(int idUsuario){
        return em.find(Usuario.class,idUsuario).getPeliculasVistas();
    }



    public void borrarUsuario(int idUsuario){
        em.remove(em.merge(idUsuario));
    }



}
