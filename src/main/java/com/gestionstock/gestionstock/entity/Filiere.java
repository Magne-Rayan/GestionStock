package com.gestionstock.gestionstock.entity;

public class Filiere {
    public Filiere(int id_filiere, String nom) {
        this.id_filiere = id_filiere;
        this.nom = nom;
    }

    private int id_filiere;

    public int getId_filiere() {
        return id_filiere;
    }

    public void setId_filiere(int id_filiere) {
        this.id_filiere = id_filiere;
    }

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

    private String nom;

}
