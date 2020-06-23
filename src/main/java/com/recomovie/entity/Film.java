package com.recomovie.entity;

import javax.persistence.*;
import java.util.ArrayList;


@Entity
@Table(name = "Film")
public class Film {
    @Id
    @Column(name = "idFilm")
    private int idFilm;
    private String title;
    private String genre;
    private String synopsis;
    private String director;
    private int year;
    private int duration;

    @ManyToOne
    private ArrayList<Rating> ratings;

    public Film(){
        this.idFilm = -9999999;
        this.title = "";
        this.genre = "";
        this.synopsis = "";
        this.director = "";
        this.year = -999999;
        this.duration = -9999999;
    }

    public Film(int idFilm, String title, String genre, String synopsis, String director, int year, int duration) {
        this.idFilm = idFilm;
        this.title = title;
        this.genre = genre;
        this.synopsis = synopsis;
        this.director = director;
        this.year = year;
        this.duration = duration;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Film{" +
                "idFilm=" + idFilm +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", director='" + director + '\'' +
                ", year=" + year +
                ", duration=" + duration +
                '}';
    }
}
