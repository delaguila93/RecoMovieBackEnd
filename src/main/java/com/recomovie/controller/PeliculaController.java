package com.recomovie.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recomovie.dao.PeliculaDAO;
import com.recomovie.dto.PeliculaDTO;
import com.recomovie.dto.VisualizacionDTO;
import com.recomovie.entity.Pelicula;
import com.recomovie.service.PeliculaService;
import jdk.internal.net.http.common.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 *
 * @author Jose Maria del Aguila Lopez
 *
 */
@RestController
@RequestMapping("pelicula")
public class PeliculaController {


    @Autowired
    private PeliculaService servicioPelicula;

    @GetMapping("/obtener-pelicula/{idPelicula}")
    public ResponseEntity<PeliculaDTO> obtenerPelicula(@PathVariable ("idPelicula") Integer idPelicula){
        HttpStatus estado = HttpStatus.OK;
        String mensaje = "";
        ObjectMapper obj = new ObjectMapper();
        try{
            mensaje = obj.writeValueAsString(servicioPelicula.obtenerPelicula(idPelicula));

        }catch (Exception e){
            mensaje = e.getMessage();
            estado = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return  new ResponseEntity(mensaje,estado);

    }

    @GetMapping("/buscar-titulo/{titulo}")
    public ResponseEntity<PeliculaDTO> buscarTitulo(@PathVariable ("titulo") String titulo){
        ResponseEntity respuesta;
        HttpStatus estado = HttpStatus.OK;
        String message;
        ObjectMapper obj = new ObjectMapper();
        try{

            message = obj.writeValueAsString(servicioPelicula.buscarTitulo(titulo));
        }catch (Exception e){
            message = e.getMessage();
            estado = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(message,estado);
    }

    @GetMapping("/buscar-year/{year}")
    public ResponseEntity<PeliculaDTO> buscarYear(@PathVariable ("year") Integer year){
        ResponseEntity respuesta;
        HttpStatus estado = HttpStatus.OK;
        String mensaje = "";
        ObjectMapper obj = new ObjectMapper();
        try{
            mensaje = obj.writeValueAsString(servicioPelicula.buscarYear(year));
        }catch (Exception e){
            mensaje = e.getMessage();
            estado = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(mensaje,estado);
    }

    @GetMapping("/listado-peliculas")
    public ResponseEntity<PeliculaDTO> getPeliculas() throws JsonProcessingException {
        ResponseEntity respuesta;
        HttpStatus estado = HttpStatus.OK;
        String message;
        ObjectMapper obj = new ObjectMapper();
        try{

            message = obj.writeValueAsString( servicioPelicula.listadoPeliculas());
        }catch (Exception e){
            estado = HttpStatus.INTERNAL_SERVER_ERROR;
            message = e.getMessage();
        }
        respuesta = new ResponseEntity(message,estado);
        return respuesta;
    }


    @GetMapping("/obtener-comentarios/{idPelicula}")
    public ResponseEntity<Pair<String, Date>> getComentariosPelicula(@PathVariable ("idPelicula") Integer idPelicula){
        ResponseEntity respuesta;
        HttpStatus status = HttpStatus.OK;
        String mensaje = "";
        ObjectMapper obj = new ObjectMapper();
        try{
            mensaje = obj.writeValueAsString(servicioPelicula.obtenerComentariosPelicula(idPelicula));
        }catch (Exception e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            mensaje = e.getMessage();
        }
        respuesta = new ResponseEntity(mensaje,status);
        return respuesta;
    }

    @GetMapping("/anadir-valoracion/{idPelicula}")
    public ResponseEntity<String> anadirValoracion(@PathVariable ("idPelicula") Integer idPelicula, @RequestParam ("visualizacion") VisualizacionDTO v){
        HttpStatus estado = HttpStatus.OK;
        String mensaje = "";
        try{
            servicioPelicula.anadirValoracion(v);
            mensaje = "Pelicula valorada correctamente";
        }catch (Exception e){
            mensaje = e.getMessage();
            estado = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(mensaje,estado);
    }

    @GetMapping("/anadir-comentario/{idPelicula}")
    public ResponseEntity<String> anadirComentario(@PathVariable ("idPelicula") Integer idPelicula,@RequestParam ("visualizacion") VisualizacionDTO v){
        HttpStatus estado = HttpStatus.OK;
        String mensaje = "";
        try{
            servicioPelicula.anadirComentario(v);
            mensaje = "Comentario añadido correctamente";
        }catch (Exception e){
            mensaje = e.getMessage();
            estado = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(mensaje,estado);
    }

}
