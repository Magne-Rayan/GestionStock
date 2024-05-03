package com.gestionstock.gestionstock.appli.pagemecano;

import com.gestionstock.gestionstock.entity.Fournisseur;
import com.gestionstock.gestionstock.entity.Utilisateur;
import com.gestionstock.gestionstock.sql.ConnexionBdd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Commande implements Initializable {

    @FXML
    private ComboBox<Fournisseur> fournisseur;

    @FXML
    private TextField libelle;

    @FXML
    private TextField prixTotal;

    @FXML
    private ComboBox<Utilisateur> prof;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ConnexionBdd connexionBdd = new ConnexionBdd();
        Connection connection = connexionBdd.getBdd();
        String sql = "SELECT * FROM Fournisseur ";

        try {


            PreparedStatement requete = connection.prepareStatement(sql);

            ResultSet resultatrequete = requete.executeQuery();

            while (resultatrequete.next()) {
                int id_fournisseur = resultatrequete.getInt("id_fournisseur");
                String nom = resultatrequete.getString("nom");
                String rue = resultatrequete.getString("rue");
                String cp = resultatrequete.getString("cp");
                String ville = resultatrequete.getString("ville");
                String referent = resultatrequete.getString("referent");
                String telephone = resultatrequete.getString("telephone");
                fournisseur.getItems().add(new Fournisseur(id_fournisseur, nom, rue, cp, ville, referent, telephone));


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql3 = "SELECT id_utilisateur, nom FROM utilisateur WHERE ref_role in(SELECT id_role FROM role WHERE fonction ='Mecanitien')";
        try {
            PreparedStatement requete = connection.prepareStatement(sql3);
            ResultSet resultatRequette = requete.executeQuery();
            while (resultatRequette.next()) {
                int id = resultatRequette.getInt("id_utilisateur");
                String nom = resultatRequette.getString("nom");
                this.prof.getItems().add(new Utilisateur(nom, id));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void bouttonValider(ActionEvent event) {
        ConnexionBdd connexionBdd = new ConnexionBdd();
        Connection connection = connexionBdd.getBdd();
        String sql = "INSERT INTO commande (libelle,prixTotal,ref_utilisateur,ref_foutnisseur) VALUES (?,?,?,?) ";

        try {
            PreparedStatement requete = connection.prepareStatement(sql);
            requete.setInt(1, Integer.parseInt(libelle.getText()));
            requete.setFloat(2,Float.parseFloat(prixTotal.getText()));
            requete.setInt(3,fournisseur.getValue().getId_fournisseur());
            requete.setInt(4,prof.getValue().getId());
            requete.executeUpdate();
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
