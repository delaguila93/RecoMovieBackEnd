package com.recomovie.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recomovie.dao.PeliculaDAO;
import com.recomovie.dto.PeliculaDTO;
import com.recomovie.entity.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/*
 *
 * @author Jose Maria del Aguila Lopez
 *
 */
@RestController
@RequestMapping("pelicula")
public class PeliculaController {


    @Autowired(required = true)
    private PeliculaDAO peliculaDAO;

    @GetMapping("/listado-peliculas")
    public ResponseEntity<PeliculaDTO> getPeliculas() throws JsonProcessingException {
        ResponseEntity respuesta;
        HttpStatus estado = HttpStatus.OK;
        String message;
        List<Pelicula> listadoPeliculas = new ArrayList<>();
        List<PeliculaDTO> listadoDTO = new ArrayList<>();
        ObjectMapper obj = new ObjectMapper();
        try{
            listadoPeliculas = peliculaDAO.getPeliculas();
            for(Pelicula p : listadoPeliculas){
                listadoDTO.add(p.toDTO());
            }

        }catch (Exception e){
            estado = HttpStatus.INTERNAL_SERVER_ERROR;
            message = e.getMessage();
        }

        message = obj.writeValueAsString(listadoDTO);
        respuesta = new ResponseEntity(message,estado);
        return respuesta;
    }

    @GetMapping("/testMundo")
    public ResponseEntity getTest() {

        ResponseEntity respuesta;
        HttpStatus status = HttpStatus.OK;
        String message;
        try {
            message = "Funciona o eso espero";
        } catch (Exception e) {
            status = HttpStatus.BAD_GATEWAY;
            message = e.getMessage();
        }
        respuesta = new ResponseEntity(message, status);
        return respuesta;

    }



}
