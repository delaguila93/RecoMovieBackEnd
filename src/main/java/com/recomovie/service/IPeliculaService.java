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
import java.util.Date;
import java.util.List;

public interface IPeliculaService {

    public PeliculaDTO obtenerPelicula(int idPelicula);

    public List<VisualizacionDTO> obtenerComentariosPelicula(int idPelicula);

    public List<PeliculaDTO> listadoPeliculas();

    public List<PeliculaDTO> listadoPeliculasAleatorias();

    public List<PeliculaDTO> buscarTitulo(String titulo);

    public List<PeliculaDTO> buscarGenero(String genero);

    public List<PeliculaDTO> buscarYear(String year);

    public void anadirValoracion(VisualizacionDTO v);

    public void anadirComentario(VisualizacionDTO v) throws ParseException;

    public void editarComentario(VisualizacionDTO v) throws ParseException;

    public void editarValoracion(VisualizacionDTO v) throws ParseException;

    public List<String> listaUsuariosPelicula(int idPelicula);

    public String minMaxYear();
}
