/**
 *
 * @author Jose Maria del Aguila Lopez
 *
 */

package com.recomovie.service;

import com.recomovie.dto.PeliculaDTO;
import com.recomovie.dto.UsuarioDTO;
import com.recomovie.dto.VisualizacionDTO;

import java.text.ParseException;
import java.util.List;

public interface IUsuarioService {

    public void crearUsuario(UsuarioDTO u) throws ParseException;

    public UsuarioDTO obtenerUsuario(int idUsuario);

    public void borrarUsuario(int idUsuario);

    public boolean editarUsuario(UsuarioDTO usuario) throws ParseException;

    public List<VisualizacionDTO> obtenerPeliculasVistas(int idUsuario);

    public boolean comprobarLogin(UsuarioDTO usuario) throws ParseException;

    public boolean comprobarUsuario(String usuario) throws ParseException;

    public void eliminarPeliculaVista(int idVisualizacion);

    public List<PeliculaDTO> obtenerPeliculas(int idUsuario);

    public UsuarioDTO verUsuarioNombre(String nombreUsuario);

}
