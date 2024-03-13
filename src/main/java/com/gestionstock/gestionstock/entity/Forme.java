package com.gestionstock.gestionstock.entity;



public class Forme {
    public Forme(float diametre, float largeur, float coteSurPlat, float hauteur, float epaisseur){
        this.diametre = diametre ;
        this.largeur = largeur;
        this.coteSurPlat = coteSurPlat;
        this.epaisseur = epaisseur;
        this.hauteur = hauteur;
    }






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
        return " Dimention : "+
                ((largeur !=0)? "l: "+largeur: "") +" "+
                ((diametre !=0)? "d: "+diametre: "") +" "+
                ((coteSurPlat !=0)? "cp: "+coteSurPlat: "") +" "+
                ((hauteur !=0)? "h: "+hauteur: "") +" "+
                ((epaisseur !=0)? "ep: "+epaisseur: "") ;

    }

}
