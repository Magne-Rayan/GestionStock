package com.gestionstock.gestionstock.appli.pagemecano;

import com.gestionstock.gestionstock.HelloApplication;
import com.gestionstock.gestionstock.entity.TypeForme;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class DmdPrix {

    @FXML
    private Button Retour;

    @FXML
    private TextField demandeur;

    @FXML
    private ImageView image;

    @FXML
    private TextField diametre;

    @FXML
    private ChoiceBox<?> forme;

    @FXML
    private TextField hauteur;

    @FXML
    private TextField largeur;

    @FXML
    private TextField longueur;

    @FXML
    private TextField quantite;

    @FXML
    void bouttonRetour(ActionEvent event) {
        HelloApplication.changeScene("pageMecano", "Page Mecano");
    }

}
