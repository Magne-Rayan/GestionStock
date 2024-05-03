package com.gestionstock.gestionstock.appli.pagemecano;

import com.gestionstock.gestionstock.entity.Forme;
import com.gestionstock.gestionstock.entity.Materiaux;
import com.gestionstock.gestionstock.entity.TypeForme;
import com.gestionstock.gestionstock.sql.ConnexionBdd;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Ajout implements Initializable {
    @FXML
    private ComboBox<Forme> dimention;

    @FXML
    private ComboBox<Materiaux> mat;

    @FXML
    private ComboBox<TypeForme> nomForme;

    @FXML
    private TextField nomMat;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ConnexionBdd connexionBdd = new ConnexionBdd();
        Connection connection = connexionBdd.getBdd();
        String sql = "SELECT * FROM typeforme";

        try {
            PreparedStatement requete = connection.prepareStatement(sql);
            ResultSet resultatRequette = requete.executeQuery();
            while (resultatRequette.next()) {
                int idTypeForme = resultatRequette.getInt("id_typeforme");
                String nom = resultatRequette.getString("nom");
                Blob image = resultatRequette.getBlob("image");
                nomForme.getItems().add(new TypeForme(idTypeForme, nom, image));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
