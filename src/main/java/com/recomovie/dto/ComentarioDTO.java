package com.recomovie.dto;
/*
 *
 * @author Jose Maria del Aguila Lopez
 *
 */

public class ComentarioDTO {

    private int idComentario;
    private String fechaComentario;
    private String comentario;

    public ComentarioDTO() {
    }

    public ComentarioDTO(int idComentario, String fechaComentario, String comentario) {
        this.idComentario = idComentario;
        this.fechaComentario = fechaComentario;
        this.comentario = comentario;
    }

    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
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
        return "ComentarioDTO{" +
                "idComentario=" + idComentario +
                ", fechaComentario='" + fechaComentario + '\'' +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}
