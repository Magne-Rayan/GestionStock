package com.gestionstock.gestionstock.entity;

public class Classe {
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(int idClasse) {
        this.idClasse = idClasse;
    }

    public Classe(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return  nom ;
    }

    private String nom;
    private int idClasse;

}
