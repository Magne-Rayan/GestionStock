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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class MajBaseDonne implements Initializable {



    @FXML
    private TextField modifStock;

    @FXML
    private Button retour;

    @FXML
    private TableView<?> tableau;

    @FXML
    private Button valider;

    @FXML
    private Button vider;

    @FXML
    private ComboBox<TypeForme> forme;

    @FXML
    void bouttonRetour(ActionEvent event){
        HelloApplication.changeScene("pageMecano","Page Mecano");
    }

    @FXML
    void vider(){


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        ConnexionBdd connexionBdd = new ConnexionBdd();
        Connection connection = connexionBdd.getBdd();


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

    }



}
