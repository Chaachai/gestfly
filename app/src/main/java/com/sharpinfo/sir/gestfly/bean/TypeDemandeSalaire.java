package com.sharpinfo.sir.gestfly.bean;

import java.io.Serializable;
import java.util.Objects;

public class TypeDemandeSalaire implements Serializable {
    private static final Long serialVersionUID = 1L;
    private Long id;
    private String type;

    public TypeDemandeSalaire() {
    }

    public TypeDemandeSalaire(Long id) {
        this.id = id;
    }

    public TypeDemandeSalaire(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeDemandeSalaire that = (TypeDemandeSalaire) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TypeDemandeSalaire{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
