package com.gestionstock.gestionstock.entity;

public class Materiaux {
    public int getIdMateriaux() {
        return idMateriaux;
    }

    public void setIdMateriaux(int idMateriaux) {
        this.idMateriaux = idMateriaux;
    }

    private int idMateriaux;
    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    private String matiere;

    public Materiaux(String nom,int idMateriaux){
        this.matiere = nom;
        this.idMateriaux = idMateriaux;

    }
    public String toString(){
        return matiere;
    }

}
