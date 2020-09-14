/**
 *
 * @author Jose Maria del Aguila Lopez
 *
 */

package com.recomovie.serviceimp;

import com.recomovie.dao.UsuarioDAO;
import com.recomovie.dto.UsuarioDTO;
import com.recomovie.dto.VisualizacionDTO;
import com.recomovie.entity.Usuario;
import com.recomovie.entity.Visualizacion;
import com.recomovie.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioDAO usuarioDao;

    public void crearUsuario(UsuarioDTO u) throws ParseException {
        Usuario usuarioNuevo = new Usuario();
        usuarioNuevo.seteMail(u.geteMail());
        usuarioNuevo.setNombreUsuario(u.getNombreUsuario());
        usuarioNuevo.setPassword(u.getPassword());
        usuarioNuevo.setFechaNacimineto(DateFormat.getDateInstance().parse(u.getFechaNacimiento()));

        usuarioDao.crea(usuarioNuevo);
    }

    public UsuarioDTO obtenerUsuario(int idUsuario){
        return usuarioDao.obtener(idUsuario).toDTO();
    }

    public void borrarUsuario(int idUsuario){
        usuarioDao.borra(idUsuario);
    }

    public void editarUsuario(UsuarioDTO usuario) throws ParseException {
        Usuario u = Usuario.fromDTO(usuario);
        usuarioDao.actualiza(u);
    }


    public List<VisualizacionDTO> obtenerPeliculasVistas(int idUsuario){
        List<Visualizacion> peliculas = usuarioDao.peliculasVistas(idUsuario);
        List<VisualizacionDTO> peliculasVistas = new ArrayList<>();
        for(Visualizacion v : peliculas){
            peliculasVistas.add(v.toDTO());
        }
        return peliculasVistas;
    }

    public boolean comprobarLogin(UsuarioDTO usuario) throws ParseException {
        return usuarioDao.comprobarLogin(Usuario.fromDTO(usuario)) != null;
    }

    public boolean comprobarUsuario(String usuario) throws ParseException {
        return usuarioDao.comprobarUsuario(usuario) != null;
    }

    public void eliminarPeliculaVista(int idVisualizacion) {
        usuarioDao.eliminarPeliculaVista(idVisualizacion);
    }

}
