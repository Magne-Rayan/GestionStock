package com.gestionstock.gestionstock.appli.pagemecano;

import com.gestionstock.gestionstock.HelloApplication;
import com.gestionstock.gestionstock.entity.Fournisseur;
import com.gestionstock.gestionstock.entity.TypeForme;
import com.gestionstock.gestionstock.sql.ConnexionBdd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DmdPrix implements Initializable {

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
    private ComboBox<Fournisseur> fournisseur;

    @FXML
    void bouttonRetour(ActionEvent event) {
        HelloApplication.changeScene("pageMecano", "Page Mecano");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ConnexionBdd connexionBdd = new ConnexionBdd();
        Connection connection = connexionBdd.getBdd();

        String sql = "SELECT * FROM Fournisseur ";

                try{

                    PreparedStatement requete = connection.prepareStatement(sql);

                    ResultSet resultatrequete = requete.executeQuery();

                    while (resultatrequete.next()){
                        int id_fournisseur =  resultatrequete.getInt("id_fournisseur");
                        String nom =  resultatrequete.getString("nom");
                        String rue  =  resultatrequete.getString("rue");
                        String cp =  resultatrequete.getString("cp");
                        String ville =  resultatrequete.getString("ville");
                        String referent =  resultatrequete.getString("referent");
                        String telephone =  resultatrequete.getString("telephone");
                        fournisseur.getItems().add(new Fournisseur(id_fournisseur,nom,rue,cp,ville,referent, telephone));

                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
    }
}