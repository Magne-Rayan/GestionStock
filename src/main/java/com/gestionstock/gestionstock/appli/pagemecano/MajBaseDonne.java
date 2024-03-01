package com.gestionstock.gestionstock.appli.pagemecano;

import com.gestionstock.gestionstock.entity.Forme;
import com.gestionstock.gestionstock.entity.Materiaux;
import com.gestionstock.gestionstock.sql.ConnexionBdd;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MajBaseDonne implements Initializable {

    @FXML
    private ChoiceBox<?> dimention;

    @FXML
    private ChoiceBox<Forme> forme;

    @FXML
    private ImageView image;

    @FXML
    private ChoiceBox<Materiaux> matiere;

    @FXML
    private TextField stockTotal;

    @FXML
    private TextField stockUtiliser;

    @FXML
    private Button valider;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ConnexionBdd connexionBdd = new ConnexionBdd();
        Connection connection = connexionBdd.getBdd();
        String sql = "SELECT nom FROM forme";

        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            ResultSet resultatRequette = requetePrepare.executeQuery();
            while (resultatRequette.next()) {
                String nom = resultatRequette.getString("nom");
                forme.getItems().add(new Forme(nom));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ConnexionBdd connexionBdd1 = new ConnexionBdd();
        Connection connection1 = connexionBdd.getBdd();
        String sql1 = "SELECT nom FROM materiaux";

        try{
            PreparedStatement requete = connection1.prepareStatement(sql1);
            ResultSet resultatRequette = requete.executeQuery();
            while (resultatRequette.next()) {
                String nom = resultatRequette.getString("nom");
                matiere.getItems().add(new Materiaux(nom));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



}