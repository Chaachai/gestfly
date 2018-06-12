package com.sharpinfo.sir.gestfly.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Projet implements Serializable {
    private static final Long serialVersionUID = 1L;
    private Long id;
    private String nom;
    private String description;
    private Date dateDebut;
    private Date dateCreation;
    private BigDecimal budget;
    private User creator = new User();
    private TypeEtat etat = new TypeEtat();

    public Projet() {
    }

    public Projet(Long id) {
        this.id = id;
    }

    public Projet(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Projet(Long id, String nom, String description, Date dateDebut, Date dateCreation, BigDecimal budget, Long creatorId, Long etatId) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateCreation = dateCreation;
        this.budget = budget;
        creator.setId(creatorId);
        etat.setId(etatId);
    }

    public Long getId() {
        return id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (creator == null)
            creator = new User();
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public TypeEtat getEtat() {
        if (etat == null)
            etat = new TypeEtat();
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
                ", titre='" + nom + '\'' +
                ", textView1='" + description + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateCreation=" + dateCreation +
                ", budget=" + budget +
                '}';
    }
}
