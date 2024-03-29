package com.gestionstock.gestionstock.vues;

import javafx.beans.property.SimpleStringProperty;

public class TableauDmdPrix extends Tableau {
    private String longueurdm;

    public String getLongueurdm() {
        return longueurdm;
    }

    public void setLongueurdm(String longueurdm) {
        this.longueurdm = longueurdm;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    private String quantite;

    public TableauDmdPrix(float diametre, float largeur, float hauteur, float coteSurPlat, float epaisseur, String nom, float longueur, String nomMat, int idTypeForme, int idMat, String longueurdm,String quantite) {
        super(diametre, largeur, hauteur, coteSurPlat, epaisseur, nom, longueur, nomMat, idTypeForme, idMat);
        this.longueurdm = longueurdm;
        this.quantite = quantite;
    }
}
