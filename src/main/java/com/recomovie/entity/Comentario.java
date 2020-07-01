package com.recomovie.entity;

import javax.persistence.*;
import java.util.Date;

/*
 *
 * @author Jose Maria del Aguila Lopez
 *
 */
@Entity
@Table(name = "comentario")
public class Comentario {

    @Id
    private int idComentario;
    @Temporal(TemporalType.DATE)
    @Column( name="fechaComentario")
    private Date fechaComentario;
    private String comentario;

    @OneToOne(mappedBy = "comentario")
    private Visualizacion visualizacion;

    public Comentario() {
        this.idComentario = -99999;
        this.fechaComentario = null;
        this.comentario = "";
        this.visualizacion = null;
    }

    public Comentario(int idComentario, Date fechaComentario, String comentario, Visualizacion visualizacion) {
        this.idComentario = idComentario;
        this.fechaComentario = fechaComentario;
        this.comentario = comentario;
        this.visualizacion = visualizacion;
    }

    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    public Date getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(Date fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Visualizacion getVisualizacion() {
        return visualizacion;
    }

    public void setVisualizacion(Visualizacion visualizacion) {
        this.visualizacion = visualizacion;
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "idComentario=" + idComentario +
                ", fechaComentario=" + fechaComentario +
                ", comentario='" + comentario + '\'' +
                ", visualizacion=" + visualizacion +
                '}';
    }
}
