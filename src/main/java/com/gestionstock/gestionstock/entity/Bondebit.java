package com.gestionstock.gestionstock.entity;

public class Bondebit {
    private Utilisateur nom;
    private Filiere filiere;
    private Systeme systeme;
    private Piece piece;
    private TypeForme forme;
    private Materiaux materiaux;
    private Forme dimention;
    private String quantite;
    private String longTotal;
    private String stockInitial;
    private String stockFinal;

    public Utilisateur getNom() {
        return nom;
    }

    public void setNom(Utilisateur nom) {
        this.nom = nom;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public Systeme getSysteme() {
        return systeme;
    }

    public void setSysteme(Systeme systeme) {
        this.systeme = systeme;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public TypeForme getForme() {
        return forme;
    }

    public void setForme(TypeForme forme) {
        this.forme = forme;
    }

    public Materiaux getMateriaux() {
        return materiaux;
    }

    public void setMateriaux(Materiaux materiaux) {
        this.materiaux = materiaux;
    }

    public Forme getDimention() {
        return dimention;
    }

    public void setDimention(Forme dimention) {
        this.dimention = dimention;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantité) {
        this.quantite = quantité;
    }

    public String getLongTotal() {
        return longTotal;
    }

    public void setLongTotal(String longTotal) {
        this.longTotal = longTotal;
    }

    public String getStockInitial() {
        return stockInitial;
    }

    public void setStockInitial(String stockInitial) {
        this.stockInitial = stockInitial;
    }

    public String getStockFinal() {
        return stockFinal;
    }

    public void setStockFinal(String stockFinal) {
        this.stockFinal = stockFinal;
    }

    public Bondebit(Utilisateur nom, Filiere filiere, Systeme systeme, Piece piece, TypeForme forme, Materiaux materiaux, Forme dimention, String quantité, String longTotal, String stockInitial, String stockFinal) {
        this.nom = nom;
        this.filiere = filiere;
        this.systeme = systeme;
        this.piece = piece;
        this.forme = forme;
        this.materiaux = materiaux;
        this.dimention = dimention;
        this.quantite = quantite;
        this.longTotal = longTotal;
        this.stockInitial = stockInitial;
        this.stockFinal = stockFinal;
    }

}
