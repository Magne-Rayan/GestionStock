package com.gestionstock.gestionstock.entity;

import java.sql.Blob;

public class Piece {

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIdPiece() {
        return idPiece;
    }

    public void setIdPiece(int idPiece) {
        this.idPiece = idPiece;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public int getRefMatiere() {
        return refMatiere;
    }

    public void setRefMatiere(int refMatiere) {
        this.refMatiere = refMatiere;
    }

    public int getRefSysteme() {
        return refSysteme;
    }

    public void setRefSysteme(int refSysteme) {
        this.refSysteme = refSysteme;
    }

    public Piece(String nom, Blob image) {
        this.nom = nom;
        this.image = image;
    }

    @Override
    public String toString() {
        return  nom ;
    }

    private String nom;
    private int idPiece;
    private Blob image;
    private int refMatiere;
    private int refSysteme;

    public float getLongeur() {
        return longeur;
    }

    public void setLongeur(float longeur) {
        this.longeur = longeur;
    }

    private float longeur;
}
