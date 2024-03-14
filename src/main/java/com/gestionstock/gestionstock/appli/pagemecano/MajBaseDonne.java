package com.gestionstock.gestionstock.appli.pagemecano;

import com.gestionstock.gestionstock.HelloApplication;
import com.gestionstock.gestionstock.entity.*;
import com.gestionstock.gestionstock.sql.ConnexionBdd;
import com.gestionstock.gestionstock.vues.Tableau;
import javafx.collections.ObservableList;
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
    private TableColumn<Tableau, Float>  diametre;

    @FXML
    private TableColumn<Tableau, Float>  epaisseur;

    @FXML
    private ComboBox<TypeForme> forme;

    @FXML
    private TableColumn<Tableau, Float>  hauteur;

    @FXML
    private ImageView imageForme;

    @FXML
    private TableColumn<Tableau, Float>  largeur;

    @FXML
    private TableColumn<Tableau, Float>  longueur;

    @FXML
    private TextField modifStock;

    @FXML
    private TableColumn<Tableau, String> nom;

    @FXML
    private TableColumn<Tableau, String> nomMat;

    @FXML
    private Button retour;

    @FXML
    private TableView<Tableau> tableau;

    @FXML
    private Button valider;

    @FXML
    private Button vider;

    ObservableList<Tableau> list;


    @FXML
    void bouttonRetour(ActionEvent event){
        HelloApplication.changeScene("pageMecano","Page Mecano");
    }

    @FXML
    void vider(){



    }
    @FXML
    public void initialiser(){
        diametre.setCellValueFactory(new PropertyValueFactory<Tableau,Float>("diametre"));
        hauteur.setCellValueFactory(new PropertyValueFactory<Tableau,Float>("hauteur"));
        largeur.setCellValueFactory(new PropertyValueFactory<Tableau,Float>("largeur"));
        coteSurPlat.setCellValueFactory(new PropertyValueFactory<Tableau,Float>("coteSurPlat"));
        epaisseur.setCellValueFactory(new PropertyValueFactory<Tableau,Float>("epaisseur"));
        nom.setCellValueFactory(new PropertyValueFactory<Tableau,String>("nom"));
        longueur.setCellValueFactory(new PropertyValueFactory<Tableau,Float>("longueur"));
        nomMat.setCellValueFactory(new PropertyValueFactory<Tableau,String>("nomMat"));


        list = ConnexionBdd.recupFormes();
        tableau.setItems(list);


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialiser();


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
    void clickFormeEvent(ActionEvent event) throws SQLException {
        if(this.forme.getValue() != null) {
            imageForme.setImage(new Image(this.forme.getValue().getImage().getBinaryStream()));
            ConnexionBdd connexionBdd = new ConnexionBdd();
            Connection connection = connexionBdd.getBdd();

            HashMap<String,HashMap<String,Integer>> tab = new HashMap<>();
            //Selectionner l'ensemble des Materiaux
            String sql1 = "SELECT * FROM materiaux";

            try{
                PreparedStatement requete = connection.prepareStatement(sql1);
                ResultSet resultatRequette = requete.executeQuery();
                while (resultatRequette.next()) {
                    String nom = resultatRequette.getString("nom");
                    int id = resultatRequette.getInt("id_materiaux");

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            // boucle sur les materieaux


<<<<<<< HEAD
=======
            dimention.setText("test");
            dimention.setCellValueFactory(new PropertyValueFactory<>("largeur"));
            dimention.setCellValueFactory(new PropertyValueFactory<>("coteSurPlat"));
            dimention.setCellValueFactory(new PropertyValueFactory<>("hauteur"));
            dimention.setCellValueFactory(new PropertyValueFactory<>("epaisseur"));
            HashMap<String,HashMap<String,Integer>> tab = new HashMap<>();
            //Selectionner l'ensemble des Materiaux
            // boucle sur les baterieaux
>>>>>>> 6454a43f90bad6a0ee63844559ed10061e7fb40f
            // selectinner l'ensemble des matieres par materiaux
            String sql2 = "SELECT * FROM matiere GROUP BY ref_materiaux";

            // créer la forme pour une matiere (rond D25 | meplat larg:25, ep:30)
            if(!tab.containsKey("rond d25")){
                tab.put("rond D25",new HashMap<>());
            }
            tab.get("rond D25").put("Alu",100);

            String sql ="SELECT f.diametre, f.hauteur,f.largeur,f.coteSurPlat,f.epaisseur, tf.nom FROM forme as f " +
                    "INNER JOIN typeforme as tf ON tf.id_typeforme = f.ref_typeforme GROUP BY f.id_forme; ";
            try {
                PreparedStatement requete = connection.prepareStatement(sql);
                ResultSet resultatRequette = requete.executeQuery();
                while (resultatRequette.next()) {
                    float diametre = resultatRequette.getFloat("diametre");
                    float hauteur = resultatRequette.getFloat("hauteur");
                    float largeur = resultatRequette.getFloat("largeur");
                    float coteSurPlat = resultatRequette.getFloat("coteSurPlat");
                    float epaisseur = resultatRequette.getFloat("epaisseur");
                    String nom = resultatRequette.getString("nom");


                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }



        }

    }}