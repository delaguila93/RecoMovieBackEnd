/**
 *
 * @author Jose Maria del Aguila Lopez
 *
 */

package com.recomovie.dao;

import com.recomovie.entity.Usuario;
import com.recomovie.entity.Visualizacion;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UsuarioDAO extends BaseDAO<Usuario,Integer> {

/* -- Metodos propios del DAO de usuarios -- */

    @Cacheable(value = "peliculasVistas")
    public List<Visualizacion> peliculasVistas(int idUsuario);

    public Usuario comprobarLogin(Usuario u);

    public Usuario comprobarUsuario(String u);

    public void eliminarPeliculaVista( int idVisualizacion);
}