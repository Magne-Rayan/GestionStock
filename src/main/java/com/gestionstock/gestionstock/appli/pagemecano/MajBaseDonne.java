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
    private TextField modifStock;

    @FXML
    private TableColumn<Matiere, Float> allu;

    @FXML
    private TableColumn<Forme, Float> dimention;

    @FXML
    private TableColumn<?, ?> matiere;

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
    private ImageView imageForme;

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
    void clickFormeEvent(ActionEvent event) throws SQLException {
        if(this.forme.getValue() != null) {
            imageForme.setImage(new Image(this.forme.getValue().getImage().getBinaryStream()));
            ConnexionBdd connexionBdd = new ConnexionBdd();
            Connection connection = connexionBdd.getBdd();


            String sql2 = "SELECT m.longueur FROM matiere AS m " + "INNER JOIN forme AS f ON f.id_forme = m.ref_forme" +
                    " INNER JOIN typeforme as tf ON tf.id_typeforme = f.ref_typeforme " +
                    "WHERE m.ref_materiaux = 1 and f.ref_typeforme =? ";

            try {
                PreparedStatement requete = connection.prepareStatement(sql2);
                requete.setInt(1, this.forme.getValue().getIdTypeForme());
                ResultSet resultatRequette = requete.executeQuery();
                while (resultatRequette.next()) {
                    float longueur = resultatRequette.getFloat("longueur");
                    //allu.setCellFactory(new PropertyValueFactory<>(longueur));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

                dimention.setText("test");
                dimention.setCellValueFactory(new PropertyValueFactory<>("largeur"));
                dimention.setCellValueFactory(new PropertyValueFactory<>("coteSurPlat"));
                dimention.setCellValueFactory(new PropertyValueFactory<>("hauteur"));
                dimention.setCellValueFactory(new PropertyValueFactory<>("epaisseur"));
            HashMap<String,HashMap<String,Integer>> tab = new HashMap<>();
            //Selectionner l'ensemble des Materiaux
            // boucle sur les baterieaux
            // selectinner l'ensemble des matieres par materiaux
            // créer la forme pour une matiere (rond D25 | meplat larg:25, ep:30)
            if(!tab.containsKey("rond d25")){
                tab.put("rond D25",new HashMap<>());
            }
            tab.get("rond D25").put("Alu",100);





        }

    }}




