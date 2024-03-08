package com.gestionstock.gestionstock.entity;

import java.sql.Blob;

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

    public TypeForme(int idTypeForme,String nom,Blob image) {
        this.idTypeForme = idTypeForme;
        this.nom = nom;
        this.image = image;
    }

    private String nom;

    public int getIdTypeForme() {
        return idTypeForme;
    }

    public void setIdTypeForme(int idTypeForme) {
        this.idTypeForme = idTypeForme;
    }

    private int idTypeForme;

    public Blob getImage() {
        return image;
    }

    private Blob image;
}
