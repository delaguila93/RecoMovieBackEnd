package com.recomovie.entity;

import com.recomovie.dto.VisualizacionDTO;
import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/*
 *
 * @author Jose Maria del Aguila Lopez
 *
 */
@Entity
@Table(name = "Visualizacion")
public class Visualizacion {

    @Id
    private int idVisualizacion;

    @ManyToOne
    @JoinColumn(name = "idPelicula")
    private Pelicula pelicula;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    private float valoracion;

    @Temporal(TemporalType.DATE)
    @Column( name="fechaComentario")
    private Date fechaComentario;

    private String comentario;

    public Visualizacion(){
        this.idVisualizacion = -99999;
        this.pelicula = null;
        this.usuario = null;
        this.valoracion = -999;
        this.fechaComentario = null;
        this.comentario = "";

    }

    public Visualizacion(int idVisualizacion,float valoracion,Date fechaComentario,String comentario) {
        //this.idVisualizacion = idVisualizacion;
        this.pelicula = new Pelicula();
        this.usuario = new Usuario();
        this.valoracion = valoracion;
        this.fechaComentario = fechaComentario;
        this.comentario = comentario;
    }

    public int getIdVisualizacion() {
        return idVisualizacion;
    }

    public void setIdVisualizacion(int idVisualizacion) {
        this.idVisualizacion = idVisualizacion;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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


    public static Visualizacion fromDTO(VisualizacionDTO v) throws ParseException {
        return new Visualizacion(v.getIdVisualizacion(),v.getValoracion(),DateFormat.getDateInstance().parse(v.getFechaComentario()),v.getComentario());
    }

    public VisualizacionDTO toDTO(){
        return new VisualizacionDTO(this.idVisualizacion,this.pelicula.getIdPelicula(),this.usuario.getIdUsuario(),this.valoracion,this.fechaComentario.toString(),this.comentario);
    }

    @Override
    public String toString() {
        return "Visualizacion{" +
                "idVisualizacion=" + idVisualizacion +
                ", pelicula=" + pelicula +
                ", usuario=" + usuario +
                ", valoracion=" + valoracion +
                ", fechaComentario=" + fechaComentario +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}
