package com.recomovie.entity;


import com.recomovie.dto.VisualizacionDTO;

import javax.persistence.*;

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

    @OneToOne
    @JoinColumn(name = "idComentario")
    private Comentario comentario;

    public Visualizacion(){
        this.idVisualizacion = -99999;
        this.pelicula = null;
        this.usuario = null;
        this.valoracion = -999;
        this.comentario = null;

    }

    public Visualizacion(int idVisualizacion,float valoracion) {
        this.idVisualizacion = idVisualizacion;
        this.pelicula = new Pelicula();
        this.usuario = new Usuario();
        this.valoracion = valoracion;
        this.comentario = new Comentario();
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

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "Visualizacion{" +
                "idVisualizacion=" + idVisualizacion +
                ", pelicula=" + pelicula +
                ", usuario=" + usuario +
                ", valoracion=" + valoracion +
                ", comentario=" + comentario +
                '}';
    }

    public static Visualizacion fromDTO(VisualizacionDTO v){
        return new Visualizacion(v.getIdVisualizacion(),v.getValoracion());
    }

    public VisualizacionDTO toDTO(){
        return new VisualizacionDTO(this.idVisualizacion,this.pelicula.getIdPelicula(),this.usuario.getIdUsuario(),this.valoracion,this.comentario.getIdComentario());
    }
}
