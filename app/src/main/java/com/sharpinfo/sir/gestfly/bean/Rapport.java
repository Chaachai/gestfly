package com.sharpinfo.sir.gestfly.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Rapport implements Serializable {
    private static final Long serialVersionUID = 1L;
    @SerializedName("ID")
    private Long id;
    @SerializedName("TITLE")
    private String titre;
    @SerializedName("DATE")
    private Date date;
    @SerializedName("TEXT")
    private String text;
    @SerializedName("TACHE_ID")
    private Long tache_id;
    @SerializedName("PROJET_ID")
    private Long projet_id;
    @SerializedName("USER_ID")
    private Long user_id;
    @SerializedName("IMAGE_ID")
    private Integer image_id;


    private Tache tache = new Tache();
    private Projet projet = new Projet();
    private User user = new User();
    private Image image = new Image();

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

    public Long getTache_id() {
        return tache_id;
    }

    public void setTache_id(Long tache_id) {
        this.tache_id = tache_id;
    }

    public Long getProjet_id() {
        return projet_id;
    }

    public void setProjet_id(Long projet_id) {
        this.projet_id = projet_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Integer getImage_id() {
        return image_id;
    }

    public void setImage_id(Integer image_id) {
        this.image_id = image_id;
    }

    public Image getImage() {
        if(image == null){
            image = new Image(image_id);
        }
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
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
            tache = new Tache(tache_id);
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }

    public Projet getProjet() {
        if (projet == null)
            projet = new Projet(projet_id);
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public User getUser() {
        if (user == null)
            user = new User(user_id);
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
                ", tache_id=" + tache_id +
                ", projet_id=" + projet_id +
                ", user_id=" + user_id +
                ", image_id=" + image_id +
                '}';
    }
}
