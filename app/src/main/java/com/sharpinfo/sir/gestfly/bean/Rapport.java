package com.sharpinfo.sir.gestfly.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Rapport implements Serializable {
    private static final Long serialVersionUID = 1L;
    private Long id;
    private String titre;
    private Date date;
    private String text;
    private Tache tache = new Tache();
    private Projet projet = new Projet();
    private User user = new User();

    public Rapport() {
        date = new Date();
    }

    public Rapport(Long id) {
        date = new Date();
        this.id = id;
    }

    public Rapport(Long id, String titre, Date date, String text, Long tacheId, Long projetId, Long userId) {
        this.id = id;
        this.titre = titre;
        this.date = date;
        this.text = text;
        tache.setId(tacheId);
        projet.setId(projetId);
        user.setId(userId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Tache getTache() {
        if (tache == null)
            tache = new Tache();
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }

    public Projet getProjet() {
        if (projet == null)
            projet = new Projet();
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
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
        Rapport rapport = (Rapport) o;
        return Objects.equals(id, rapport.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Rapport{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", date=" + date +
                ", text='" + text + '\'' +
                '}';
    }
}
