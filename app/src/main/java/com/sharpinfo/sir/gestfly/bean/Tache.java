package com.sharpinfo.sir.gestfly.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Tache implements Serializable {
    private static final Long serialVersionUID = 1L;
    private Long id;
    private String nom;
    private Date dateDebut;
    private Date dateCreation;
    private String description;
    private Projet projet = new Projet();
    private User creator = new User();

    public Tache() {
    }

    public Tache(Long id, String nom, Date dateDebut, Date dateCreation, String description, Long projetId, Long creatorId) {
        this.id = id;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateCreation = dateCreation;
        this.description = description;
        projet.setId(projetId);
        creator.setId(creatorId);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Projet getProjet() {
        if(projet == null)
            projet = new Projet();
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public User getCreator() {
        if(creator == null)
            creator = new User();
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tache tache = (Tache) o;
        return Objects.equals(id, tache.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String
    toString() {
        return "Tache{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateCreation=" + dateCreation +
                ", description='" + description + '\'' +
                '}';
    }
}
