package com.gestionstock.gestionstock;

import com.gestionstock.gestionstock.sql.ConnexionBdd;
import com.gestionstock.gestionstock.sql.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

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
            Utilisateur user =  Utilisateur.connexion(identifiant.getText(),mdp.getText(),messageCo);
            HelloApplication.setUser(user);
            System.out.println(user.getId());
            if(user.getRole()==1){
                HelloApplication.changeScene("pageAdmin");
            } else if (user.getRole()==2) {
                
            } else if (user.getRole()==3) {
                
            }

        }
    }







}

