package com.gestionstock.gestionstock.sql;

import com.gestionstock.gestionstock.HelloApplication;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Utilisateur {
    private int id;
    private String nom;
    private String prenom;
    private String identif;
    private String mdP;
    private int role;

    public Utilisateur(){

    }

    public Utilisateur(int id, String nom, String prenom, String identif, String mdP, int role){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.identif = identif;
        this.mdP = mdP;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getIdentif() {
        return identif;
    }

    public void setIdentif(String identif) {
        this.identif = identif;
    }

    public String getMdP() {
        return mdP;
    }

    public void setMdP(String mdP) {
        this.mdP = mdP;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public static Utilisateur connexion(String identifiant, String mdp, Label label){
        Utilisateur user = new Utilisateur();
        ConnexionBdd connexionBdd = new ConnexionBdd();
        Connection connection = connexionBdd.getBdd();
        String sql = "SELECT * FROM utilisateur WHERE identifiant ='"+identifiant+ "' AND mdp ='"+mdp+"'";

        try{
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            ResultSet resultatRequette = requetePrepare.executeQuery();
            if(resultatRequette.next()){
                int id = resultatRequette.getInt(1);
                String nom = resultatRequette.getString(2);
                String prenom = resultatRequette.getString(3);
                String identif = resultatRequette.getString(4);
                String mdP = resultatRequette.getString(5);
                int role = resultatRequette.getInt(6);
                user = new Utilisateur(id,nom, prenom,identif,mdP,role);
                return user;
            }else {
                label.setText("Erreur veuillez re essayer");

            }
        }catch (Exception e ){
            System.out.println(e.getMessage());

        }
        return user;
    }


}
