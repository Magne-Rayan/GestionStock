package com.gestionstock.gestionstock.entity;

public class TypeForme {

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom ;
    }

    public TypeForme(String nom) {
        this.nom = nom;
    }

    private String nom;
}
