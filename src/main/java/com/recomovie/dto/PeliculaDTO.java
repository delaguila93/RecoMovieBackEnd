/**
 *
 * @author Jose Maria del Aguila Lopez
 *
 */


package com.recomovie.dto;

public class PeliculaDTO {

    private int idPelicula;
    private String titulo;
    private String genero;
    private String sinopsis;
    private String director;
    private int year;
    private int duracion;
    private int numVisualizaciones;
    private String urlImage;

    public PeliculaDTO() {
    }

    public PeliculaDTO(int idPelicula, String titulo, String genero, String sinopsis, String director, int year, int duracion, int numVisualizaciones, String urlImage) {
        this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.genero = genero;
        this.sinopsis = sinopsis;
        this.director = director;
        this.year = year;
        this.duracion = duracion;
        this.numVisualizaciones = numVisualizaciones;
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

    public int getNumVisualizaciones() {
        return numVisualizaciones;
    }

    public void setNumVisualizaciones(int numVisualizaciones) {
        this.numVisualizaciones = numVisualizaciones;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    @Override
    public String toString() {
        return "PeliculaDTO{" +
                "idPelicula=" + idPelicula +
                ", titulo='" + titulo + '\'' +
                ", genero='" + genero + '\'' +
                ", sinopsis='" + sinopsis + '\'' +
                ", director='" + director + '\'' +
                ", year=" + year +
                ", duracion=" + duracion +
                ", numVisualizaciones=" + numVisualizaciones +
                ", urlImage='" + urlImage + '\'' +
                '}';
    }
}
