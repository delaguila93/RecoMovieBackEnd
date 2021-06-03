/**
 * @author Jose Maria del Aguila Lopez
 */

package com.recomovie.serviceimp;

import com.recomovie.dao.PeliculaDAO;
import com.recomovie.dao.UsuarioDAO;
import com.recomovie.dto.PeliculaDTO;
import com.recomovie.dto.UsuarioDTO;
import com.recomovie.dto.VisualizacionDTO;
import com.recomovie.entity.Pelicula;
import com.recomovie.entity.Visualizacion;
import com.recomovie.service.IPeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PeliculaService implements IPeliculaService {

    @Autowired
    private PeliculaDAO peliculaDao;

    @Autowired
    private UsuarioService usuarioDao;


    public PeliculaDTO obtenerPelicula(int idPelicula) {
        return peliculaDao.obtener(idPelicula).toDTO();
    }


    public List<PeliculaDTO> listadoPeliculas() {
        List<PeliculaDTO> listadoPeliculas = new ArrayList<>();
        List<Pelicula> lista = peliculaDao.obtenerTodos();
        for (Pelicula p : lista) {
            listadoPeliculas.add(p.toDTO());
        }
        return listadoPeliculas;
    }

    public List<PeliculaDTO> buscarTitulo(String titulo) {
        List<PeliculaDTO> listadoPeliculas = new ArrayList<>();
        List<Pelicula> lista = peliculaDao.buscarTitulo(titulo);
        for (Pelicula p : lista) {
            listadoPeliculas.add(p.toDTO());
        }
        return listadoPeliculas;
    }

    /* -- A partir de aqui son los metodos concretos del servicio de peliculas -- */

    public List<PeliculaDTO> listadoPeliculasAleatorias() {
        List<PeliculaDTO> listadoPeliculas = new ArrayList<>();
        List<Pelicula> lista = new ArrayList<>(); // Listado donde se almacenan las peliculas que se van añadiendo para evitar añadir peliculas repetidas
        int contadorPeliculas = 0;
        int idPeliculaAleatoria = 0;
        int idUltimaPelicula = peliculaDao.ultimaPelicula();
        while (contadorPeliculas < 12) {
            idPeliculaAleatoria = (int) (Math.random() * (idUltimaPelicula - 1 + 1) + 1);
            Pelicula p = peliculaDao.obtener(idPeliculaAleatoria);
            if (!lista.contains(p) && p != null) {
                lista.add(p);
                listadoPeliculas.add(p.toDTO());
                contadorPeliculas++;
            }

        }
        return listadoPeliculas;
    }

    /**
     * @param idPelicula
     * @return
     */

    public List<VisualizacionDTO> obtenerComentariosPelicula(int idPelicula) {
        List<Visualizacion> listado = new ArrayList<>();
        List<VisualizacionDTO> listadoPeliculas = new ArrayList<>();
        try {
            listado = peliculaDao.getComentariosPelicula(idPelicula);
            for (Visualizacion v : listado) {
                listadoPeliculas.add(v.toDTO());
            }
        } catch (Exception e) {
            return null;
        }
        return listadoPeliculas;
    }

    /**
     * Funcion que busca las obtiene las peliculas segun el genero o generos dado
     *
     * @param genero
     * @return Listado de peliculas por genero
     */
    public List<PeliculaDTO> buscarGenero(String genero) {
        List<PeliculaDTO> listadoPeliculas = new ArrayList<>();
        List<Pelicula> pelis = new ArrayList<>();
        String genre = genero.substring(1,genero.length()-2);
        String[] generos = genre.split(",");
        for (int i = 0; i< generos.length ; ++i){
            if(generos[i].charAt(0) != generos[i].charAt(1) ){
                generos[i]=generos[i].substring(1,generos[i].length()-1);
            }
        }
        for (String g : generos) {
            List<Pelicula> lista = peliculaDao.buscarGenero(g);
            pelis.addAll(lista.stream().distinct().collect(Collectors.toList()));
        }

        for (Pelicula p : pelis) {
            listadoPeliculas.add(p.toDTO());
        }
        return listadoPeliculas;
    }

    /**
     * @param year
     * @return
     */
    public List<PeliculaDTO> buscarYear(String year) {
        String[] years = year.split(" ");
        int minYear = Integer.parseInt(years[0].substring(1,years[0].length()));
        int maxYear = Integer.parseInt(years[1].substring(0,years[1].length()-1));

        List<PeliculaDTO> listadoPeliculas = new ArrayList<>();
        List<Pelicula> lista = peliculaDao.buscarYear(minYear,maxYear);
        for (Pelicula p : lista) {
            listadoPeliculas.add(p.toDTO());
        }
        return listadoPeliculas;
    }

    public void anadirValoracion(VisualizacionDTO v) throws ParseException {
        Pelicula p = peliculaDao.obtener(v.getIdPelicula());
        Visualizacion obtenido = new Visualizacion();
        int idBuscado = 0;
        if(p.existeVisualizacion(v.getIdPelicula(), v.getIdUsuario())){
            Set<Visualizacion> listado = p.getVisualizaciones();
            for (Visualizacion visualizacion: listado){
                if((v.getIdUsuario() == visualizacion.getUsuario().getIdUsuario()) && (v.getIdPelicula() == visualizacion.getPelicula().getIdPelicula())){
                    idBuscado = visualizacion.getIdVisualizacion();
                }
            }
            obtenido.setValoracion(v.getValoracion());
            peliculaDao.anadirValoracion(idBuscado,v.getValoracion());
        }else{
            peliculaDao.crearVisualizacion(v.getIdPelicula(), v.getIdUsuario());
            Set<Visualizacion> listado = p.getVisualizaciones();
            for (Visualizacion visualizacion: listado){
                if((v.getIdUsuario() == visualizacion.getUsuario().getIdUsuario()) && (v.getIdPelicula() == visualizacion.getPelicula().getIdPelicula())){
                    idBuscado = visualizacion.getIdVisualizacion();
                }
            }
            obtenido.setValoracion(v.getValoracion());
            peliculaDao.anadirValoracion(idBuscado,v.getValoracion());
        }
    }


    public void anadirComentario(VisualizacionDTO v) throws ParseException {
        if (peliculaDao.obtenerVisualizacion(v.getIdPelicula(), v.getIdUsuario()) == null) {
            peliculaDao.crearVisualizacion(v.getIdPelicula(), v.getIdUsuario());
        }
        peliculaDao.anadirComentario(v.getIdPelicula(), v.getIdUsuario(), v.getFechaComentario(), v.getComentario());
    }

    public void editarComentario(VisualizacionDTO v) throws ParseException {
        peliculaDao.editarComentario(Visualizacion.fromDTO(v));
    }

    public void editarValoracion(VisualizacionDTO v) throws ParseException {
        System.out.println(v.getIdPelicula());
       // peliculaDao.editarValoracion(Visualizacion.fromDTO(v));
    }

    public List<String> listaUsuariosPelicula(int idPelicula) {
        List<Visualizacion> listado = new ArrayList<>();
        List<String> listadoUsuarios = new ArrayList<>();
        try {
            listado = peliculaDao.getComentariosPelicula(idPelicula);
            for (Visualizacion v : listado) {
                listadoUsuarios.add(v.getUsuario().getNombreUsuario());
            }
        } catch (Exception e) {
            return null;
        }
        return listadoUsuarios;
    }

    public String minMaxYear(){
        String min = String.valueOf( peliculaDao.minYear());
        String max = String.valueOf( peliculaDao.maxYear());
        return min +" "+ max;
    }
}
