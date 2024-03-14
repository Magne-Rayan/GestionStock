package com.gestionstock.gestionstock.entity;

import java.sql.Blob;

public class Systeme {
    public Systeme(String nom, int idSysteme, Blob image) {
        this.nom = nom;
        this.idSysteme = idSysteme;
        this.image = image;
    }

    @Override
    public String toString() {
        return  nom ;
    }

    private String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIdSysteme() {
        return idSysteme;
    }

    public void setIdSysteme(int idSysteme) {
        this.idSysteme = idSysteme;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    private int idSysteme;
    private Blob image;
}
