package com.gestionstock.gestionstock.pageadmin;

import com.gestionstock.gestionstock.HelloApplication;
import com.gestionstock.gestionstock.sql.ConnexionBdd;
import com.gestionstock.gestionstock.sql.Utilisateur;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PageAdmin {
    @FXML
    private TextField idText;

    @FXML
    private Button clear;

    @FXML
    private TableColumn<Utilisateur, String> identifiantUser;

    @FXML
    private Button ajouter;

    @FXML
    private TableColumn<Utilisateur, Integer> idUser;

    @FXML
    private TextField identifiant;

    @FXML
    private TextField mdp;

    @FXML
    private TableColumn<Utilisateur, String> mdpUser;

    @FXML
    private Button modifier;

    @FXML
    private TextField nom;

    @FXML
    private Label nomAdmin;

    @FXML
    private TableColumn<Utilisateur, String> nomUser;

    @FXML
    private TextField prenom;

    @FXML
    private TableColumn<Utilisateur, String> prenomUser;

    @FXML
    private MenuButton role;

    @FXML
    private TableColumn<Utilisateur, Integer> roleUser;

    @FXML
    private Button supprimer;

    @FXML
    private TableView<Utilisateur> tableau;

    @FXML
    private TextField rechercher;

    ObservableList<Utilisateur> list;
    ObservableList<Utilisateur> dataList;
    int index = -1;

    @FXML
    void messageAdmin() {
        nomAdmin.setText("Session "+ HelloApplication.getUser().getNom() + HelloApplication.getUser().getPrenom());

    }

    @FXML
    void ajoutUtilisateur() {
        ConnexionBdd connexionBdd = new ConnexionBdd();
        Connection connection = connexionBdd.getBdd();
        String sql = "INSERT INTO utilisateur (nom,prenom,identifiant,mdp,ref_role) VALUES (?,?,?,?,?)";

        try{
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            requetePrepare.setString(1,nom.getText());
            requetePrepare.setString(2,prenom.getText());
            requetePrepare.setString(3,identifiant.getText());
            requetePrepare.setString(4,mdp.getText());
            requetePrepare.setString(5,role.getText());
            rechercher();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    public void initialiser(){
        idUser.setCellValueFactory(new PropertyValueFactory<Utilisateur,Integer>("id"));
        nomUser.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("nom"));
        prenomUser.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("prenom"));
        identifiantUser.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("identif"));
        mdpUser.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("mdP"));
        roleUser.setCellValueFactory(new PropertyValueFactory<Utilisateur,Integer>("role"));

        list = ConnexionBdd.recupererUtilisateur();
        tableau.setItems(list);
        rechercher();

    }
    @FXML
    void setMenuBouton (){
        ConnexionBdd connexionBdd = new ConnexionBdd();
        Connection connection = connexionBdd.getBdd();
        String sql = "SELECT fonction FROM role";

        try{
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            ResultSet resultatRequette = requetePrepare.executeQuery();
            while (resultatRequette.next()){
                String nom = resultatRequette.getString("fonction");
                MenuItem menuItem = new MenuItem(nom);
                role.getItems().add(menuItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void getSelected(javafx.scene.input.MouseEvent mouseEvent) {
        index = tableau.getSelectionModel().getSelectedIndex();
        if(index <= -1){
            return;
        }
        idText.setText(idUser.getCellData(index).toString());
        nom.setText(nomUser.getCellData(index).toString());
        prenom.setText(prenomUser.getCellData(index).toString());
        identifiant.setText(identifiantUser.getCellData(index).toString());
        mdp.setText(mdpUser.getCellData(index).toString());
        //role.setText(roleUser.getCellData(index).toString());

    }

    public void modifier(){
        ConnexionBdd connexionBdd = new ConnexionBdd();
        Connection connection = connexionBdd.getBdd();
        String sql = "UPDATE utilisateur  SET id_utilisateur =?, nom = ?, prenom = ?, identifiant = ?, mdp = ? , role = ?";

        try{
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            requetePrepare.setString(1,nom.getText());
            requetePrepare.setString(2,prenom.getText());
            requetePrepare.setString(3,identifiant.getText());
            requetePrepare.setString(4,mdp.getText());
            requetePrepare.setString(5,role.getText());
            ResultSet resultatRequette = requetePrepare.executeQuery();
            rechercher();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    void rechercher(){
        idUser.setCellValueFactory(new PropertyValueFactory<Utilisateur,Integer>("id"));
        nomUser.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("nom"));
        prenomUser.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("prenom"));
        identifiantUser.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("identif"));
        mdpUser.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("mdP"));
        roleUser.setCellValueFactory(new PropertyValueFactory<Utilisateur,Integer>("role"));

        dataList = ConnexionBdd.recupererUtilisateur();
        tableau.setItems(dataList);
        FilteredList<Utilisateur> filteredData = new FilteredList<>(dataList, b -> true);
        rechercher.textProperty().addListener((observableValue, oldValue, newValue) ->{
            filteredData.setPredicate(utilisateur -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if(utilisateur.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                } else if (utilisateur.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                else{
                    return false;
                }           });
        } );
        SortedList<Utilisateur> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableau.comparatorProperty());
        tableau.setItems(sortedData);
    }
    @FXML
     void vider(){
        idText.setText("");
        prenom.setText("");
        nom.setText("");
        identifiant.setText("");
        mdp.setText("");
        role.setText("");
    }
}