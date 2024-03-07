package com.gestionstock.gestionstock.appli.pageacceuil;

import com.gestionstock.gestionstock.HelloApplication;
import com.gestionstock.gestionstock.entity.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;


public class HelloController   {

    @FXML
    private Button connexion;

    @FXML
    private TextField identifiant;

    @FXML
    private Pane pageConnexion;

    @FXML
    public ImageView logoLycee;

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
                HelloApplication.changeScene("pageAdmin","Page Admin");
            } else if (user.getRole()==2) {
                HelloApplication.changeScene("pageMecano","Page Mecano");
            } else if (user.getRole()==3) {
                HelloApplication.changeScene("pageCompta","Page Comptabilité");
            }

        }
    }



}

