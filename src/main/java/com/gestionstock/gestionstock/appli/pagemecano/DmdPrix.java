package com.gestionstock.gestionstock.appli.pagemecano;

import com.gestionstock.gestionstock.HelloApplication;
import com.gestionstock.gestionstock.entity.Fournisseur;
import com.gestionstock.gestionstock.entity.Utilisateur;
import com.gestionstock.gestionstock.sql.ConnexionBdd;
import com.gestionstock.gestionstock.vues.Tableau;
import com.gestionstock.gestionstock.vues.TableauDmdPrix;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DmdPrix implements Initializable {

    @FXML
    private TextField CoteSurPlat;

    @FXML
    private Button Retour;

    @FXML
    private Button ajouter;

    @FXML
    private ComboBox<Utilisateur> demandeur;

    @FXML
    private TextField diametre;

    @FXML
    private TextField epaisseur;

    @FXML
    private ComboBox<Tableau> forme;

    @FXML
    private TableColumn<TableauDmdPrix, String> formeTab;

    @FXML
    private TableColumn<TableauDmdPrix, String> largeurTab;

    @FXML
    private TableColumn<TableauDmdPrix, String> coteTab;

    @FXML
    private TableColumn<TableauDmdPrix, String> epaisseurTab;
    @FXML
    private TableColumn<TableauDmdPrix, String> diametreTab;
    @FXML
    private TableColumn<TableauDmdPrix, String> hauteurTab;
    @FXML
    private TableColumn<TableauDmdPrix, String> longueurTab;
    @FXML
    private TableColumn<TableauDmdPrix, String> quantiteTab;

    @FXML
    private ComboBox<Fournisseur> fournisseur;

    @FXML
    private Label getId;

    @FXML
    private TextField hauteur;

    @FXML
    private ImageView image;

    @FXML
    private TextField largeur;

    @FXML
    private TextField longeur;

    @FXML
    private TextField quantite1;

    @FXML
    private TableView<TableauDmdPrix> tableau;

    private ObservableList<TableauDmdPrix> data = FXCollections.observableArrayList();




    @FXML
    void bouttonRetour(ActionEvent event) {
        if(HelloApplication.getUser().getRole()== 1 ){
            HelloApplication.changeScene("menuAdmin","Menu Admin");
        }else if(HelloApplication.getUser().getRole()== 2 ){
            HelloApplication.changeScene("pageMecano", "Page Mecano");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tableau.setItems(data);
        formeTab.setCellValueFactory(new PropertyValueFactory<TableauDmdPrix,String>("nom"));
        hauteurTab.setCellValueFactory(new PropertyValueFactory<TableauDmdPrix,String>("hauteur"));
        diametreTab.setCellValueFactory(new PropertyValueFactory<TableauDmdPrix,String>("diametre"));
        epaisseurTab.setCellValueFactory(new PropertyValueFactory<TableauDmdPrix,String>("epaisseur"));
        coteTab.setCellValueFactory(new PropertyValueFactory<TableauDmdPrix,String>("coteSurPlat"));
        largeurTab.setCellValueFactory(new PropertyValueFactory<TableauDmdPrix,String>("largeur"));
        longueurTab.setCellValueFactory(new PropertyValueFactory<TableauDmdPrix,String>("longueurdm"));
        quantiteTab.setCellValueFactory(new PropertyValueFactory<TableauDmdPrix,String>("quantite"));

        ConnexionBdd connexionBdd = new ConnexionBdd();
        Connection connection = connexionBdd.getBdd();

        String sql = "SELECT * FROM Fournisseur ";

        try {

            PreparedStatement requete = connection.prepareStatement(sql);

            ResultSet resultatrequete = requete.executeQuery();

            while (resultatrequete.next()) {
                int id_fournisseur = resultatrequete.getInt("id_fournisseur");
                String nom = resultatrequete.getString("nom");
                String rue = resultatrequete.getString("rue");
                String cp = resultatrequete.getString("cp");
                String ville = resultatrequete.getString("ville");
                String referent = resultatrequete.getString("referent");
                String telephone = resultatrequete.getString("telephone");
                fournisseur.getItems().add(new Fournisseur(id_fournisseur, nom, rue, cp, ville, referent, telephone));


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql2 = "SELECT * FROM tableau3 ";

        try {

            PreparedStatement requete = connection.prepareStatement(sql2);

            ResultSet resultatrequete = requete.executeQuery();

            while (resultatrequete.next()) {
                int id_typpeforme = resultatrequete.getInt("id_typeforme");
                int id_matiere = resultatrequete.getInt("id_matiere");
                float diametre = resultatrequete.getFloat("diametre");
                float hauteur = resultatrequete.getFloat("hauteur");
                float largeur = resultatrequete.getFloat("largeur");
                float coteSurPlat = resultatrequete.getFloat("coteSurPlat");
                float epaisseur = resultatrequete.getFloat("epaisseur");
                float longueur = resultatrequete.getFloat("longueur");
                String nom = resultatrequete.getString("nom");
                String nomMat = resultatrequete.getString("nomMat");
                forme.getItems().add(new Tableau(diametre, hauteur, largeur, coteSurPlat, epaisseur, nom, longueur, nomMat, id_typpeforme, id_matiere));


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql3 = "SELECT id_utilisateur, nom FROM utilisateur WHERE ref_role in(SELECT id_role FROM role WHERE fonction ='Mecanitien')";
        try {
            PreparedStatement requete = connection.prepareStatement(sql3);
            ResultSet resultatRequette = requete.executeQuery();
            while (resultatRequette.next()) {
                int id = resultatRequette.getInt("id_utilisateur");
                String nom = resultatRequette.getString("nom");
                this.demandeur.getItems().add(new Utilisateur(nom, id));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void clickForme(ActionEvent event) {
        ConnexionBdd connexionBdd = new ConnexionBdd();
        Connection connection = connexionBdd.getBdd();
        Tableau t = forme.getValue();
        largeur.setText(String.valueOf(t.getLargeur()));
        diametre.setText(String.valueOf(t.getDiametre()));
        hauteur.setText(String.valueOf(t.getHauteur()));
        epaisseur.setText(String.valueOf(t.getEpaisseur()));
        CoteSurPlat.setText(String.valueOf(t.getCoteSurPlat()));
    }

    @FXML
    void vider(){
        forme.setValue(null);
        largeur.setText("");
        CoteSurPlat.setText("");
        epaisseur.setText("");
        diametre.setText("");
        hauteur.setText("");
        longeur.setText("");
        quantite1.setText("");

    }

    @FXML
    void clickAjouter(ActionEvent event) {
        Tableau f = forme.getValue();

        data.add(new TableauDmdPrix(f.getDiametre(), f.getHauteur(), f.getLargeur(), f.getCoteSurPlat(), f.getEpaisseur(), f.getNom(), f.getLongueur(), f.getNomMat(), f.getId(),f.getIdMat(),longeur.getText(),quantite1.getText()));
        vider();
    }

    @FXML
    void bouttonVider(ActionEvent event) {}

    @FXML
    void genererPdf(ActionEvent event) {
        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("C:\\projetJava\\gestionStock\\Pdf\\DemandeDePrix.pdf"));
            doc.open();
            doc.add(new Paragraph("Demande de prix"));
            /*Image img = Image.getInstance("C:\\projetJava\\gestionStock\\src\\main\\resources");
            img.setAbsolutePosition(600,92);
            doc.add(img);*/
            doc.add(new Paragraph("Demandeur : " + demandeur.getValue()));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("Fournisseur : " + fournisseur.getValue()));
            doc.add(new Paragraph(" "));
            PdfPTable tableau = new PdfPTable(8);
            tableau.setWidthPercentage(100);
            PdfPCell cell ;
            cell = new PdfPCell(new Phrase("Forme", FontFactory.getFont("Comic Sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            tableau.addCell((PdfPCell) cell);

            cell = new PdfPCell(new Phrase("Largeur", FontFactory.getFont("Comic Sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            tableau.addCell((PdfPCell) cell);

            cell = new PdfPCell(new Phrase("Diametre", FontFactory.getFont("Comic Sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            tableau.addCell((PdfPCell) cell);

            cell = new PdfPCell(new Phrase("Epaisseur", FontFactory.getFont("Comic Sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            tableau.addCell((PdfPCell) cell);

            cell = new PdfPCell(new Phrase("CoteSurPlat", FontFactory.getFont("Comic Sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            tableau.addCell((PdfPCell) cell);

            cell = new PdfPCell(new Phrase("Hauteur", FontFactory.getFont("Comic Sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            tableau.addCell((PdfPCell) cell);

            cell = new PdfPCell(new Phrase("Longueur", FontFactory.getFont("Comic Sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            tableau.addCell((PdfPCell) cell);

            cell = new PdfPCell(new Phrase("Quantité", FontFactory.getFont("Comic Sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            tableau.addCell((PdfPCell) cell);

            //Tableau
            for (TableauDmdPrix t : data){
                cell = new PdfPCell(new Phrase(t.getNom(), FontFactory.getFont("Comic Sans MS",12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableau.addCell((PdfPCell) cell);

                cell = new PdfPCell(new Phrase(String.valueOf(t.getLargeur()), FontFactory.getFont("Comic Sans MS",12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableau.addCell((PdfPCell) cell);

                cell = new PdfPCell(new Phrase(String.valueOf(t.getDiametre()), FontFactory.getFont("Comic Sans MS",12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableau.addCell((PdfPCell) cell);

                cell = new PdfPCell(new Phrase(String.valueOf(t.getEpaisseur()), FontFactory.getFont("Comic Sans MS",12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableau.addCell((PdfPCell) cell);

                cell = new PdfPCell(new Phrase(String.valueOf(t.getCoteSurPlat()), FontFactory.getFont("Comic Sans MS",12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableau.addCell((PdfPCell) cell);

                cell = new PdfPCell(new Phrase(String.valueOf(t.getHauteur()), FontFactory.getFont("Comic Sans MS",12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableau.addCell((PdfPCell) cell);

                cell = new PdfPCell(new Phrase(t.getLongueurdm(), FontFactory.getFont("Comic Sans MS",12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableau.addCell((PdfPCell) cell);

                cell = new PdfPCell(new Phrase(t.getQuantite(), FontFactory.getFont("Comic Sans MS",12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableau.addCell((PdfPCell) cell);
            }
            doc.add(tableau);
            doc.close();


        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    }



