package com.gestionstock.gestionstock.appli.pagemecano;

import com.gestionstock.gestionstock.HelloApplication;
import com.gestionstock.gestionstock.entity.Forme;
import com.gestionstock.gestionstock.entity.Materiaux;
import com.gestionstock.gestionstock.entity.TypeForme;
import com.gestionstock.gestionstock.sql.ConnexionBdd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Ajout implements Initializable {
    @FXML
    private TextField coteSurPlat;

    @FXML
    private TextField diametre;

    @FXML
    private Label getId;

    @FXML
    private ComboBox<Forme> dimention;

    @FXML
    private TextField epaisseur;

    @FXML
    private TextField hauteur;

    @FXML
    private TextField largeur;

    @FXML
    private ComboBox<Materiaux> mat;

    @FXML
    private ComboBox<TypeForme> nomForme;

    @FXML
    private ComboBox<TypeForme> nomForme1;

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
                nomForme1.getItems().add(new TypeForme(idTypeForme, nom, image));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Connection connection1 = connexionBdd.getBdd();
        String sql6 = "SELECT * FROM materiaux";

        try{
            PreparedStatement requete = connection1.prepareStatement(sql6);
            ResultSet resultatRequette = requete.executeQuery();
            while (resultatRequette.next()) {
                String nom = resultatRequette.getString("nom");
                int id = resultatRequette.getInt("id_materiaux");
                this.mat.getItems().add(new Materiaux(nom,id));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void bouttonRetour(ActionEvent event) {
        if(HelloApplication.getUser().getRole()== 1 ){
            HelloApplication.changeScene("menuAdmin","Menu Admin");
        }else if(HelloApplication.getUser().getRole()== 2 ){
            HelloApplication.changeScene("pageMecano", "Page Mecano");
        }
    }
    @FXML
    void clickMateriauxEvent(ActionEvent event){
        if(nomForme.getValue() != null && dimention.getValue() != null){
            ConnexionBdd connectionBdd = new ConnexionBdd();
            Connection connection = connectionBdd.getBdd();

            String sql2 = "SELECT id_matiere FROM matiere WHERE ref_materiaux = ? and ref_forme = ?";
            try {
                PreparedStatement requetePrepare = connection.prepareStatement(sql2);
                System.out.println(mat.getValue().getIdMateriaux());
                System.out.println(nomForme.getValue().getIdTypeForme());
                requetePrepare.setInt(1, this.mat.getValue().getIdMateriaux());
                requetePrepare.setInt(2, this.nomForme.getValue().getIdTypeForme());
                ResultSet resultatRequette = requetePrepare.executeQuery();
                while (resultatRequette.next()) {
                    int id = resultatRequette.getInt("id_matiere");
                    getId.setText(String.valueOf(id));

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void clickFormeEvent(ActionEvent event) throws SQLException {
        if(this.nomForme.getValue() != null){
            String sql = "SELECT diametre, hauteur,largeur,coteSurPlat,epaisseur FROM forme WHERE  ref_typeforme = ?";
            try {
                ConnexionBdd connection = new ConnexionBdd();
                Connection connection1 = connection.getBdd();
                PreparedStatement requetePrepare = connection1.prepareStatement(sql);
                requetePrepare.setInt(1,this.nomForme.getValue().getIdTypeForme());
                ResultSet resultatRequette = requetePrepare.executeQuery();
                while (resultatRequette.next()) {
                    float diametre = resultatRequette.getFloat("diametre");
                    float hauteur = resultatRequette.getFloat("hauteur");
                    float largeur = resultatRequette.getFloat("largeur");
                    float coteSurPlat = resultatRequette.getFloat("coteSurPlat");
                    float epaisseur = resultatRequette.getFloat("epaisseur");
                    dimention.getItems().add(new Forme(diametre,largeur,coteSurPlat,hauteur,epaisseur));

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void ajoutMatiere(ActionEvent event){
        ConnexionBdd connexionBdd = new ConnexionBdd();
        Connection connection = connexionBdd.getBdd();

        String sql1 = "INSERT INTO matiere (longueur,ref_materiaux,ref_forme) VALUES (?,?,?) ";

        try {
            PreparedStatement requete = connection.prepareStatement(sql1);
            requete.setFloat(1, Float.parseFloat(nomMat.getText()));
            requete.setInt(2,mat.getValue().getIdMateriaux());
            requete.setInt(3, Integer.parseInt(getId.getText()));
            requete.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void ajoutForme(ActionEvent event){
        ConnexionBdd connexionBdd = new ConnexionBdd();
        Connection connection = connexionBdd.getBdd();

        String sql1 = "INSERT INTO forme (coteSurPlat,epaisseur,hauteur,diametre,largeur,ref_typeforme) VALUES (?,?,?,?,?,?) ";
        try {
            PreparedStatement requete = connection.prepareStatement(sql1);
            requete.setFloat(1, Float.parseFloat(coteSurPlat.getText()));
            requete.setFloat(2,Float.parseFloat(epaisseur.getText()));
            requete.setFloat(3, Float.parseFloat(hauteur.getText()));
            requete.setInt(4, Integer.parseInt(diametre.getText()));
            requete.setFloat(5, Float.parseFloat(largeur.getText()));
            requete.setInt(6, nomForme1.getValue().getIdTypeForme());
            requete.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}

