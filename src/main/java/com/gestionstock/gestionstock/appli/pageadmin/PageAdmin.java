package com.gestionstock.gestionstock.appli.pageadmin;

import com.gestionstock.gestionstock.HelloApplication;
import com.gestionstock.gestionstock.entity.Role;
import com.gestionstock.gestionstock.sql.ConnexionBdd;
import com.gestionstock.gestionstock.entity.Utilisateur;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PageAdmin implements Initializable {
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
    @FXML
    private ChoiceBox<Role> roles;

    ObservableList<Utilisateur> list;
    ObservableList<Utilisateur> dataList;
    int index = -1;

    @FXML
    void messageAdmin() {
        nomAdmin.setText("Session "+ HelloApplication.getUser().getNom() +" "+ HelloApplication.getUser().getPrenom());
    }

    public PageAdmin() {
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
            requetePrepare.setInt(5,roles.getValue().getIdRole());
            requetePrepare.executeUpdate();
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
        for (Role r : roles.getItems()
             ) {
            if (r.getIdRole() == Integer.valueOf(roleUser.getCellData(index).toString())){
                roles.setValue(r);
                break;
            }
        }

    }

    public void modifier(){
        ConnexionBdd connexionBdd = new ConnexionBdd();
        Connection connection = connexionBdd.getBdd();
        String sql = "UPDATE utilisateur  SET  nom = ?, prenom = ?, identifiant = ?, mdp = ? , ref_role = ? where id_utilisateur =?";

        try{
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            requetePrepare.setString(1,nom.getText());
            requetePrepare.setString(2,prenom.getText());
            requetePrepare.setString(3,identifiant.getText());
            requetePrepare.setString(4,mdp.getText());
            requetePrepare.setInt(5,roles.getValue().getIdRole());
            requetePrepare.setInt(6, Integer.parseInt(idText.getText()) );
            requetePrepare.executeUpdate();
            rechercher();
            vider();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void supprimer(){
        ConnexionBdd connexionBdd = new ConnexionBdd();
        Connection connection = connexionBdd.getBdd();
        String sql = "DELETE FROM utilisateur where id_utilisateur =?";
        try{
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            System.out.println(idText.getText());
            requetePrepare.setInt(1, Integer.parseInt(idText.getText()));
            requetePrepare.executeUpdate();
            vider();
            System.out.println(tableau.getItems().get(index));
            list = ConnexionBdd.recupererUtilisateur();
            tableau.setItems(list);
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
        roles.setValue(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messageAdmin();
        initialiser();
        ConnexionBdd connexionBdd = new ConnexionBdd();
        Connection connection = connexionBdd.getBdd();
        String sql = "SELECT * FROM role";

        try{
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            ResultSet resultatRequette = requetePrepare.executeQuery();
            while (resultatRequette.next()){
                String nom = resultatRequette.getString("fonction");
                int idRole = resultatRequette.getInt("id_role");
                roles.getItems().add(new Role(idRole,nom));
                //MenuItem menuItem = new MenuItem(nom);
                //role.getItems().add(menuItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}