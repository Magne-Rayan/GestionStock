package com.gestionstock.gestionstock.entity;

public class Materiaux {
    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    private String matiere;

    public Materiaux(String nom){
        this.matiere = nom;

    }
    public String toString(){
        return matiere;
    }
}
