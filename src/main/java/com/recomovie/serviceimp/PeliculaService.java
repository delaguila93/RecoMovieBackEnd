/**
 *
 * @author Jose Maria del Aguila Lopez
 *
 */

package com.recomovie.serviceimp;

import com.recomovie.dao.PeliculaDAO;
import com.recomovie.dto.PeliculaDTO;
import com.recomovie.dto.VisualizacionDTO;
import com.recomovie.entity.Pelicula;
import com.recomovie.entity.Visualizacion;
import com.recomovie.service.IPeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PeliculaService implements IPeliculaService {

    @Autowired
    private PeliculaDAO peliculaDao;


    public PeliculaDTO obtenerPelicula(int idPelicula){
        return peliculaDao.obtener(idPelicula).toDTO();
    }


    public List<PeliculaDTO> listadoPeliculas(){
        List<PeliculaDTO> listadoPeliculas = new ArrayList<>();
        List<Pelicula> lista = peliculaDao.obtenerTodos();
        for(Pelicula p: lista){
            listadoPeliculas.add(p.toDTO());
        }
        return listadoPeliculas;
    }

    public List<PeliculaDTO> buscarTitulo(String titulo){
        List<PeliculaDTO> listadoPeliculas = new ArrayList<>();
        List<Pelicula> lista = peliculaDao.buscarTitulo(titulo);
        for(Pelicula p: lista){
            listadoPeliculas.add(p.toDTO());
        }
        return listadoPeliculas;
    }

    /* -- A partir de aqui son los metodos concretos del servicio de peliculas -- */


    /**
     *
     * @param idPelicula
     * @return
     */

    public List<VisualizacionDTO> obtenerComentariosPelicula(int idPelicula){
        List<Visualizacion> listado = new ArrayList<>();
        List<VisualizacionDTO> listadoPeliculas = new ArrayList<>();
        try{
            listado = peliculaDao.getComentariosPelicula(idPelicula);
            for(Visualizacion v : listado){
                listadoPeliculas.add(v.toDTO());
            }
        }catch (Exception e){
            return  null;
        }
        return listadoPeliculas;
    }

    /**
     * Funcion que busca las obtiene las peliculas segun el genero o generos dado
     * @param genero
     * @return Listado de peliculas por genero
     */
    public List<PeliculaDTO> buscarGenero(String genero){
        List<PeliculaDTO> listadoPeliculas = new ArrayList<>();
        List<Pelicula> pelis = new ArrayList<>();
        String[] generos = genero.split(",");
        for(String g : generos){
            List<Pelicula> lista = peliculaDao.buscarGenero(genero);
            pelis.addAll(lista.stream().distinct().collect(Collectors.toList()));
        }

        for(Pelicula p: pelis){
            listadoPeliculas.add(p.toDTO());
        }
        return listadoPeliculas;
    }

    /**
     *
     * @param year
     * @return
     */
    public List<PeliculaDTO> buscarYear(int year){
        List<PeliculaDTO> listadoPeliculas = new ArrayList<>();
        List<Pelicula> lista = peliculaDao.buscarYear(year);
        for(Pelicula p: lista){
            listadoPeliculas.add(p.toDTO());
        }
        return listadoPeliculas;
    }

    public void anadirValoracion(VisualizacionDTO v){
        if(peliculaDao.obtenerVisualizacion(v.getIdPelicula(),v.getIdUsuario()) == null){
            peliculaDao.crearVisualizacion(v.getIdPelicula(),v.getIdUsuario());
        }
        peliculaDao.anadirValoracion(v.getIdPelicula(),v.getIdUsuario(),v.getValoracion());

    }


    public void anadirComentario(VisualizacionDTO v) throws ParseException {
        if(peliculaDao.obtenerVisualizacion(v.getIdPelicula(),v.getIdUsuario()) == null){
            peliculaDao.crearVisualizacion(v.getIdPelicula(),v.getIdUsuario());
        }
        peliculaDao.anadirComentario(v.getIdPelicula(), v.getIdUsuario(),v.getFechaComentario(),v.getComentario());
    }

    public void editarComentario(VisualizacionDTO v) throws ParseException {
        peliculaDao.editarComentario(Visualizacion.fromDTO(v));
    }

    public void editarValoracion(VisualizacionDTO v) throws ParseException {
        peliculaDao.editarValoracion(Visualizacion.fromDTO(v));
    }


}