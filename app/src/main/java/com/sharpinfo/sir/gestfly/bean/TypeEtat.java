package com.sharpinfo.sir.gestfly.bean;

import java.io.Serializable;
import java.util.Objects;

public class TypeEtat implements Serializable {
    private static final Long serialVersionUID = 1L;
    private Long id;
    private String libelle;

    public TypeEtat() {
    }

    public TypeEtat(Long id) {
        this.id = id;
    }

    public TypeEtat(Long id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeEtat typeEtat = (TypeEtat) o;
        return Objects.equals(id, typeEtat.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TypeEtat{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}
