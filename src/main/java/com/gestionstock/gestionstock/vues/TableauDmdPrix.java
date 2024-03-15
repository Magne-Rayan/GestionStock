package com.gestionstock.gestionstock.vues;

public class TableauDmdPrix extends Tableau {

    @Override
    public float getLongueur() {
        return longueurdmd;
    }

    @Override
    public void setLongueur(float longueur) {
        this.longueurdmd = longueur;
    }

    public float getQuantité() {
        return quantite;
    }

    public void setQuantité(float quantité) {
        this.quantite= quantité;
    }

    private float longueurdmd;

    private float quantite;

    public TableauDmdPrix(Tableau t, float longeurdmd, float quantite) {
        super(t);

        this.longueurdmd = longeurdmd;
        this.quantite = quantite;


    }
}
