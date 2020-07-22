/*
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
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

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
    private Set<Visualizacion> peliculasVistas;


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
        this.peliculasVistas = new TreeSet<>();
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

    public Set<Visualizacion> getPeliculasVistas() {
        return peliculasVistas;
    }

    public void setPeliculasVistas(Set<Visualizacion> peliculasVistas) {
        this.peliculasVistas = peliculasVistas;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUser=" + idUsuario +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", password='" + password + '\'' +
                ", eMail='" + eMail + '\'' +
                ", fechaNacimineto=" + fechaNacimineto +
                ", peliculasVistas=" + peliculasVistas +
                '}';
    }

    public static Usuario fromDTO(UsuarioDTO u) throws ParseException {
        return new Usuario(u.getIdUsuario(),u.getNombreUsuario(),u.getPassword(),u.geteMail(), DateFormat.getDateInstance().parse(u.getFechaNacimiento()));
    }

    public UsuarioDTO toDTO(){
        return new UsuarioDTO(this.idUsuario,this.nombreUsuario,this.password,this.eMail,this.fechaNacimineto.toString(),this.peliculasVistas.size());
    }
}
