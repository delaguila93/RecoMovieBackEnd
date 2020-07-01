package com.recomovie.entity;


import javax.persistence.*;
import java.util.Set;

/*
 *
 * @author Jose Maria del Aguila Lopez
 *
 */
@Entity
@Table(name = "Visualizacion")
public class Visualizacion {

    @Id
    private int idRating;

    @ManyToOne
    @JoinColumn(name = "idPelicula")
    private Pelicula pelicula;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    private float rating;

    @OneToOne
    @JoinColumn(name = "idComentario")
    private Comentario comentario;

    public Visualizacion(){
        this.idRating = -99999;
        this.pelicula = null;
        this.usuario = null;
        this.rating = -999;
        this.comentario = null;

    }

    public Visualizacion(int idRating, Pelicula pelicula, Usuario usuario, float rating, Comentario comentario) {
        this.idRating = idRating;
        this.pelicula = pelicula;
        this.usuario = usuario;
        this.rating = rating;
        this.comentario = comentario;
    }

    public int getIdRating() {
        return idRating;
    }

    public void setIdRating(int idRating) {
        this.idRating = idRating;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
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
                "idRating=" + idRating +
                ", pelicula=" + pelicula +
                ", usuario=" + usuario +
                ", rating=" + rating +
                ", comentario=" + comentario +
                '}';
    }
}
