/**
 *
 * @author Jose Maria del Aguila Lopez
 *
 */

package com.recomovie.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.recomovie.dto.UsuarioDTO;
import com.recomovie.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService servicioUsuario;

    /**
     *
     * @param usuarioNuevo El usuario nuevo que se ha registrado
     * @return Mensaje de confirmacion de 
     */
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

    /**
     *
     * @param idUsuario
     * @return
     */
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

    /**
     *
     * @param idUsuario
     * @return
     */
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

    /**
     *
     * @param idUsuario
     * @param usuarioModificado
     * @return
     */
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

    /**
     *
     * @param idUsuario
     * @return
     */
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
