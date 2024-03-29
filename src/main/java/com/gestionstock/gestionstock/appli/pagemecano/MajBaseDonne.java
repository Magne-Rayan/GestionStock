package com.gestionstock.gestionstock.appli.pagemecano;

import com.gestionstock.gestionstock.HelloApplication;
import com.gestionstock.gestionstock.entity.*;
import com.gestionstock.gestionstock.sql.ConnexionBdd;
import com.gestionstock.gestionstock.vues.Tableau;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class MajBaseDonne implements Initializable {


    @FXML
    private TableColumn<Tableau, Float> coteSurPlat;

    @FXML
    private TableColumn<Tableau, Float> diametre;

    @FXML
    private TableColumn<Tableau, Float> epaisseur;

    @FXML
    private ComboBox<TypeForme> forme;

    @FXML
    private TableColumn<Tableau, Float> hauteur;

    @FXML
    private ImageView imageForme;

    @FXML
    private TableColumn<Tableau, Float> largeur;

    @FXML
    private TableColumn<Tableau, Float> longueur;

    @FXML
    private TextField modifStock;

    @FXML
    private TableColumn<Tableau, String> nom;

    @FXML
    private TableColumn<Tableau, String> nomMat;

    @FXML
    private Button retour;

    @FXML
    private Label getId;

    @FXML
    private TableView<Tableau> tableau;

    @FXML
    private Button valider;

    @FXML
    private Button vider;

    ObservableList<Tableau> list;
    int index = -1;


    @FXML
    void bouttonRetour(ActionEvent event) {
        HelloApplication.changeScene("pageMecano", "Page Mecano");
    }

    @FXML
    void vider() {
        modifStock.setText("");
        imageForme.setImage(null);
    }

    public void getSelected(javafx.scene.input.MouseEvent mouseEvent) {
        Tableau t = tableau.getSelectionModel().getSelectedItem();

        modifStock.setText(String.valueOf(t.getLongueur()));
        getId.setText(String.valueOf(t.getIdMat()));

        ConnexionBdd connexionBdd = new ConnexionBdd();
        Connection connection = connexionBdd.getBdd();


        String sql2 = "SELECT * FROM typeforme WHERE id_typeforme =?";

        try {
            PreparedStatement requete = connection.prepareStatement(sql2);
            requete.setInt(1, t.getId());
            ResultSet resultatRequette = requete.executeQuery();
            while (resultatRequette.next()) {
                int idTypeForme = resultatRequette.getInt("id_typeforme");
                String nom = resultatRequette.getString("nom");
                Blob image = resultatRequette.getBlob("image");
                TypeForme forme1 = new TypeForme(idTypeForme, nom, image);
                imageForme.setImage(new Image(forme1.getImage().getBinaryStream()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    public void initialiser() {
        diametre.setCellValueFactory(new PropertyValueFactory<Tableau, Float>("diametre"));
        hauteur.setCellValueFactory(new PropertyValueFactory<Tableau, Float>("hauteur"));
        largeur.setCellValueFactory(new PropertyValueFactory<Tableau, Float>("largeur"));
        coteSurPlat.setCellValueFactory(new PropertyValueFactory<Tableau, Float>("coteSurPlat"));
        epaisseur.setCellValueFactory(new PropertyValueFactory<Tableau, Float>("epaisseur"));
        nom.setCellValueFactory(new PropertyValueFactory<Tableau, String>("nom"));
        longueur.setCellValueFactory(new PropertyValueFactory<Tableau, Float>("longueur"));
        nomMat.setCellValueFactory(new PropertyValueFactory<Tableau, String>("nomMat"));


        list = ConnexionBdd.recupFormes();
        tableau.setItems(list);


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialiser();
    }

    @FXML
    void clickValider(ActionEvent event) {
        ConnexionBdd connexionBdd = new ConnexionBdd();
        Connection connection = connexionBdd.getBdd();
        String sql1 = "UPDATE matiere SET longueur= ? WHERE id_matiere = ?";
        try {
            PreparedStatement requete = connection.prepareStatement(sql1);
            requete.setFloat(1, Float.parseFloat(this.modifStock.getText()));
            requete.setInt(2, Integer.parseInt(getId.getText()));
            requete.executeUpdate();
            initialiser();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}

