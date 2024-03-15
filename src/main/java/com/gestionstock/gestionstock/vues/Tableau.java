package com.gestionstock.gestionstock.vues;

import com.gestionstock.gestionstock.entity.Forme;
import com.gestionstock.gestionstock.entity.Utilisateur;
import com.gestionstock.gestionstock.sql.ConnexionBdd;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;

public class Tableau {

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

    @Override
    public String toString() {
        return "Tableau{" +
                "diametre=" + diametre +
                ", largeur=" + largeur +
                ", epaisseur=" + epaisseur +
                ", hauteur=" + hauteur +
                ", coteSurPlat=" + coteSurPlat +
                ", nom='" + nom + '\'' +
                '}';
    }

    public Tableau(float diametre, float largeur, float hauteur, float coteSurPlat, float epaisseur, String nom, float longueur, String nomMat) {

        this.diametre = diametre;
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.coteSurPlat = coteSurPlat;
        this.epaisseur = epaisseur;
        this.nom = nom;
        this.longueur = longueur;
        this.nomMat = nomMat;
    }

    private float hauteur;

    public float getCoteSurPlat() {
        return coteSurPlat;
    }

    public void setCoteSurPlat(float coteSurPlat) {
        this.coteSurPlat = coteSurPlat;
    }

    private float coteSurPlat;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    private String nom;

    public float getLongueur() {
        return longueur;
    }

    public void setLongueur(float longueur) {
        this.longueur = longueur;
    }

    private float longueur;

    public String getNomMat() {
        return nomMat;
    }

    public void setNomMat(String nomMat) {
        this.nomMat = nomMat;
    }

    private String nomMat;

}



