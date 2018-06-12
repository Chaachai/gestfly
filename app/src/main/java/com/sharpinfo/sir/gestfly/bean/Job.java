package com.sharpinfo.sir.gestfly.bean;

import java.io.Serializable;
import java.util.Objects;

public class Job implements Serializable {
    private static final Long serialVersionUID = 1L;
    private Long id;
    private String nom;

    Job() {
    }

    public Job(Long id) {
        this.id = id;
    }

    public Job(Long id, String nom) {
        this.id = id;
        this.nom = nom;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return Objects.equals(id, job.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", titre='" + nom + '\'' +
                '}';
    }
}
