package com.recomovie.dao;/*
 *
 * @author Jose Maria del Aguila Lopez
 *
 */

import com.recomovie.entity.Usuario;
import com.recomovie.entity.Visualizacion;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Repository
public class UsuarioDAO {

    @PersistenceContext
    EntityManager em;


    public Usuario obtenerUsuario(int idUsuario){
        return em.find(Usuario.class,idUsuario);
    }

    @Transactional
    public void creaUsuario(Usuario u){
        em.persist(u);
    }

    @Cacheable(value = "peliculasVistas")
    public Set<Visualizacion> peliculasVistas(int idUsuario){
        return em.find(Usuario.class,idUsuario).getPeliculasVistas();
    }

    @Transactional
    public void borrarUsuario(int idUsuario){
        em.remove(em.merge(idUsuario));
    }

    @Transactional
    public void editarUsuario(Usuario u){
        Usuario usuario = em.find(Usuario.class,u.getIdUsuario());
        usuario.setNombreUsuario(u.getNombreUsuario());
        usuario.setFechaNacimineto(u.getFechaNacimineto());
        usuario.setPassword(u.getPassword());
        usuario.seteMail(u.geteMail());
        em.merge(usuario);

    }

}
