/**
 *
 * @author Jose Maria del Aguila Lopez
 *
 */

package com.recomovie.entity;

import com.recomovie.dto.UsuarioDTO;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;

@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @Column( name="idUsuario")
    private int idUsuario;
    @Column( name="nombreUsuario")
    private String nombreUsuario;
    private String password;
    private String eMail;
    @Temporal(TemporalType.DATE)
    @Column( name="fechaNacimiento")
    private Date fechaNacimineto;

    @OneToMany(mappedBy = "usuario")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Visualizacion> peliculasVistas;


    public Usuario() {
        this.idUsuario = -1;
        this.nombreUsuario = "";
        this.password = "";
        this.eMail = "";
        this.fechaNacimineto = null;
        this.peliculasVistas = null;
    }

    public Usuario(int idUsuario, String nombreUsuario, String password, String eMail, Date fechaNacimineto) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.eMail = eMail;
        this.fechaNacimineto = fechaNacimineto;
        this.peliculasVistas = new ArrayList<>();
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Date getFechaNacimineto() {
        return fechaNacimineto;
    }

    public void setFechaNacimineto(Date fechaNacimineto) {
        this.fechaNacimineto = fechaNacimineto;
    }

    public List<Visualizacion> getPeliculasVistas() {
        return peliculasVistas;
    }

    public void setPeliculasVistas(List<Visualizacion> peliculasVistas) {
        this.peliculasVistas = peliculasVistas;
    }

    public void anadirPeliculaVista(Visualizacion v ){
        this.peliculasVistas.add(v);
    }

    public static Usuario fromDTO(UsuarioDTO u) throws ParseException {
        return new Usuario(u.getIdUsuario(),u.getNombreUsuario(),u.getPassword(),u.geteMail(), DateFormat.getDateInstance().parse(u.getFechaNacimiento()));
    }

    public UsuarioDTO toDTO(){
        return new UsuarioDTO(this.idUsuario,this.nombreUsuario,this.password,this.eMail,this.fechaNacimineto.toString(),this.peliculasVistas.size());
    }

    public boolean existeVisualizacion(Visualizacion visualizacion){
        for(Visualizacion v : this.peliculasVistas){
            if((v.getPelicula().equals(visualizacion.getPelicula()) ) && v.getUsuario().equals(this)){
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean equals(Object o) {
        Usuario u = (Usuario) o;
        return this.idUsuario == u.getIdUsuario();
    }


        @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", password='" + password + '\'' +
                ", eMail='" + eMail + '\'' +
                ", fechaNacimineto=" + fechaNacimineto +
                ", peliculasVistas=" + peliculasVistas +
                '}';
    }
}
