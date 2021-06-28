/**
 *
 * @author Jose Maria del Aguila Lopez
 *
 */

package com.recomovie.daoimp;

import com.recomovie.dao.PeliculaDAO;
import com.recomovie.entity.Pelicula;
import com.recomovie.entity.Usuario;
import com.recomovie.entity.Visualizacion;
import com.recomovie.excepciones.Util;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.ParseException;
import java.util.List;

@Repository
public class PeliculaDAOImp implements PeliculaDAO {

    @PersistenceContext
    EntityManager em;

    //@Transactional(propagation=Propagation.REQUIRED)

    /* -- Metodos heredados de baseDAO -- */

    public Pelicula obtener(Integer idPelicula){
        return em.find(Pelicula.class,idPelicula);
    }

    @Transactional
    public void actualiza(Pelicula p){
        Pelicula peli = em.find(Pelicula.class,p.getIdPelicula());
        peli.setDirector(p.getDirector());
        peli.setGenero(p.getGenero());
        peli.setSinopsis(p.getSinopsis());
        peli.setTitulo(p.getTitulo());
        peli.setDuracion(p.getDuracion());
        peli.setYear(p.getYear());
        peli.setUrlImage(p.getUrlImage());
        em.merge(peli);
    }

    public void crea(Pelicula p){
        em.persist(p);
    }

    public void borra (Integer idPelicula){
        em.remove(em.merge(idPelicula));
    }

    public List<Pelicula> obtenerTodos(){
        return em.createQuery("SELECT p FROM Pelicula p",Pelicula.class).getResultList();
    }

    /* -- Metodos propios del DAO de peliculas -- */

    public List<Visualizacion> obtenerVisualizaciones(int idPelicula){
        return em.createQuery("SELECT v FROM Visualizacion v WHERE v.idPelicula = ?1",Visualizacion.class)
                .setParameter(1,idPelicula)
                .getResultList();
    }

    @Transactional
    public List<Visualizacion> getComentariosPelicula(int idPelicula){
        return em.find(Pelicula.class,idPelicula).listadoComentarios();
    }

    @Cacheable(value = "peliculasTitulo")
    public List<Pelicula> buscarTitulo (String titulo){
        return em.createQuery("SELECT p FROM Pelicula p WHERE p.titulo LIKE ?1",Pelicula.class)
                .setParameter(1,"%"+titulo+"%")
                .getResultList();
    }

    @Cacheable(value = "peliculasGenero")
    public List<Pelicula> buscarGenero (String genero){
        return em.createQuery("SELECT p FROM Pelicula p WHERE p.genero LIKE ?1",Pelicula.class)
                .setParameter(1,"%"+genero+"%")
                .getResultList();
    }

    @Cacheable (value = "peliculasYear")
    public List<Pelicula> buscarYear (int minYear, int maxYear){
        return em.createQuery("SELECT p FROM Pelicula p WHERE p.year BETWEEN ?1 AND ?2",Pelicula.class)
                .setParameter(1,minYear)
                .setParameter(2,maxYear)
                .getResultList();
    }

    @Transactional
    public void crearVisualizacion(int idPelicula, int idUsuario){
        Visualizacion visualizacion = new Visualizacion();
        Pelicula p = em.find(Pelicula.class,idPelicula);
        Usuario u = em.find(Usuario.class,idUsuario);
        visualizacion.setPelicula(p);
        visualizacion.setUsuario(u);
        p.anadirVisualizacion(visualizacion);
        u.anadirPeliculaVista(visualizacion);
        em.merge(p);
        em.merge(u);
        em.persist(visualizacion);
    }

    @Transactional
    public void anadirComentario(int idVisualizacion, String fecha, String comentario) {
        Visualizacion v = em.find(Visualizacion.class,idVisualizacion);
        v.setFechaComentario(Util.parsearStringFecha(fecha));
        v.setComentario(comentario);
        em.merge(v);
    }

    @Transactional
    public void anadirValoracion(int idVisualizacion, float valoracion){
        Visualizacion v = em.find(Visualizacion.class,idVisualizacion);
        v.setValoracion(valoracion);
        em.merge(v);
    }

    public Visualizacion obtenerVisualizacion(int idPelicula,int idUsuario){
        return em.createQuery("SELECT v FROM Visualizacion v WHERE v.pelicula= ?1 AND v.usuario = ?2",Visualizacion.class)
                .setParameter(1,idPelicula)
                .setParameter(2,idUsuario)
                .getSingleResult();
    }

    public void editarValoracion(Visualizacion v){
        Visualizacion visualizacion = em.createQuery("SELECT v FROM Visualizacion v WHERE v.idVisualizacion = ?1",Visualizacion.class)
                .setParameter(1,v.getIdVisualizacion())
                .getSingleResult();
        visualizacion.setValoracion(v.getValoracion());
        em.merge(visualizacion);
    }

    public void editarComentario(Visualizacion v){
        Visualizacion visualizacion = em.find(Visualizacion.class,v.getIdVisualizacion());
        visualizacion.setFechaComentario(v.getFechaComentario());
        visualizacion.setComentario(v.getComentario());
        em.merge(visualizacion);
    }

    public int ultimaPelicula(){
        Query q = em.createQuery("SELECT MAX(p.idPelicula) FROM Pelicula p ",Integer.class);
        return (Integer) q.getSingleResult();
    }

    public int minYear(){
        return em.createQuery("SELECT MIN(p.year) FROM Pelicula p",Integer.class).getSingleResult();
    }

    public int maxYear(){
        return em.createQuery("SELECT MAX(p.year) FROM Pelicula p",Integer.class).getSingleResult();
    }
}
