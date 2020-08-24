package com.recomovie.service;/*
 *
 * @author Jose Maria del Aguila Lopez
 *
 */


import com.recomovie.dao.UsuarioDAO;
import com.recomovie.dto.PeliculaDTO;
import com.recomovie.dto.UsuarioDTO;
import com.recomovie.dto.VisualizacionDTO;
import com.recomovie.entity.Usuario;
import com.recomovie.entity.Visualizacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Set;
import java.util.TreeSet;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioDAO usuarioDao;

    public void crearUsuario(UsuarioDTO u) throws ParseException {
        Usuario usuarioNuevo = new Usuario();
        usuarioNuevo.seteMail(u.geteMail());
        usuarioNuevo.setNombreUsuario(u.getNombreUsuario());
        usuarioNuevo.setPassword(u.getPassword());
        usuarioNuevo.setFechaNacimineto(DateFormat.getDateInstance().parse(u.getFechaNacimiento()));

        usuarioDao.creaUsuario(usuarioNuevo);
    }

    public UsuarioDTO obtenerUsuario(int idUsuario){
        return usuarioDao.obtenerUsuario(idUsuario).toDTO();
    }

    public void borrarUsuario(int idUsuario){
        usuarioDao.borrarUsuario(idUsuario);
    }

    public Set<VisualizacionDTO> obtenerPeliculasVistas(int idUsuario){
        Set<Visualizacion> peliculas = usuarioDao.peliculasVistas(idUsuario);
        Set<VisualizacionDTO> peliculasVistas = new TreeSet<>();
        for(Visualizacion v : peliculas){
            peliculasVistas.add(v.toDTO());
        }
        return peliculasVistas;
    }

    public void editarUsuario(UsuarioDTO usuario) throws ParseException {
        Usuario u = Usuario.fromDTO(usuario);
        usuarioDao.editarUsuario(u);
    }


}
