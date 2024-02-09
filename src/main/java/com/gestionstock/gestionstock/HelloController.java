package com.gestionstock.gestionstock;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class HelloController {

    @FXML
    private Button connexion;

    @FXML
    private TextField identifiant;

    @FXML
    private Pane pageConnexion;

    @FXML
    private ImageView logoLycee;

    @FXML
    private PasswordField mdp;

    @FXML
    private Label messageCo;

    @FXML
    void connexion(ActionEvent event){
        if(identifiant.getText().isBlank()  &&  mdp.getText().isBlank()){
            messageCo.setText("entrer L'identifiant et le mot de passe ");
        }
        else{
            validConnexion();
        }
    }
    @FXML
    void validConnexion(){
        ConnexionBdd connexionBdd = new ConnexionBdd();
        Connection connection = connexionBdd.getBdd();
        String sql = "SELECT identifiant,mdp FROM utilisateur WHERE identifiant = "+identifiant.getText()+ "AND mdp ="+mdp.getText();

        try{
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            ResultSet resultatRequette = requetePrepare.executeQuery(sql);
            if(resultatRequette.next()){
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pageAdmin.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("GestionStock-Menu");
                stage.setScene(scene);
                stage.show();


            }else {
                messageCo.setText("Erreur veuillez re essayer");
            }


        }catch (Exception e ){

        }
    }




}

