package com.sharpinfo.sir.gestfly.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class DemandeSalaire implements Serializable {
    private static final Long serialVersionUID = 1L;
    @SerializedName("ID")
    private Long id;
    @SerializedName("SALAIREACTUEL")
    private BigDecimal salaireActuel;
    @SerializedName("MONTANTAJOUTER")
    private BigDecimal montantAjouter;
    @SerializedName("MOISAVANCER")
    private Integer moisAvancer;
    @SerializedName("MESSAGE")
    private String message;
    @SerializedName("DATEDEMANDE")
    private Date dateDemande;
    @SerializedName("ETAT_ID")
    private Long etat_id;
    @SerializedName("TYPE_ID")
    private Long type_id;
    @SerializedName("USER_ID")
    private Long user_id;

    private TypeDemandeSalaire type = new TypeDemandeSalaire();
    private User user = new User();
    private TypeEtatDemande etat = new TypeEtatDemande();

    public DemandeSalaire() {
        dateDemande = new Date();
    }

    public DemandeSalaire(Long id) {
        this.id = id;
        dateDemande = new Date();
    }

    public DemandeSalaire(Long id, BigDecimal salaireActuel, BigDecimal montantAjouter, Integer moisAvancer, String message, Date dateDemande, Long typeId, Long userId, Long etatId) {
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

    public Long getEtat_id() {
        return etat_id;
    }

    public void setEtat_id(Long etat_id) {
        this.etat_id = etat_id;
    }

    public Long getType_id() {
        return type_id;
    }

    public void setType_id(Long type_id) {
        this.type_id = type_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public BigDecimal getSalaireActuel() {
        return salaireActuel;
    }

    public void setSalaireActuel(BigDecimal salaireActuel) {
        this.salaireActuel = salaireActuel;
    }

    public BigDecimal getMontantAjouter() {
        return montantAjouter;
    }

    public void setMontantAjouter(BigDecimal montantAjouter) {
        this.montantAjouter = montantAjouter;
    }

    public Integer getMoisAvancer() {
        return moisAvancer;
    }

    public void setMoisAvancer(Integer moisAvancer) {
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
            user = new User(user_id);
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TypeEtatDemande getEtat() {
        if (etat == null)
            etat = new TypeEtatDemande(etat_id);
        return etat;
    }

    public void setEtat(TypeEtatDemande etat) {
        this.etat = etat;
    }

    public TypeDemandeSalaire getType() {
        if (type == null)
            type = new TypeDemandeSalaire(type_id);
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
