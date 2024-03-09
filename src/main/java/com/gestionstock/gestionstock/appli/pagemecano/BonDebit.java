package com.gestionstock.gestionstock.appli.pagemecano;

import com.gestionstock.gestionstock.HelloApplication;
import com.gestionstock.gestionstock.entity.*;
import com.gestionstock.gestionstock.sql.ConnexionBdd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class BonDebit  implements Initializable {
    @FXML
    private ComboBox<Classe> classe;

    @FXML
    private ComboBox<Forme> dimention;

    @FXML
    private ComboBox<Filiere> filiere;

    @FXML
    private ComboBox<TypeForme> forme;

    @FXML
    private ImageView imageSysteme;

    @FXML
    private ImageView logo;

    @FXML
    private ComboBox<Utilisateur> nom;

    @FXML
    private ComboBox<Piece> piece;

    @FXML
    private TextField quantite;

    @FXML
    private Button retourArriere;

    @FXML
    private TextField stockFinal;

    @FXML
    private TextField stockInitial;

    @FXML
    private ComboBox<Systeme> systeme;

    @FXML
    private TextField totalDebiter;

    @FXML
    private Button valider;

    @FXML
    private Button vider;

    @FXML
    private ImageView visuelPiece;

    @FXML
    void vider(){
        nom.setValue(null);
        forme.setValue(null);
        dimention.setValue(null);
        classe.setValue(null);
        filiere.setValue(null);
        systeme.setValue(null);
        piece.setValue(null);
        quantite.setText("");
        totalDebiter.setText(null);
        stockInitial.setText(null);
        stockFinal.setText(null);

    }

    @FXML
    void bouttonRetour(ActionEvent event){
        HelloApplication.changeScene("pageMecano","Page Mecano");
    }

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
                forme.getItems().add(new TypeForme(idTypeForme, nom, image));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql1 = "SELECT diametre, hauteur,largeur,coteSurPlat,epaisseur,ref_typeforme FROM forme";

        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql1);
            ResultSet resultatRequette = requetePrepare.executeQuery();
            while (resultatRequette.next()) {
                float diametre = resultatRequette.getFloat("diametre");
                float hauteur = resultatRequette.getFloat("hauteur");
                float largeur = resultatRequette.getFloat("largeur");
                float coteSurPlat = resultatRequette.getFloat("coteSurPlat");
                float epaisseur = resultatRequette.getFloat("epaisseur");
                int typeforme = resultatRequette.getInt("ref_typeforme");
                dimention.getItems().add(new Forme(diametre, largeur, coteSurPlat, hauteur, epaisseur));


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql2 = "SELECT * FROM filiere";

        try {
            PreparedStatement requete = connection.prepareStatement(sql2);
            ResultSet resultatRequette = requete.executeQuery();
            while (resultatRequette.next()) {
                int id = resultatRequette.getInt("id_filiere");
                String nom = resultatRequette.getString("nom");
                filiere.getItems().add(new Filiere(id, nom));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql3 = "SELECT nom FROM utilisateur WHERE ref_role in(SELECT id_role FROM role WHERE fonction ='Mecanitien')";
        try {
            PreparedStatement requete = connection.prepareStatement(sql3);
            ResultSet resultatRequette = requete.executeQuery();
            while (resultatRequette.next()) {
                String nom = resultatRequette.getString("nom");
                this.nom.getItems().add( new Utilisateur(nom));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql4 = "SELECT * FROM systeme ";

        try{
            PreparedStatement requete = connection.prepareStatement(sql4);
            ResultSet resultatRequette = requete.executeQuery();
            while (resultatRequette.next()) {
                String nom = resultatRequette.getString("nom");
                int id = resultatRequette.getInt("id_systeme");
                Blob image = resultatRequette.getBlob("img");
                this.systeme.getItems().add(new Systeme(nom,id,image));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql5 = "SELECT nom,img FROM piece ";

        try{
            PreparedStatement requete = connection.prepareStatement(sql5);
            ResultSet resultatRequette = requete.executeQuery();
            while (resultatRequette.next()) {
                String nom = resultatRequette.getString("nom");
                Blob image = resultatRequette.getBlob("img");
                this.piece.getItems().add(new Piece(nom,image));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void clickFiliereEvent(ActionEvent event) throws SQLException {
        if(this.filiere.getValue() != null){
            String sql4 = "SELECT nom FROM classe WHERE ref_filiere = ? ";
            try {
                ConnexionBdd connection = new ConnexionBdd();
                Connection connection1 = connection.getBdd();
                PreparedStatement requetePrepare = connection1.prepareStatement(sql4);
                requetePrepare.setInt(1,this.filiere.getValue().getId_filiere());
                ResultSet resultatRequette = requetePrepare.executeQuery();
                while (resultatRequette.next()) {
                    String nom =resultatRequette.getString("nom");
                    classe.getItems().add(new Classe(nom));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @FXML
    void clickSystemeEvent(ActionEvent event) throws SQLException {
        if(this.systeme.getValue() != null){
            imageSysteme.setImage(new Image(this.systeme.getValue().getImage().getBinaryStream()));
        }
    }

    @FXML
    void clickPieceEvent(ActionEvent event) throws SQLException {
        if(this.piece.getValue() != null){
            visuelPiece.setImage(new Image(this.piece.getValue().getImage().getBinaryStream()));
        }
    }



    }
