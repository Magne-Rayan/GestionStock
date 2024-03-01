package com.gestionstock.gestionstock.entity;

public class Matiere {
    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    private String matiere;

    public Matiere(String nom){
        this.matiere = nom;

    }
    public String toString(){
        return matiere;
    }
}
