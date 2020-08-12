package com.recomovie.controller;/*
 *
 * @author Jose Maria del Aguila Lopez
 *
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recomovie.dao.UsuarioDAO;
import com.recomovie.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    UsuarioDAO usuarioDao;

    @GetMapping("/crear")
    public ResponseEntity crearUsuario(@RequestParam ("usuario") UsuarioDTO usuario){
        HttpStatus status = HttpStatus.OK;
        ResponseEntity respuesta  = null;


        return respuesta;
    }

    @GetMapping("/obtener/{idUsuario}")
    public ResponseEntity<UsuarioDTO> obtenerUsuario(@PathVariable("idUsuario") Integer idUsuario){
        HttpStatus status = HttpStatus.OK;
        ResponseEntity respuesta;
        String mensaje = " ";
        ObjectMapper obj = new ObjectMapper();
        try{
            mensaje = obj.writeValueAsString(usuarioDao.getUsuario(idUsuario));
        }catch (Exception e ){
            mensaje = e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;

        }
        respuesta = new ResponseEntity (mensaje,status);
        return respuesta;
    }

    @GetMapping("/borrar/{idUsuario}")
    public ResponseEntity borrarUsuario(@PathVariable("idUsuario") Integer idUsuario){
        
        return null;
    }


}
