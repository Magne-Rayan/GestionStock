package com.gestionstock.gestionstock.appli.pagemecano;

import com.gestionstock.gestionstock.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class etatDesStocks {
    @FXML
    void bouttonRetour(ActionEvent event){
        HelloApplication.changeScene("pageMecano","Page Mecano");
    }
}
