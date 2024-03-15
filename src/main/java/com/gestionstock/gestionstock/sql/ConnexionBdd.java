package com.gestionstock.gestionstock.sql;
import com.gestionstock.gestionstock.entity.Utilisateur;
import com.gestionstock.gestionstock.vues.Tableau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;


public class ConnexionBdd {
    public Connection bdd;

    public Connection getBdd() {
        String bddNom = "gestionStock";
        String user ="gestionStock";
        String usermdp ="!cbQHHTTTn1waCP";
        String url ="jdbc:mysql://localhost:3306/" + bddNom;

        try {
            bdd = DriverManager.getConnection(url,user,usermdp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return bdd;
    }
    public static ObservableList<Utilisateur> recupererUtilisateur() {
        ObservableList<Utilisateur> liste = FXCollections.observableArrayList();
        ConnexionBdd connexionBdd = new ConnexionBdd();
        Connection connection = connexionBdd.getBdd();
        String sql = "SELECT * FROM utilisateur ";
        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            ResultSet resultatRequette = requetePrepare.executeQuery();
            while (resultatRequette.next()) {
                liste.add(new Utilisateur(Integer.parseInt(resultatRequette.getString("id_utilisateur")),resultatRequette.getString("nom"),resultatRequette.getString("prenom"),resultatRequette.getString("identifiant"),resultatRequette.getString("mdp"),Integer.parseInt(resultatRequette.getString("ref_role"))));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return liste;
    }

    public static ObservableList<Tableau> recupFormes() {
        ObservableList<Tableau> liste = FXCollections.observableArrayList();
        ConnexionBdd connexionBdd = new ConnexionBdd();
        Connection connection = connexionBdd.getBdd();
        String sql = "SELECT * FROM `tableau` ";
        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            ResultSet resultatRequette = requetePrepare.executeQuery();
            while (resultatRequette.next()) {
                liste.add(new Tableau(resultatRequette.getFloat("diametre"),resultatRequette.getFloat("hauteur"),resultatRequette.getFloat("largeur"),resultatRequette.getFloat("coteSurPlat"),resultatRequette.getFloat("epaisseur"),resultatRequette.getString("nom"),resultatRequette.getFloat("longueur"),resultatRequette.getString("nomMat"),resultatRequette.getInt("id_typeforme")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return liste;
    }
}
