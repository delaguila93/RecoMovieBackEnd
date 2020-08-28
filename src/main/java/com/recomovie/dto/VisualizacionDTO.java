/**
 *
 * @author Jose Maria del Aguila Lopez
 *
 */

package com.recomovie.dto;


public class VisualizacionDTO {

    private int idVisualizacion;
    private int idPelicula;
    private int idUsuario;
    private float valoracion;

    private String fechaComentario;
    private String comentario;

    public VisualizacionDTO() {
    }

    public VisualizacionDTO(int idVisualizacion, int idPelicula, int idUsuario, float valoracion,String fechaComentario,String comentario) {
        this.idVisualizacion = idVisualizacion;
        this.idPelicula = idPelicula;
        this.idUsuario = idUsuario;
        this.valoracion = valoracion;
        this.comentario = comentario;
        this.fechaComentario = fechaComentario;
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

    public String getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(String fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "VisualizacionDTO{" +
                "idVisualizacion=" + idVisualizacion +
                ", idPelicula=" + idPelicula +
                ", idUsuario=" + idUsuario +
                ", valoracion=" + valoracion +
                ", fechaComentario='" + fechaComentario + '\'' +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}
