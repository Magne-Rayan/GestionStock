package com.gestionstock.gestionstock;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnexionBdd {
    public Connection bdd;

    public Connection getBdd() {
        String bddNom = "utilisateur";
        String user ="admin";
        String usermdp ="dc7g9Z7K";
        String url ="jdbc:mysql://localhost:3306/" + bddNom;

        try {
            bdd = DriverManager.getConnection(url,user,usermdp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return bdd;
    }
}
