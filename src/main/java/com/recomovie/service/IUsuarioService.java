/**
 *
 * @author Jose Maria del Aguila Lopez
 *
 */

package com.recomovie.service;

import com.recomovie.dto.UsuarioDTO;
import com.recomovie.dto.VisualizacionDTO;

import java.text.ParseException;
import java.util.List;

public interface IUsuarioService {

    public void crearUsuario(UsuarioDTO u) throws ParseException;

    public UsuarioDTO obtenerUsuario(int idUsuario);

    public void borrarUsuario(int idUsuario);

    public void editarUsuario(UsuarioDTO usuario) throws ParseException;

    public List<VisualizacionDTO> obtenerPeliculasVistas(int idUsuario);
}
