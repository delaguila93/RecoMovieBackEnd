/*
 *
 * @author Jose Maria del Aguila Lopez
 *
 */

package com.recomovie.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "User")
public class User {

    @Id
    @Column( name="idUser")
    private int idUser;
    @Column( name="userName")
    private String userName;
    private String password;
    private String eMail;
    @Temporal(TemporalType.DATE)
    @Column( name="birthDate")
    private Date birthDate;

    public User() {
        this.idUser = -1;
        this.userName = "";
        this.password = "";
        this.eMail = "";
        this.birthDate = null;
    }

    public User(int idUser, String userName, String password,String eMail, Date birthDate) {
        this.idUser = idUser;
        this.userName = userName;
        this.password = password;
        this.eMail = eMail;
        this.birthDate = birthDate;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", userName='" + userName + '\'' +
                ", eMail='" + eMail + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
