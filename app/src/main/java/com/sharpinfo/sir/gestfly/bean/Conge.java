package com.sharpinfo.sir.gestfly.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Conge implements Serializable {
    private static final Long serialVersionUID = 1L;
    private Long id;
    private Date dateDebut;
    private Date dateReprise;
    private User employe = new User();
    private TypeEtatConge etat = new TypeEtatConge();

    public Conge() {
    }

    public Conge(Long id) {
        this.id = id;
    }

    public Conge(Long id, Date dateDebut, Date dateReprise, String employeId, Long etatId) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateReprise = dateReprise;
        etat.setId(etatId);
        employe.setId(employeId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateReprise() {
        return dateReprise;
    }

    public void setDateReprise(Date dateReprise) {
        this.dateReprise = dateReprise;
    }

    public User getEmploye() {
        if (employe == null)
            employe = new User();
        return employe;
    }

    public void setEmploye(User employe) {
        this.employe = employe;
    }

    public TypeEtatConge getEtat() {
        if (etat == null)
            etat = new TypeEtatConge();
        return etat;
    }

    public void setEtat(TypeEtatConge etat) {
        this.etat = etat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conge conge = (Conge) o;
        return Objects.equals(id, conge.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Conge{" +
                "id=" + id +
                ", dateDebut=" + dateDebut +
                ", dateReprise=" + dateReprise +
                '}';
    }
}
