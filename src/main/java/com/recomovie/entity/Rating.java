package com.recomovie.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 *
 * @author Jose Maria del Aguila Lopez
 *
 */
@Entity
@Table(name = "Rating")
public class Rating {

    @Id
    private int idRating;
    private int idUser;
    private int idFilm;
    private float rating;

    public Rating(){
        this.idRating = -99999;
        this.idUser = -9999;
        this.idFilm = -99999;
        this.rating = -9999;
    }

    public Rating(int idRating, int idUser, int idFilm, float rating) {
        this.idRating = idRating;
        this.idUser = idUser;
        this.idFilm = idFilm;
        this.rating = rating;
    }

    public int getIdRating() {
        return idRating;
    }

    public void setIdRating(int idRating) {
        this.idRating = idRating;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "idRating=" + idRating +
                ", idUser=" + idUser +
                ", idFilm=" + idFilm +
                ", rating=" + rating +
                '}';
    }
}
