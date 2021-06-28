/**
 *
 * @author Jose Maria del Aguila Lopez
 *
 */

package com.recomovie.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recomovie.dto.PeliculaDTO;
import com.recomovie.dto.UsuarioDTO;
import com.recomovie.dto.VisualizacionDTO;
import com.recomovie.service.IPeliculaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;


@RestController
@RequestMapping("pelicula")
public class PeliculaController {


    @Autowired
    private IPeliculaService servicioPelicula;

    /**
     * Funcion que recibe un identificador de una pelicula para obtener sus datos
     *
     * @param idPelicula El identificador de la pelicula
     * @return Los datos la pelicula proporcionada
     */
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

    /**
     * Funcion que recibe un titulo de una pelicula para buscar las peliculas que contengan ese titulo
     *
     * @param titulo El titulo de la pelicula que se desea buscar
     * @return Las peliculas que encajen con el titulo que se ha dado
     */
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

    /**
     * Funcion que recibe un rango de años para buscar las peliculas de ese rango de años
     *
     * @param year El año de las peliculas para
     * @return La lista de las peliculas cuyo año coincidan con el año dado
     */
    @GetMapping("/buscar-year/{year}")
    public ResponseEntity<PeliculaDTO> buscarYear(@PathVariable ("year") String year){
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

    /**
     * Funcion que buscas las peliculas segun los generos dado
     *
     * @param generos Los generos para buscar las peliculas
     * @return Las peliculas que tengan los generos dados
     */
    @GetMapping("/buscar-genero")
    public ResponseEntity<PeliculaDTO> buscarGenero(@RequestParam("generos") String generos){
        String mensaje = "";
        HttpStatus estado = HttpStatus.OK;
        ObjectMapper obj = new ObjectMapper();
        try{
            mensaje = obj.writeValueAsString(servicioPelicula.buscarGenero(generos));
        }catch (Exception e){
            mensaje = e.getMessage();
            estado = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(mensaje,estado);
    }

    /**
     * Funcion que devuelve todas las peliculas
     *
     * @return El listado completo de peliculas
     */
    @GetMapping("/listado-peliculas")
    public ResponseEntity<PeliculaDTO> obtenerListadoPeliculas()  {
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

    /**
     * Funcion que dado un identificador de pelicula devuelve las visualizaciones de la peliculas
     *
     * @param idPelicula Identificador de la pelicula de la cual queremos obtener los comentarios
     * @return El listado de comentarios de la pelicula proporcionada
     */
    @GetMapping("/obtener-comentarios/{idPelicula}")
    public ResponseEntity<VisualizacionDTO> getComentariosPelicula(@PathVariable ("idPelicula") Integer idPelicula){
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

    /**
     *
     * @param v La Valoracion de la pelicula a añadir contenido en el dto de Visualizacion
     * @return Mensaje de confirmacion de que la valoracion ha sido añadida
     */
    @PostMapping("/anadir-valoracion")
    public ResponseEntity<String> anadirValoracion( @RequestBody VisualizacionDTO v){
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

    /**
     * Funcion que recibe un comentario y su fecha para añadir
     *
     * @param v La fecha y el contenido del comentario a añadir contenido en el dto de Visualizacion
     * @return Mensaje de conformacion de que el comentario ha sido añadido correctamente
     */
    @PostMapping("/anadir-comentario")
    public ResponseEntity<String> anadirComentario(@RequestBody VisualizacionDTO v){
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

    /**
     * Funcion que recibe el comentario y la fecha actual para su edicion
     *
     * @param v Comentario editado contenido en el dto de Visualizacion
     * @return Mensaje que confirma los cambios de comentario
     */
    @GetMapping("/editar-comentario")
    public ResponseEntity<String> editarComentario(@RequestBody VisualizacionDTO v){
        HttpStatus estado = HttpStatus.OK;
        String mensaje = "";
        try{
            servicioPelicula.editarComentario(v);
            mensaje = "Comentario editado correctamente";
        }catch (Exception e){
            mensaje = e.getMessage();
            estado = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(mensaje,estado);
    }

    /**
     * Funcion que devuelve un listado aleatorio de peliculas para la pagina de Inicio
     *
     * @return El listado de peliculas
     */
    @GetMapping(path = "/listadoInicio")
    public ResponseEntity<PeliculaDTO> obtenerPeliculasAleatorias(){
        HttpStatus estado = HttpStatus.OK;
        String mensaje = "";
        ObjectMapper obj = new ObjectMapper();
        try{
            mensaje = obj.writeValueAsString(servicioPelicula.listadoPeliculasAleatorias());
        }catch (Exception e){
            mensaje = e.getMessage();
            estado = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(mensaje,estado);
    }

    @GetMapping(path = "/listadoUsuarios/{idPelicula}")
    public ResponseEntity<String> obtenerListadoUsuarios(@PathVariable ("idPelicula") Integer idPelicula){
        HttpStatus estado = HttpStatus.OK;
        String mensaje = "";
        ObjectMapper obj = new ObjectMapper();
        try{
            mensaje = obj.writeValueAsString(servicioPelicula.listaUsuariosPelicula(idPelicula));
        }catch (Exception e){
            mensaje = e.getMessage();
            estado = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(mensaje,estado);
    }

    @GetMapping(path = "/obtenerMinMaxYear")
    public ResponseEntity<String> obtenerMinMaxYear(){
        HttpStatus estado = HttpStatus.OK;
        String mensaje = "";
        try{
            mensaje = servicioPelicula.minMaxYear() ;
        }catch (Exception e){
            mensaje = e.getMessage();
            estado = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(mensaje,estado);
    }

}
