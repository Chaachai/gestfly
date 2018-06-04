package com.sharpinfo.sir.gestfly.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Etat implements Serializable {
    private static final Long serialVersionUID = 1L;
    private Long id;
    private Date date;
    private TypeEtat statut = new TypeEtat();
    private Projet projet = new Projet();
    private Tache tache = new Tache();
    private User user = new User();

    public Etat() {
    }

    public Etat(Long id) {
        this.id = id;
    }

    public Etat(Long id, Date date, Long statutId, Long projetId, Long tacheId, Long userId) {
        this.id = id;
        this.date = date;
        statut.setId(statutId);
        projet.setId(projetId);
        tache.setId(tacheId);
        user.setId(userId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TypeEtat getStatut() {
        if (statut == null)
            statut = new TypeEtat();
        return statut;
    }

    public void setStatut(TypeEtat statut) {
        this.statut = statut;
    }

    public Projet getProjet() {
        if (projet == null)
            projet = new Projet();
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public Tache getTache() {
        if (tache == null)
            tache = new Tache();
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
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
        Etat etat = (Etat) o;
        return Objects.equals(id, etat.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Etat{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }
}