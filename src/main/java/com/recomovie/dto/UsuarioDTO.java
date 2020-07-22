package com.recomovie.dto;

/*
 *
 * @author Jose Maria del Aguila Lopez
 *
 */
public class UsuarioDTO {

    private int idUsuario;
    private String nombreUsuario;
    private String password;
    private String eMail;
    private String fechaNacimiento;
    private int numPeliculasVistas;

    public UsuarioDTO() {
    }

    public UsuarioDTO(int idUsuario, String nombreUsuario, String password, String eMail, String fechaNacimiento, int numPeliculasVistas) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.eMail = eMail;
        this.fechaNacimiento = fechaNacimiento;
        this.numPeliculasVistas = numPeliculasVistas;
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getNumPeliculasVistas() {
        return numPeliculasVistas;
    }

    public void setNumPeliculasVistas(int numPeliculasVistas) {
        this.numPeliculasVistas = numPeliculasVistas;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "idUsuario=" + idUsuario +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", password='" + password + '\'' +
                ", eMail='" + eMail + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", numPeliculasVistas=" + numPeliculasVistas +
                '}';
    }
}
