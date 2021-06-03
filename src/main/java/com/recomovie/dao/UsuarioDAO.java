/**
 *
 * @author Jose Maria del Aguila Lopez
 *
 */

package com.recomovie.dao;

import com.recomovie.entity.Pelicula;
import com.recomovie.entity.Usuario;
import com.recomovie.entity.Visualizacion;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface UsuarioDAO extends BaseDAO<Usuario,Integer> {

/* -- Metodos propios del DAO de usuarios -- */

    @Cacheable(value = "peliculasVistas")
    public Set<Visualizacion> peliculasVistas(int idUsuario);

    public Usuario comprobarLogin(Usuario u);

    public Usuario obtenerUsuarioNombre(String u);

    public void eliminarPeliculaVista( int idVisualizacion);

}
