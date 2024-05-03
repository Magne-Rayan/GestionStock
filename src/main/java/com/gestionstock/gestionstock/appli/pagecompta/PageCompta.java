package com.gestionstock.gestionstock.appli.pagecompta;

import com.gestionstock.gestionstock.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PageCompta {
    @FXML
    void bouttonJaune(ActionEvent event) {
        HelloApplication.changeScene("etatStock", "Etats des Stocks");}

}
