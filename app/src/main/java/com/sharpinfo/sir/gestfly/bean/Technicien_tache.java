package com.sharpinfo.sir.gestfly.bean;

import java.io.Serializable;
import java.util.Objects;

public class Technicien_tache implements Serializable {
    private static final Long serialVersionUID = 1L;
    private Long id;
    private boolean etat;
    private Tache tache = new Tache();
    private User user = new User();

    public Technicien_tache() {
    }

    public Technicien_tache(Long id) {
        this.id = id;
    }

    public Technicien_tache(Long id, boolean etat, Long tacheId, Long userId) {
        this.id = id;
        this.etat = etat;
        tache.setId(tacheId);
        user.setId(userId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
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
        Technicien_tache that = (Technicien_tache) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Technicien_tache{" +
                "id=" + id +
                ", etat=" + etat +
                '}';
    }
}
