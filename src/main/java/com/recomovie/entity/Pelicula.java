/**
 *
 * @author Jose Maria del Aguila Lopez
 *
 */

package com.recomovie.entity;

import com.recomovie.dto.PeliculaDTO;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "Pelicula")
public class Pelicula {
    @Id
    @Column(name = "idPelicula")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPelicula;
    private String titulo;
    private String genero;
    @Column(name="sinopsis", length=512)
    private String sinopsis;
    private String director;
    private int year;
    private int duracion;
    private String urlImage;

    @OneToMany(mappedBy = "pelicula")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Visualizacion> visualizaciones;


    public Pelicula(){
        this.idPelicula = -9999999;
        this.titulo = "";
        this.genero = "";
        this.sinopsis = "";
        this.director = "";
        this.year = -999999;
        this.duracion = -9999999;
        this.visualizaciones = new TreeSet<>();
        this.urlImage = "";
    }

    public Pelicula(int idPelicula, String titulo, String genero, String sinopsis, String director, int year, int duracion,String urlImage) {
        this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.genero = genero;
        this.sinopsis = sinopsis;
        this.director = director;
        this.year = year;
        this.duracion = duracion;
        this.visualizaciones = new TreeSet<>();
        this.urlImage = urlImage;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public Set<Visualizacion> getVisualizaciones() {
        return visualizaciones;
    }

    public void setVisualizaciones(Set<Visualizacion> visualizaciones) {
        this.visualizaciones = visualizaciones;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public void anadirVisualizacion(Visualizacion v){
        this.visualizaciones.add(v);
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "idPelicula=" + idPelicula +
                ", titulo='" + titulo + '\'' +
                ", genero='" + genero + '\'' +
                ", sinopsis='" + sinopsis + '\'' +
                ", director='" + director + '\'' +
                ", year=" + year +
                ", duracion=" + duracion +
                ", urlImage='" + urlImage + '\'' +
                ", visualizaciones=" + visualizaciones +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        Pelicula p = (Pelicula) o;
        return this.idPelicula == p.getIdPelicula();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
        result = prime * result + ((genero == null) ? 0 : genero.hashCode());
        result = prime * result + ((sinopsis == null) ? 0 : sinopsis.hashCode());
        result = prime * result + ((director == null) ? 0 : director.hashCode());
        return result;
    }

    public static Pelicula fromDTO (PeliculaDTO p){
        return new Pelicula(p.getIdPelicula(),p.getTitulo(),p.getGenero(),p.getSinopsis(),p.getDirector(),p.getYear(),p.getDuracion(),p.getUrlImage());
    }

    public PeliculaDTO toDTO(){
        return new PeliculaDTO(this.idPelicula,this.titulo,this.genero,this.sinopsis,this.director,this.year,this.duracion,this.visualizaciones.size(),this.urlImage);
    }

    public List<Visualizacion> listadoComentarios(){
        List<Visualizacion> listado = new ArrayList<>();
        for(Visualizacion v : visualizaciones){
            if(v.getFechaComentario() != null && v.getPelicula().getIdPelicula() == this.idPelicula) {
                listado.add(v);
            }
        }
        return listado;
    }


    public boolean existeVisualizacion(int idPelicula, int idUsuario){
        for(Visualizacion v : this.visualizaciones){
            if((v.getUsuario().getIdUsuario() == idUsuario) && (v.getPelicula().getIdPelicula() == idPelicula)){
                return true;
            }
        }
        return false;
    }

    public void quitarVisualizacion(Visualizacion v){
        this.visualizaciones.remove(v);
    }

}
