package com.sharpinfo.sir.gestfly.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class journee implements Serializable {
    private static final Long serialVersionUID = 1L;
    private int id;
    private Long tempDebut;
    private Long tempFin;
    private Date dateDebut;
    private Date dateFin;
    private String localisationDebut;
    private String localisationFin;
    private User user = new User();

    public journee() {
    }

    public journee(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getTempDebut() {
        return tempDebut;
    }

    public void setTempDebut(Long tempDebut) {
        this.tempDebut = tempDebut;
    }

    public Long getTempFin() {
        return tempFin;
    }

    public void setTempFin(Long tempFin) {
        this.tempFin = tempFin;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getLocalisationDebut() {
        return localisationDebut;
    }

    public void setLocalisationDebut(String localisationDebut) {
        this.localisationDebut = localisationDebut;
    }

    public String getLocalisationFin() {
        return localisationFin;
    }

    public void setLocalisationFin(String localisationFin) {
        this.localisationFin = localisationFin;
    }

    public User getUser() {
        if (user == null)
            user = new User();
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        journee journee = (journee) o;
        return id == journee.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "journee{" +
                "id=" + id +
                ", tempDebut=" + tempDebut +
                ", tempFin=" + tempFin +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", localisationDebut='" + localisationDebut + '\'' +
                ", localisationFin='" + localisationFin + '\'' +
                '}';
    }
}
