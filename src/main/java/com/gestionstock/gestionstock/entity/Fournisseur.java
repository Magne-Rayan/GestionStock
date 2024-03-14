package com.gestionstock.gestionstock.entity;

public class Fournisseur {

    public int getId_fournisseur() {
        return id_fournisseur;
    }

    public void setId_fournisseur(int id_fournisseur) {
        this.id_fournisseur = id_fournisseur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getReferent() {
        return referent;
    }

    public void setReferent(String referent) {
        this.referent = referent;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Fournisseur(int id_fournisseur, String nom, String rue, String cp, String ville, String referent, String telephone) {
        this.id_fournisseur = id_fournisseur;
        this.nom = nom;
        this.rue = rue;
        this.cp = cp;
        this.ville = ville;
        this.referent = referent;
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Fournisseur" + ":" + nom +" " + rue +" " +  cp  +" " + ville  +" " +  referent +" " +  telephone ;

    }

    private int id_fournisseur;

    private String nom;

    private String rue;

    private String cp;

    private String ville;

    private String referent;

    private String telephone;




}
