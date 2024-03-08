package com.gestionstock.gestionstock.entity;



public class Forme {
    public Forme(String nom,float diametre, float largeur, float coteSurPlat, float hauteur, float epaisseur){
        this.nom = nom ;
        this.diametre = diametre ;
        this.largeur = largeur;
        this.coteSurPlat = coteSurPlat;
        this.epaisseur = epaisseur;
        this.hauteur = hauteur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    private String nom;


    public float getDiametre() {
        return diametre;
    }

    public void setDiametre(float diametre) {
        this.diametre = diametre;
    }

    public float getLargeur() {
        return largeur;
    }

    public void setLargeur(float largeur) {
        this.largeur = largeur;
    }

    private float diametre;
    private float largeur;

    public float getEpaisseur() {
        return epaisseur;
    }

    public void setEpaisseur(float epaisseur) {
        this.epaisseur = epaisseur;
    }

    private float epaisseur;

    public float getHauteur() {
        return hauteur;
    }

    public void setHauteur(float hauteur) {
        this.hauteur = hauteur;
    }

    private float hauteur;

    public float getCoteSurPlat() {
        return coteSurPlat;
    }

    public void setCoteSurPlat(float coteSurPlat) {
        this.coteSurPlat = coteSurPlat;
    }

    private float coteSurPlat;



    public String toString(){
        return nom + " dimention :"+largeur + diametre + coteSurPlat + hauteur ;

    }

}
