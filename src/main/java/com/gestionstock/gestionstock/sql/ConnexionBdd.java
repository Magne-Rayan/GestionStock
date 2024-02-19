package com.gestionstock.gestionstock.sql;
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
}
