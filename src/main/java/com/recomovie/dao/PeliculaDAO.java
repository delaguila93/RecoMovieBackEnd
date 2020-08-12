package com.recomovie.dao;

import com.recomovie.dto.VisualizacionDTO;
import com.recomovie.entity.Pelicula;
import com.recomovie.entity.Usuario;
import com.recomovie.entity.Visualizacion;
import jdk.internal.net.http.common.Pair;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public class PeliculaDAO {

    @PersistenceContext
    EntityManager em;

    //@Transactional(propagation=Propagation.REQUIRED)


    public Pelicula getPelicula(int idPelicula){
        return em.find(Pelicula.class,idPelicula);

    }

    public List<Pelicula> getListadoPeliculas(){
        return em.createQuery("SELECT p FROM Pelicula p",Pelicula.class).getResultList();
    }

    public List<Visualizacion> obtenerVisualizaciones(int idPelicula){
        return em.createQuery("SELECT v FROM Visualizacion v WHERE v.idPelicula = ?1",Visualizacion.class)
                .setParameter(1,idPelicula)
                .getResultList();
    }

    @Transactional
    public List<Pair<String, Date>> getComentariosPelicula(int idPelicula){
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
    public List<Pelicula> buscarYear (int year){
        return em.createQuery("SELECT p FROM pelicula WHERE p.year = ?1",Pelicula.class)
                .setParameter(1,year)
                .getResultList();
    }

    @Transactional
    public void anadirVisualizacion(VisualizacionDTO v){
        Visualizacion visualizacion = new Visualizacion();
        Pelicula p = em.find(Pelicula.class,v.getIdPelicula());
        Usuario u = em.find(Usuario.class,v.getIdUsuario());
        p.anadirVisualizacion(visualizacion);
        u.anadirPeliculaVista(visualizacion);
        em.merge(p);
        em.merge(u);
        em.persist(visualizacion);
    }

    @Transactional
    public void anadirComentario(int idPelicula,int idUsuario, String fecha, String comentario) throws ParseException {
        Visualizacion v = em.createQuery("SELECT v FROM Visualizacion v WHERE v.idPelicula= ?1 AND v.idUsuario = ?2",Visualizacion.class)
                .setParameter(1,idPelicula)
                .setParameter(2,idUsuario)
                .getSingleResult();

        v.setFechaComentario(DateFormat.getDateInstance().parse(fecha));
        v.setComentario(comentario);
        em.merge(v);
    }

}
