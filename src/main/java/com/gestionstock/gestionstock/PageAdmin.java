package com.gestionstock.gestionstock;

import com.gestionstock.gestionstock.sql.ConnexionBdd;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PageAdmin {
    @FXML
    private Label nomAdmin;

    @FXML
    void messageAdmin() {
        System.out.println(HelloApplication.getUser().getNom());
    }
}
