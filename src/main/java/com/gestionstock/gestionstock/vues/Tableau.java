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
        return   nom + "dimenssion" +
                ((largeur !=0)? "l: "+largeur: "") +" "+
                ((diametre !=0)? "d: "+diametre: "") +" "+
                ((coteSurPlat !=0)? "cp: "+coteSurPlat: "") +" "+
                ((hauteur !=0)? "h: "+hauteur: "") +" "+
                ((epaisseur !=0)? "ep: "+epaisseur: "");
    }


    public Tableau(float diametre,float largeur, float hauteur,   float coteSurPlat,float epaisseur, String nom, float longueur, String nomMat,int idTypeForme, int idMat) {
        this.diametre = diametre;
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.coteSurPlat = coteSurPlat;
        this.epaisseur = epaisseur;
        this.nom = nom;
        this.longueur = longueur;
        this.nomMat = nomMat;
        this.id = idTypeForme;
        this.idMat = idMat;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public int getIdMat() {
        return idMat;
    }

    public void setIdMat(int idMat) {
        this.idMat = idMat;
    }

    private int idMat;


}



