package com.recomovie.controller;/*
 *
 * @author Jose Maria del Aguila Lopez
 *
 */

import com.fasterxml.jackson.databind.ObjectMapper;

import com.recomovie.dto.UsuarioDTO;
import com.recomovie.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService servicioUsuario;

    @GetMapping("/crear")
    public ResponseEntity crearUsuario(@RequestParam ("usuario") UsuarioDTO usuarioNuevo){
        HttpStatus estado = HttpStatus.OK;
        String mensaje = " ";
        try{
            servicioUsuario.crearUsuario(usuarioNuevo);
            mensaje = "Usuario creado correctamente";
        }catch(Exception e){
            mensaje = e.getMessage();
            estado = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity(mensaje,estado);
    }

    @GetMapping("/obtener/{idUsuario}")
    public ResponseEntity<UsuarioDTO> obtenerUsuario(@PathVariable("idUsuario") Integer idUsuario){
        HttpStatus status = HttpStatus.OK;
        String mensaje = " ";
        ObjectMapper obj = new ObjectMapper();
        try{
            mensaje = obj.writeValueAsString(servicioUsuario.obtenerUsuario(idUsuario));
        }catch (Exception e ){
            mensaje = e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity (mensaje,status);
    }

    @GetMapping("/borrar/{idUsuario}")
    public ResponseEntity borrarUsuario(@PathVariable("idUsuario") Integer idUsuario){
        HttpStatus status = HttpStatus.OK;
        ResponseEntity respuesta;
        String mensaje = "";

        try{
            servicioUsuario.borrarUsuario(idUsuario);
            mensaje = "Borrado correcto";
        }catch (Exception e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            mensaje = e.getMessage();
        }

        return new ResponseEntity(mensaje,status);
    }

    @GetMapping("/editar/{idUsuario}")
    public ResponseEntity editarUsuario(@PathVariable ("idUsuario") Integer idUsuario,@RequestParam ("usuarioModificado") UsuarioDTO usuarioModificado){
        String mensaje = "";
        HttpStatus estado = HttpStatus.OK;
        try{
            servicioUsuario.editarUsuario(usuarioModificado);
            mensaje = "Usuario editado correctamente";
        }catch (Exception e){
            mensaje = e.getMessage();
            estado = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity(mensaje,estado);
    }

    @GetMapping("/peliculasVistas/{idUsuario}")
    public ResponseEntity obtenerPeliculasVistas(@PathVariable ("idUsuario") Integer idUsuario){
        String mensaje = "";
        HttpStatus estado = HttpStatus.OK;
        ObjectMapper obj = new ObjectMapper();
        try{
            mensaje = obj.writeValueAsString(servicioUsuario.obtenerPeliculasVistas(idUsuario));

        }catch(Exception e){
            mensaje = e.getMessage();
            estado = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(mensaje,estado);

    }
}
