package com.sharpinfo.sir.gestfly.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Conge implements Serializable {
    private static final Long serialVersionUID = 1L;
    @SerializedName("ID")
    private Long id;
    @SerializedName("DATEDEBUT")
    private Date dateDebut;
    @SerializedName("DATEREPRISE")
    private Date dateReprise;
    @SerializedName("ETAT_ID")
    private Long etat_id;
    @SerializedName("USER_ID")
    private Long user_id;

    private User employe = new User();
    private TypeEtatDemande etat = new TypeEtatDemande();

    public Conge() {
    }

    public Conge(Long id) {
        this.id = id;
    }

    public Long getEtat_id() {
        return etat_id;
    }

    public void setEtat_id(Long etat_id) {
        this.etat_id = etat_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Conge(Long id, Date dateDebut, Date dateReprise, Long employeId, Long etatId) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateReprise = dateReprise;
        etat.setId(etatId);
        employe.setId(employeId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateReprise() {
        return dateReprise;
    }

    public void setDateReprise(Date dateReprise) {
        this.dateReprise = dateReprise;
    }

    public User getEmploye() {
        if (employe == null)
            employe = new User(user_id);
        return employe;
    }

    public void setEmploye(User employe) {
        this.employe = employe;
    }

    public TypeEtatDemande getEtat() {
        if (etat == null)
            etat = new TypeEtatDemande(etat_id);
        return etat;
    }

    public void setEtat(TypeEtatDemande etat) {
        this.etat = etat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conge conge = (Conge) o;
        return Objects.equals(id, conge.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Conge{" +
                "id=" + id +
                ", dateDebut=" + dateDebut +
                ", dateReprise=" + dateReprise +
                ", etat_id=" + etat_id +
                ", user_id=" + user_id +
                '}';
    }
}
