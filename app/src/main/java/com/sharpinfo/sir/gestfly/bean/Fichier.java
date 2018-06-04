package com.sharpinfo.sir.gestfly.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Fichier implements Serializable {
    private static final Long serialVersionUID = 1L;
    private Long id;
    private String nom;
    private String type;
    private Date date;
    private User user = new User();
    private boolean administrative;

    public Fichier() {
    }

    public Fichier(Long id) {
        this.id = id;
    }

    public Fichier(Long id, String nom, String type, Date date, String userId, boolean administrative) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.date = date;
        user.setId(userId);
        this.administrative = administrative;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        if (user == null)
            user = new User();
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isAdministrative() {
        return administrative;
    }

    public void setAdministrative(boolean administrative) {
        this.administrative = administrative;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fichier fichier = (Fichier) o;
        return Objects.equals(id, fichier.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Fichier{" +
                "id=" + id +
                ", etat='" + nom + '\'' +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", administrative=" + administrative +
                '}';
    }
}
