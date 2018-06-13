package com.sharpinfo.sir.gestfly.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Projet implements Serializable {
    private static final Long serialVersionUID = 1L;
    @SerializedName("ID")
    private Long id;
    @SerializedName("NOM")
    private String nom;
    //private String description;
    @SerializedName("DATEDEBUTREALISATION")
    private Date dateDebut;
    @SerializedName("DATECREATION")
    private Date dateCreation;

    private BigDecimal budget;
    @SerializedName("USER_ID")
    private Long user_id;
    @SerializedName("ETAT_ID")
    private Long etat_id;
    private User creator ;
    private TypeEtat etat;

    public Projet() {
    }



    public Projet(Long id) {
        this.id = id;
    }

    public Projet(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Projet(Long id, String nom, Date dateDebut, Date dateCreation, BigDecimal budget, Long creatorId, Long etatId) {
        this.id = id;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateCreation = dateCreation;
        this.budget = budget;
        creator.setId(creatorId);
        etat.setId(etatId);
    }

    public Long getEtat_id() {
        return etat_id;
    }

    public void setEtat_id(Long etat_id) {
        this.etat_id = etat_id;
    }

    public Long getId() {
        return id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public User getCreator() {
        if(creator == null)
        creator = new User(user_id);
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public TypeEtat getEtat() {
        if(etat == null)
        etat = new TypeEtat(etat_id);
        return etat;
    }

    public void setEtat(TypeEtat etat) {
        this.etat = etat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Projet projet = (Projet) o;
        return Objects.equals(id, projet.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Projet{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateCreation=" + dateCreation +
                ", budget=" + budget +
                ", user_id=" + user_id +
                ", etat_id=" + etat_id +
                '}';
    }
}
