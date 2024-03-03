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

    public String getDiametre() {
        return "Diametre :"+ diametre;
    }

    public void setDiametre(float diamettre) {
        this.diametre = diamettre;
    }

    public float getLargeur() {
        return largeur;
    }

    public void setLargeur(float largeur) {
        this.largeur = largeur;
    }

    private float diametre;
    private float largeur;



    public String toString(){
        return nom;
    }

}
