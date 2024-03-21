package com.gestionstock.gestionstock.appli.pagemecano;

import com.gestionstock.gestionstock.HelloApplication;
import com.gestionstock.gestionstock.entity.*;
import com.gestionstock.gestionstock.sql.ConnexionBdd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
    private ComboBox<Materiaux> materiaux;

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
    private Label message;

    @FXML
    private Label getId;

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
        materiaux.setValue(null);
        quantite.setText("");
        totalDebiter.setText(null);
        stockInitial.setText(null);
        stockFinal.setText(null);
        imageSysteme.setImage(null);
        visuelPiece.setImage(null);

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

        String sql3 = "SELECT id_utilisateur, nom FROM utilisateur WHERE ref_role in(SELECT id_role FROM role WHERE fonction ='Mecanitien')";
        try {
            PreparedStatement requete = connection.prepareStatement(sql3);
            ResultSet resultatRequette = requete.executeQuery();
            while (resultatRequette.next()) {
                int id = resultatRequette.getInt("id_utilisateur");
                String nom = resultatRequette.getString("nom");
                this.nom.getItems().add( new Utilisateur(nom,id));
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


        Connection connection1 = connexionBdd.getBdd();
        String sql6 = "SELECT * FROM materiaux";

        try{
            PreparedStatement requete = connection1.prepareStatement(sql6);
            ResultSet resultatRequette = requete.executeQuery();
            while (resultatRequette.next()) {
                String nom = resultatRequette.getString("nom");
                int id = resultatRequette.getInt("id_materiaux");
                this.materiaux.getItems().add(new Materiaux(nom,id));
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
            Systeme system = systeme.getValue();
            imageSysteme.setImage(new Image(systeme.getValue().getImage().getBinaryStream()));
            String sql4 = "SELECT * FROM piece WHERE ref_systeme = ? ";
            try {
                ConnexionBdd connection = new ConnexionBdd();
                Connection connection1 = connection.getBdd();
                PreparedStatement requetePrepare = connection1.prepareStatement(sql4);
                requetePrepare.setInt(1,systeme.getValue().getIdSysteme());
                ResultSet resultatRequette = requetePrepare.executeQuery();
                while (resultatRequette.next()) {
                    int id = resultatRequette.getInt("id_piece");
                    String nom = resultatRequette.getString("nom");
                    Blob image = resultatRequette.getBlob("img");
                    float longueur = resultatRequette.getFloat("longueur");
                    this.piece.getItems().add(new Piece(nom,image,longueur,id));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void clickPieceEvent(ActionEvent event) throws SQLException {
        if(this.piece.getValue() != null){
            visuelPiece.setImage(new Image(this.piece.getValue().getImage().getBinaryStream()));
        }
    }

    @FXML
    void clickQuantiteEvent(ActionEvent event) throws SQLException {
        if(this.quantite.getText() != null){
            totalDebiter.setText(piece.getValue().getLongeur()*Integer.valueOf(quantite.getText())+"");
            float stockInitialValue = Float.valueOf(stockInitial.getText());
            float totalDebiterValue = Float.valueOf(totalDebiter.getText());
            float resultat = stockInitialValue - totalDebiterValue;
            stockFinal.setText(Float.valueOf(resultat)+"");
        }
    }

    @FXML
    void clickFormeEvent(ActionEvent event) throws SQLException {
        if(this.forme.getValue() != null){
            String sql = "SELECT diametre, hauteur,largeur,coteSurPlat,epaisseur FROM forme WHERE  ref_typeforme = ?";
            try {
                ConnexionBdd connection = new ConnexionBdd();
                Connection connection1 = connection.getBdd();
                PreparedStatement requetePrepare = connection1.prepareStatement(sql);
                requetePrepare.setInt(1,this.forme.getValue().getIdTypeForme());
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
    void clickDimentionEvent(ActionEvent event) throws SQLException {
        if(this.dimention.getValue() != null){
            String sql4 = "SELECT longueur FROM matiere WHERE ref_materiaux = ? and ref_forme = ?";
            try {
                ConnexionBdd connection = new ConnexionBdd();
                Connection connection1 = connection.getBdd();
                PreparedStatement requetePrepare = connection1.prepareStatement(sql4);
                System.out.println(materiaux.getValue().getIdMateriaux());
                System.out.println(forme.getValue().getIdTypeForme());
                requetePrepare.setInt(1,this.materiaux.getValue().getIdMateriaux());
                requetePrepare.setInt(2,this.forme.getValue().getIdTypeForme());
                ResultSet resultatRequette = requetePrepare.executeQuery();
                while (resultatRequette.next()) {
                    float longeur = resultatRequette.getFloat("longueur");
                    stockInitial.setText(String.valueOf(longeur));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            String sql = "SELECT id_matiere FROM matiere WHERE ref_materiaux = ? and ref_forme = ?";
            try {
                ConnexionBdd connection = new ConnexionBdd();
                Connection connection1 = connection.getBdd();
                PreparedStatement requetePrepare = connection1.prepareStatement(sql);
                System.out.println(materiaux.getValue().getIdMateriaux());
                System.out.println(forme.getValue().getIdTypeForme());
                requetePrepare.setInt(1, this.materiaux.getValue().getIdMateriaux());
                requetePrepare.setInt(2, this.forme.getValue().getIdTypeForme());
                ResultSet resultatRequette = requetePrepare.executeQuery();
                while (resultatRequette.next()) {
                    int id = resultatRequette.getInt("id_matiere");
                    message.setText("Matiere trouveé : ");
                    getId.setText(String.valueOf(id));

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void clickValider(ActionEvent event){
        ConnexionBdd connexionBdd = new ConnexionBdd();
        Connection connection = connexionBdd.getBdd();
        String sql = "INSERT INTO bondebit (`quantite`, `longueurTotal`, `ref_filiere`, `ref_piece`, `ref_matiere`) " +
                "VALUES (?,?,?,?,?) ";

        try {
            PreparedStatement requete = connection.prepareStatement(sql);
            requete.setInt(1, Integer.valueOf(this.quantite.getText()));
            requete.setFloat(2, Float.valueOf(this.totalDebiter.getText()));
            requete.setInt(3,this.filiere.getValue().getId_filiere());
            requete.setInt(4,this.piece.getValue().getIdPiece());
            requete.setInt(5, Integer.parseInt(getId.getText()));
            requete.executeUpdate();

    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String sql1 = "UPDATE matiere SET longueur= ? WHERE id_matiere = ?" ;
        try {
            PreparedStatement requete = connection.prepareStatement(sql1);
            requete.setFloat(1, Float.parseFloat(this.stockFinal.getText()));
            requete.setInt(2,Integer.parseInt(getId.getText()));
            requete.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Utilisateur utilisateur = nom.getValue();

        String sql2 = "INSERT INTO fait (ref_utilisateur, ref_debit) VALUES (?,?)" ;
        try {
            PreparedStatement requete = connection.prepareStatement(sql2);
            requete.setFloat(1, utilisateur.getId());
            //requete.setInt(2,);
            requete.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        vider();


    }
}
