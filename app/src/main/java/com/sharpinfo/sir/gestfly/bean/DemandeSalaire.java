package com.sharpinfo.sir.gestfly.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class DemandeSalaire implements Serializable {
    private static final Long serialVersionUID = 1L;
    private Long id;
    private double salaireActuel;
    private double montantAjouter;
    private Date moisAvancer;
    private String message;
    private Date dateDemande;
    private TypeDemandeSalaire type = new TypeDemandeSalaire();
    private User user = new User();
    private TypeEtatConge etat = new TypeEtatConge();

    public DemandeSalaire() {
        dateDemande = new Date();
    }

    public DemandeSalaire(Long id) {
        this.id = id;
        dateDemande = new Date();
    }

    public DemandeSalaire(Long id, double salaireActuel, double montantAjouter, Date moisAvancer, String message, Date dateDemande, Long typeId, Long userId, Long etatId) {
        this.id = id;
        this.salaireActuel = salaireActuel;
        this.montantAjouter = montantAjouter;
        this.moisAvancer = moisAvancer;
        this.message = message;
        this.dateDemande = dateDemande;
        user.setId(userId);
        etat.setId(etatId);
        type.setId(typeId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getSalaireActuel() {
        return salaireActuel;
    }

    public void setSalaireActuel(double salaireActuel) {
        this.salaireActuel = salaireActuel;
    }

    public double getMontantAjouter() {
        return montantAjouter;
    }

    public void setMontantAjouter(double montantAjouter) {
        this.montantAjouter = montantAjouter;
    }

    public Date getMoisAvancer() {
        return moisAvancer;
    }

    public void setMoisAvancer(Date moisAvancer) {
        this.moisAvancer = moisAvancer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }

    public User getUser() {
        if (user == null)
            user = new User();
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TypeEtatConge getEtat() {
        if (etat == null)
            etat = new TypeEtatConge();
        return etat;
    }

    public void setEtat(TypeEtatConge etat) {
        this.etat = etat;
    }

    public TypeDemandeSalaire getType() {
        if (type == null)
            type = new TypeDemandeSalaire();
        return type;
    }

    public void setType(TypeDemandeSalaire type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DemandeSalaire that = (DemandeSalaire) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DemandeSalaire{" +
                "id=" + id +
                ", salaireActuel=" + salaireActuel +
                ", montantAjouter=" + montantAjouter +
                ", moisAvancer=" + moisAvancer +
                ", message='" + message + '\'' +
                ", dateDemande=" + dateDemande +
                '}';
    }
}
