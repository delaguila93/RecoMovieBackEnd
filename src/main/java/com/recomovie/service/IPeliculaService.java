/**
 *
 * @author Jose Maria del Aguila Lopez
 *
 */

package com.recomovie.service;

import com.recomovie.dto.PeliculaDTO;
import com.recomovie.dto.VisualizacionDTO;
import jdk.internal.net.http.common.Pair;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface IPeliculaService {

    public PeliculaDTO obtenerPelicula(int idPelicula);

    public List<VisualizacionDTO> obtenerComentariosPelicula(int idPelicula);

    public List<PeliculaDTO> listadoPeliculas();

    public List<PeliculaDTO> buscarTitulo(String titulo);

    public List<PeliculaDTO> buscarGenero(String genero);

    public List<PeliculaDTO> buscarYear(int year);

    public void anadirValoracion(VisualizacionDTO v);

    public void anadirComentario(VisualizacionDTO v) throws ParseException;
}
