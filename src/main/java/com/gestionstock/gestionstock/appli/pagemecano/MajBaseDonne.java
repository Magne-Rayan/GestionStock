package com.gestionstock.gestionstock.appli.pagemecano;

import com.gestionstock.gestionstock.HelloApplication;
import com.gestionstock.gestionstock.entity.Forme;
import com.gestionstock.gestionstock.entity.Materiaux;
import com.gestionstock.gestionstock.entity.Matiere;
import com.gestionstock.gestionstock.entity.TypeForme;
import com.gestionstock.gestionstock.sql.ConnexionBdd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class MajBaseDonne implements Initializable {


    @FXML
    private ChoiceBox<Forme> dimention;

    @FXML
    private ChoiceBox<TypeForme> forme;

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

    @FXML
    private Button retour;

    @FXML
    private Button vider;

    @FXML
    void bouttonRetour(ActionEvent event){
        HelloApplication.changeScene("pageMecano","Page Mecano");
    }

    @FXML
    void vider(){
        forme.setValue(null);
        dimention.setValue(null);
        matiere.setValue(null);
        image.setImage(null);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Matiere matiere1 = new Matiere();
        //stockTotal.setText(String.valueOf(matiere1.afficher()));

        ConnexionBdd connexionBdd = new ConnexionBdd();
        Connection connection = connexionBdd.getBdd();
        String sql = "SELECT diametre, hauteur,largeur,coteSurPlat,epaisseur,ref_typeforme FROM forme";

        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            ResultSet resultatRequette = requetePrepare.executeQuery();
            while (resultatRequette.next()) {
                float diametre = resultatRequette.getFloat("diametre");
                float hauteur = resultatRequette.getFloat("hauteur");
                float largeur = resultatRequette.getFloat("largeur");
                float coteSurPlat = resultatRequette.getFloat("coteSurPlat");
                float epaisseur = resultatRequette.getFloat("epaisseur");
                int typeforme = resultatRequette.getInt("ref_typeforme");
                dimention.getItems().add(new Forme(diametre,largeur,coteSurPlat,hauteur,epaisseur));




            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Connection connection1 = connexionBdd.getBdd();
        String sql1 = "SELECT * FROM materiaux";

        try{
            PreparedStatement requete = connection1.prepareStatement(sql1);
            ResultSet resultatRequette = requete.executeQuery();
            while (resultatRequette.next()) {
                String nom = resultatRequette.getString("nom");
                int id = resultatRequette.getInt("id_materiaux");
                this.matiere.getItems().add(new Materiaux(nom,id));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql2 = "SELECT * FROM typeforme";

        try{
            PreparedStatement requete = connection.prepareStatement(sql2);
            ResultSet resultatRequette = requete.executeQuery();
            while (resultatRequette.next()) {
                int idTypeForme = resultatRequette.getInt("id_typeforme");
                String nom = resultatRequette.getString("nom");
                Blob image = resultatRequette.getBlob("image");
                forme.getItems().add(new TypeForme(idTypeForme,nom,image));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }


    @FXML
    void clickFormeEvent(MouseEvent event) throws SQLException {
        System.out.println("test*");
        if(this.forme.getValue() != null){
            image.setImage(new Image(this.forme.getValue().getImage().getBinaryStream()));
            String sql3 = "SELECT diametre, hauteur,largeur,coteSurPlat,epaisseur FROM forme WHERE  ref_typeforme = ?";
            try {
                ConnexionBdd connection = new ConnexionBdd();
                Connection connection1 = connection.getBdd();
                PreparedStatement requetePrepare = connection1.prepareStatement(sql3);
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
    void clickDimentionEvent(MouseEvent event) throws SQLException {
        if(this.dimention.getValue() != null){
            String sql4 = "SELECT longueur FROM matiere WHERE ref_materiaux = ? and ref_forme = ?";
            try {
                ConnexionBdd connection = new ConnexionBdd();
                Connection connection1 = connection.getBdd();
                PreparedStatement requetePrepare = connection1.prepareStatement(sql4);
                System.out.println(matiere.getValue().getIdMateriaux());
                System.out.println(forme.getValue().getIdTypeForme());
                requetePrepare.setInt(1,this.matiere.getValue().getIdMateriaux());
                requetePrepare.setInt(2,this.forme.getValue().getIdTypeForme());
                ResultSet resultatRequette = requetePrepare.executeQuery();
                while (resultatRequette.next()) {
                    float longeur = resultatRequette.getFloat("longueur");
                    stockTotal.setText(String.valueOf(longeur));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
