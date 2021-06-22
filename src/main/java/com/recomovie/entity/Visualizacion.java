/**
 *
 * @author Jose Maria del Aguila Lopez
 *
 */

package com.recomovie.entity;

import com.recomovie.dto.VisualizacionDTO;
import com.recomovie.excepciones.Util;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "Visualizacion")
public class Visualizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVisualizacion;

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "idPelicula")
    private Pelicula pelicula;

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    private float valoracion;

    @Temporal(TemporalType.DATE)
    @Column( name="fechaComentario")
    private Calendar fechaComentario;

    private String comentario;

    public Visualizacion(){
        this.pelicula = null;
        this.usuario = null;
        this.valoracion = -999;
        this.fechaComentario = null;
        this.comentario = "";

    }

    public Visualizacion (Usuario u,Pelicula p){
        this.pelicula = p;
        this.usuario = u;
        this.valoracion = -999;
        this.fechaComentario = null;
        this.comentario = "";
    }

    public Visualizacion(float valoracion,Calendar fechaComentario,String comentario) {
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

    public Calendar getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(Calendar fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }


    public static Visualizacion fromDTO(VisualizacionDTO v) throws ParseException {
        if(v.getFechaComentario() == null){
            v.setFechaComentario("2000-01-01");
        };
        return new Visualizacion(v.getValoracion(), Util.parsearStringFecha(v.getFechaComentario()),v.getComentario());
    }

    public VisualizacionDTO toDTO(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2000);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        if(this.getFechaComentario() == null){
            this.setFechaComentario(cal);
        }
        return new VisualizacionDTO(this.idVisualizacion,this.pelicula.getIdPelicula(),this.usuario.getIdUsuario(),this.valoracion,Util.parsearCalendarFecha(this.fechaComentario),this.comentario);
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
