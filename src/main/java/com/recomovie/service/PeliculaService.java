package com.recomovie.service;/*
 *
 * @author Jose Maria del Aguila Lopez
 *
 */

import com.recomovie.dao.PeliculaDAO;
import com.recomovie.dto.PeliculaDTO;
import com.recomovie.dto.VisualizacionDTO;
import com.recomovie.entity.Pelicula;
import com.recomovie.entity.Visualizacion;
import jdk.internal.net.http.common.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PeliculaService {

    @Autowired
    private PeliculaDAO peliculaDao;


    public PeliculaDTO obtenerPelicula(int idPelicula){
        return peliculaDao.getPelicula(idPelicula).toDTO();
    }

    public List<Pair<String, Date>> obtenerComentariosPelicula(int idPelicula){
        return peliculaDao.getComentariosPelicula(idPelicula);
    }

    public List<PeliculaDTO> listadoPeliculas(){
        List<PeliculaDTO> listadoPeliculas = new ArrayList<>();
        List<Pelicula> lista = peliculaDao.getListadoPeliculas();
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

    public List<PeliculaDTO> buscarGenero(String genero){
        List<PeliculaDTO> listadoPeliculas = new ArrayList<>();
        List<Pelicula> lista = peliculaDao.buscarGenero(genero);
        for(Pelicula p: lista){
            listadoPeliculas.add(p.toDTO());
        }
        return listadoPeliculas;
    }

    public List<PeliculaDTO> buscarYear(int year){
        List<PeliculaDTO> listadoPeliculas = new ArrayList<>();
        List<Pelicula> lista = peliculaDao.buscarYear(year);
        for(Pelicula p: lista){
            listadoPeliculas.add(p.toDTO());
        }
        return listadoPeliculas;
    }

    public void anadirValoracion(VisualizacionDTO v){
        peliculaDao.anadirVisualizacion(v);
    }

    public void anadirComentario(String fecha, String comentario,int idPelicula,int idUsuario){

    }


}
