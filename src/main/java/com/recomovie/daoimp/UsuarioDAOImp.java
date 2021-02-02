/**
 *
 * @author Jose Maria del Aguila Lopez
 *
 */

package com.recomovie.daoimp;

import com.recomovie.dao.UsuarioDAO;
import com.recomovie.dto.VisualizacionDTO;
import com.recomovie.entity.Pelicula;
import com.recomovie.entity.Usuario;
import com.recomovie.entity.Visualizacion;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    @Cacheable(value = "listadoUsuarios")
    public List<Usuario> obtenerTodos(){
        return em.createQuery("SELECT u FROM Usuario u",Usuario.class).getResultList();
    }

    /* -- Metodos propios del DAO de usuarios -- */

    @Cacheable(value = "peliculasVistas")
    public List<Visualizacion> peliculasVistas(int idUsuario){
        return em.find(Usuario.class,idUsuario).getPeliculasVistas();
    }

    public Usuario comprobarLogin(Usuario u){
        return em.createQuery("SELECT u FROM Usuario u WHERE u.nombreUsuario = ?1 AND u.password = ?2",Usuario.class)
                .setParameter(1,u.getNombreUsuario())
                .setParameter(2,u.getPassword())
                .getSingleResult();
    }

    public Usuario obtenerUsuarioNombre(String u){
        Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.nombreUsuario = ?1",Usuario.class)
                .setParameter(1,u);
        try{
            Usuario usuario =(Usuario)q.getSingleResult();
            return usuario;
        }catch (NoResultException e){
            return null;
        }
    }

    public void eliminarPeliculaVista( int idVisualizacion) {
        Visualizacion v = em.find(Visualizacion.class,idVisualizacion);
        Usuario u = em.find(Usuario.class,v.getUsuario().getIdUsuario());
        Pelicula p = em.find(Pelicula.class,v.getPelicula().getIdPelicula());
        u.quitarPeliculaVista(v);
        p.quitarVisualizacion(v);
        em.remove(em.merge(idVisualizacion));
    }

    public List<Pelicula> obtenerPeliculasVisualizadas(int idUsuario){
        return em.createQuery("SELECT p FROM Pelicula p, Visualizacion v WHERE p.idPelicula = v.idPelicula AND v.idUsuario = ?1",Pelicula.class)
                .setParameter(1,idUsuario)
                .getResultList();
    }
}
