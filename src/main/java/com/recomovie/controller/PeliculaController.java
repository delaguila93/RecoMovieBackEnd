package com.recomovie.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recomovie.dao.PeliculaDAO;
import com.recomovie.entity.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/testRead")
    public ResponseEntity<Pelicula> getFilm() {
        ResponseEntity<Pelicula> respuesta;
        HttpStatus status = HttpStatus.OK;
        String message = " ";
        ObjectMapper Obj = new ObjectMapper();

        try {
            message = Obj.writeValueAsString(peliculaDAO.getFilm());

        } catch (Exception e) {
            message = e.getMessage();
            status = HttpStatus.BAD_GATEWAY;
        }
        respuesta = new ResponseEntity(message, status);
        return respuesta;
    }

}
