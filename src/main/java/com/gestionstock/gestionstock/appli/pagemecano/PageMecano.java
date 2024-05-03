package com.gestionstock.gestionstock.appli.pagemecano;

import com.gestionstock.gestionstock.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PageMecano {
    @FXML
    private Button bonCom;

    @FXML
    private Button debitMat;

    @FXML
    private Button etatStock;

    @FXML
    private Button maj;

    @FXML
    void bouttonRouge(ActionEvent event) {
        HelloApplication.changeScene("majBaseDonne", "Mise a jour base de donne");
    }

    @FXML
    void bouttonJaune(ActionEvent event) {
        HelloApplication.changeScene("bonDebit", "Bon de debit matière");
    }
    @FXML
    void bouttonBleu(ActionEvent event) {
        HelloApplication.changeScene("DmdPrix", "Demande de Prix");

    }
    @FXML
    void bouttonVert(ActionEvent event) {
        HelloApplication.changeScene("etatStock", "Etats des Stocks");

    }

    @FXML
    void bouttonBleu2(ActionEvent event) {
        HelloApplication.changeScene("commande", "Commande");

    }

    @FXML
    void bouttonAjout(ActionEvent event) {
        HelloApplication.changeScene("ajout", "Ajout");

    }
    @FXML
    void deconnexion(ActionEvent event) {
        HelloApplication.changeScene("hello-view","Gestion de stock!");
    }
}
