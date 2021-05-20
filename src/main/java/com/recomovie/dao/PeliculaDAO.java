/**
 *
 * @author Jose Maria del Aguila Lopez
 *
 */
package com.recomovie.dao;


import com.recomovie.entity.Pelicula;
import com.recomovie.entity.Visualizacion;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.text.ParseException;
import java.util.List;


@Repository
public interface PeliculaDAO extends BaseDAO<Pelicula,Integer> {

    //@Transactional(propagation=Propagation.REQUIRED)


    public List<Visualizacion> obtenerVisualizaciones(int idPelicula);

    @Transactional
    public List<Visualizacion> getComentariosPelicula(int idPelicula);

    @Cacheable(value = "peliculasTitulo")
    public List<Pelicula> buscarTitulo (String titulo);

    @Cacheable(value = "peliculasGenero")
    public List<Pelicula> buscarGenero (String genero);

    @Cacheable (value = "peliculasYear")
    public List<Pelicula> buscarYear (int minYear, int maxYear);

    @Transactional
    public void crearVisualizacion(int idPelicula, int idUsuario);
    @Transactional
    public void anadirComentario(int idPelicula,int idUsuario, String fecha, String comentario) throws ParseException ;

    @Transactional
    public void anadirValoracion(int idPelicula,int idUsuario, float valoracion);

    public Visualizacion obtenerVisualizacion(int idPelicula,int idUsuario);

    public void editarValoracion(Visualizacion v);

    public void editarComentario(Visualizacion v);

    public int ultimaPelicula();

    public int minYear();

    public int maxYear();
}
