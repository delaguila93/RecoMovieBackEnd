package com.recomovie.dto;

/*
 *
 * @author Jose Maria del Aguila Lopez
 *
 */

public class VisualizacionDTO {

    private int idVisualizacion;
    private int idPelicula;
    private int idUsuario;
    private float valoracion;
    private int idComentario;

    public VisualizacionDTO() {
    }

    public VisualizacionDTO(int idVisualizacion, int idPelicula, int idUsuario, float valoracion, int idComentario) {
        this.idVisualizacion = idVisualizacion;
        this.idPelicula = idPelicula;
        this.idUsuario = idUsuario;
        this.valoracion = valoracion;
        this.idComentario = idComentario;
    }

    public int getIdVisualizacion() {
        return idVisualizacion;
    }

    public void setIdVisualizacion(int idVisualizacion) {
        this.idVisualizacion = idVisualizacion;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }

    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    @Override
    public String toString() {
        return "VisualizacionDTO{" +
                "idVisualizacion=" + idVisualizacion +
                ", idPelicula=" + idPelicula +
                ", idUsuario=" + idUsuario +
                ", valoracion=" + valoracion +
                ", idComentario=" + idComentario +
                '}';
    }
}
