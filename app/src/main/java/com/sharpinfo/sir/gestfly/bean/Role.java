package com.sharpinfo.sir.gestfly.bean;

import java.io.Serializable;
import java.util.Objects;

public class Role implements Serializable {
    private static final Long serialVersionUID = 1L;
    private Long id;
    private String lebelle;

    public Role() {
    }

    public Role(Long id) {
        this.id = id;
    }

    public Role(Long id, String lebelle) {
        this.id = id;
        this.lebelle = lebelle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLebelle() {
        return lebelle;
    }

    public void setLebelle(String lebelle) {
        this.lebelle = lebelle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", lebelle='" + lebelle + '\'' +
                '}';
    }
}

