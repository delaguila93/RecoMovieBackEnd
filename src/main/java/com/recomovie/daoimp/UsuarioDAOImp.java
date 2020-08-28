/**
 *
 * @author Jose Maria del Aguila Lopez
 *
 */

package com.recomovie.daoimp;

import com.recomovie.dao.UsuarioDAO;
import com.recomovie.entity.Usuario;
import com.recomovie.entity.Visualizacion;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UsuarioDAOImp implements UsuarioDAO {


    @PersistenceContext
    EntityManager em;

    /* --  Metodos heredados de la clase baseDAO  -- */

    public Usuario obtener(Integer idUsuario){
        return em.find(Usuario.class,idUsuario);
    }

    @Transactional
    public void crea(Usuario u){
        em.persist(u);
    }

    @Transactional
    public void borra(Integer idUsuario){
        em.remove(em.merge(idUsuario));
    }

    @Transactional
    public void actualiza(Usuario u){
        Usuario usuario = em.find(Usuario.class,u.getIdUsuario());
        usuario.setNombreUsuario(u.getNombreUsuario());
        usuario.setFechaNacimineto(u.getFechaNacimineto());
        usuario.setPassword(u.getPassword());
        usuario.seteMail(u.geteMail());
        em.merge(usuario);

    }

    public List<Usuario> obtenerTodos(){
        return em.createQuery("SELECT u FROM Usuario u",Usuario.class).getResultList();
    }

    /* -- Metodos propios del DAO de usuarios -- */

    @Cacheable(value = "peliculasVistas")
    public List<Visualizacion> peliculasVistas(int idUsuario){
        return em.find(Usuario.class,idUsuario).getPeliculasVistas();
    }
}
