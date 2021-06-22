/**
 *
 * @author Jose Maria del Aguila Lopez
 *
 */

package com.recomovie.serviceimp;

import com.recomovie.dao.PeliculaDAO;
import com.recomovie.dao.UsuarioDAO;
import com.recomovie.dto.PeliculaDTO;
import com.recomovie.dto.UsuarioDTO;
import com.recomovie.dto.VisualizacionDTO;
import com.recomovie.entity.Pelicula;
import com.recomovie.entity.Usuario;
import com.recomovie.entity.Visualizacion;
import com.recomovie.excepciones.Util;
import com.recomovie.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioDAO usuarioDao;

    @Autowired
    private PeliculaDAO peliculaDAO;

    @Autowired
    private PasswordEncoder encoder;

    public void crearUsuario(UsuarioDTO u) throws ParseException {
        Usuario usuarioNuevo = new Usuario();
        usuarioNuevo.seteMail(u.geteMail());
        usuarioNuevo.setNombreUsuario(u.getNombreUsuario());
        usuarioNuevo.setPassword(encoder.encode(u.getPassword()));
        usuarioNuevo.setFechaNacimineto(Util.parsearStringFecha(u.getFechaNacimiento()));
        usuarioDao.crea(usuarioNuevo);
    }

    public UsuarioDTO obtenerUsuario(int idUsuario){
        return usuarioDao.obtener(idUsuario).toDTO();
    }

    public void borrarUsuario(int idUsuario){
        usuarioDao.borra(idUsuario);
    }

    public boolean editarUsuario(UsuarioDTO usuario) throws ParseException {
        Usuario u = Usuario.fromDTO(usuario);
        if(usuarioDao.obtenerUsuarioNombre(u.getNombreUsuario()) != null){
            return false;
        }else{
            String password = "";
            password = encoder.encode(u.getPassword());
            u.setPassword(password);
            usuarioDao.actualiza(u);
            return true;
        }

    }

    public List<VisualizacionDTO> obtenerPeliculasVistas(String nombreUsuario){
        Usuario u = usuarioDao.obtenerUsuarioNombre(nombreUsuario);
        int idUsuario = u.getIdUsuario();
        Set<Visualizacion> peliculas = usuarioDao.peliculasVistas(idUsuario);
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
        return usuarioDao.obtenerUsuarioNombre(usuario) != null;
    }

    public void eliminarPeliculaVista(int idVisualizacion) {
        usuarioDao.eliminarPeliculaVista(idVisualizacion);
    }

    public List<PeliculaDTO> obtenerPeliculas(String nombreUsuario){
        Usuario u = usuarioDao.obtenerUsuarioNombre(nombreUsuario);
        int idUsuario = u.getIdUsuario();
        Set<Visualizacion> peliculasVisualizadas = usuarioDao.peliculasVistas(idUsuario);
        List<PeliculaDTO> peliculaDTOS = new ArrayList<>();
        for(Visualizacion v:peliculasVisualizadas){
            peliculaDTOS.add(peliculaDAO.obtener(v.getPelicula().getIdPelicula()).toDTO());
        }
        return peliculaDTOS;
    }

    public UsuarioDTO verUsuarioNombre(String nombreUsuario){
        return usuarioDao.obtenerUsuarioNombre(nombreUsuario).toDTO();
    }

    public VisualizacionDTO obtenerVisualizacion(String nombreUsuario, int idPelicula){
        Usuario u = usuarioDao.obtenerUsuarioNombre(nombreUsuario);
        Set<Visualizacion> listado = u.getPeliculasVistas();
        for ( Visualizacion v : listado){
            if(v.getPelicula().getIdPelicula() == idPelicula){
                return v.toDTO();
            }
        }
        return null;
    }
}
