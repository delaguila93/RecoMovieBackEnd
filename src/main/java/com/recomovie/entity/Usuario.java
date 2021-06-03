/**
 *
 * @author Jose Maria del Aguila Lopez
 *
 */

package com.recomovie.entity;

import com.recomovie.dto.UsuarioDTO;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @Column( name="idUsuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    /**
     * Contructor por defecto de Usuario
     */
    public Usuario() {
        this.nombreUsuario = "";
        this.password = "";
        this.eMail = "";
        this.fechaNacimineto = null;
        this.peliculasVistas = null;
    }

    /**
     * Contructor parametrizado de Usuario
     * @param nombreUsuario
     * @param password
     * @param eMail
     * @param fechaNacimineto
     */
    public Usuario( String nombreUsuario, String password, String eMail, Date fechaNacimineto) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.eMail = eMail;
        this.fechaNacimineto = fechaNacimineto;
        this.peliculasVistas = new TreeSet<>();
    }

    /* -- Metodos Get y Set de los atributos de Usuario -- */
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

    public void anadirPeliculaVista(Visualizacion v ){
        this.peliculasVistas.add(v);
    }

    /**
     * Funcion que convierte del DTO de Usuario a Usuario
     * @param u El DTO del Usuario
     * @return El usuario convertido a partir del DTO
     * @throws ParseException
     */
    public static Usuario fromDTO(UsuarioDTO u) throws ParseException {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date d = df.parse(u.getFechaNacimiento());
        return new Usuario(u.getNombreUsuario(),u.getPassword(),u.geteMail(), d);
    }

    /**
     * Funcion que convierte el Usuario a DTO
     * @return
     */
    public UsuarioDTO toDTO(){
        return new UsuarioDTO(this.idUsuario,this.nombreUsuario,this.password,this.eMail,this.fechaNacimineto.toString(),this.peliculasVistas.size());
    }

    /**
     * Funcion que revisa si el usuario ha visto la pelicula
     * @param visualizacion
     * @return Si el usuario ha visto la pelicula
     */
    public boolean existeVisualizacion(Visualizacion visualizacion){
        for(Visualizacion v : this.peliculasVistas){
            if((v.getPelicula().equals(visualizacion.getPelicula()) ) && v.getUsuario().equals(this)){
                return true;
            }
        }
        return false;
    }

    public void quitarPeliculaVista(Visualizacion v){
        this.peliculasVistas.remove(v);
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
