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
     * Funcion que recibe un usuario nuevo para registrarlo en la BBDD
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
     * Funcion que develve el usuario dado
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
     * Funcion que borra el usuario dado
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
     * Funcion que recibe la llamada con el usuario y los datos del usuario dado modificado
     * @param idUsuario El usuario a modificar
     * @param usuarioModificado los datos del usuario modificados
     * @return La confirmacion de que el usuario ha sido modificado
     */
    @GetMapping("/editar/{idUsuario}")
    public ResponseEntity editarUsuario(@PathVariable ("idUsuario") Integer idUsuario,@RequestBody UsuarioDTO usuarioModificado){
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
     * Funcion que devuelve las peliculas que ha visto el usuario
     * @param idUsuario
     * @return Las peliculas vistas por el usuario dado
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

    /**
     * Funcion que comprueba si se puede logear el usuario
     * @param usuario
     * @return La respuesta indicando si existe el usuario pasado
     */
    @GetMapping("/comprobarLogin")
    public ResponseEntity comprobarLogin(@RequestBody UsuarioDTO usuario){
        String mensaje = "";
        HttpStatus estado = HttpStatus.OK;
        try{
            if(servicioUsuario.comprobarLogin(usuario)){
                mensaje = "Existe el usuario";
            }else{
                mensaje = "No existe el usuario";
            }
        }catch (Exception e){
            mensaje = e.getMessage();
            estado = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(mensaje,estado);
    }

    /**
     * Funcion que comprueba si existe ese nombre de usuario
     * @param usuario
     * @return La respuesta indicando si existe el usuario pasado
     */
    @GetMapping("/comprobarUsuario")
    public ResponseEntity comprobarUsuario(@RequestParam ("usuario") String usuario){
        String mensaje = "";
        HttpStatus estado = HttpStatus.OK;
        try{
            if(servicioUsuario.comprobarUsuario(usuario)){
                mensaje = "Existe ese nombre de usuario";
            }else{
                mensaje = "No existe ese nombre de usuario";
            }
        }catch (Exception e){
            mensaje = e.getMessage();
            estado = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(mensaje,estado);
    }

    /**
     *
     * @param idVisualizacion
     * @return
     */
    @GetMapping("/borrarVisual/{idVisualizacion}")
    public ResponseEntity eliminarVisualizacion(@PathVariable ("idVisualizacion") Integer idVisualizacion){
        String mensaje = "";
        HttpStatus estado = HttpStatus.OK;
        try{
            servicioUsuario.eliminarPeliculaVista(idVisualizacion);
            mensaje = "Pelicula vista eliminada de la lista de peliculas vistas";
        }catch (Exception e){
            mensaje = e.getMessage();
            estado = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity(mensaje,estado);
    }
}