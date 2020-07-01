package com.recomovie.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;


@Entity
@Table(name = "Pelicula")
public class Pelicula {
    @Id
    @Column(name = "idPelicula")
    private int idPelicula;
    private String titulo;
    private String genero;
    private String sinopsis;
    private String director;
    private int year;
    private int duracion;

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
    }

    public Pelicula(int idPelicula, String titulo, String genero, String sinopsis, String director, int year, int duracion) {
        this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.genero = genero;
        this.sinopsis = sinopsis;
        this.director = director;
        this.year = year;
        this.duracion = duracion;
        this.visualizaciones = new TreeSet<>();
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


}
