package com.gestionstock.gestionstock.entity;



public class Forme {
    public Forme(String nom){
        this.nom = nom ;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    private String nom;

    public String toString(){
        return nom;
    }

}
