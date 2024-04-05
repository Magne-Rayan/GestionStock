package com.gestionstock.gestionstock.appli.pagemecano;

import com.gestionstock.gestionstock.HelloApplication;
import com.gestionstock.gestionstock.sql.ConnexionBdd;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EtatDesStocks {
    @FXML
    void bouttonRetour(ActionEvent event){
        if(HelloApplication.getUser().getId()== 1 ){
            HelloApplication.changeScene("menuAdmin","Menu Admin");
        }if(HelloApplication.getUser().getId()== 2 ){
            HelloApplication.changeScene("pageMecano", "Page Mecano");
        }
    }
    @FXML
    void genererPdf(ActionEvent event) {

        Document doc = new Document();
        ConnexionBdd connexionBdd = new ConnexionBdd();
        Connection connection = connexionBdd.getBdd();
        String sql = "SELECT * FROM tableau ";
        try {
                PreparedStatement requetePrepare = connection.prepareStatement(sql);
                ResultSet resultatRequette = requetePrepare.executeQuery();

            PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\laura\\Pdf\\EtatDesStocks.pdf"));
            doc.open();
            doc.add(new Paragraph("Etats des Stocks : "));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("Tableau : "));
            doc.add(new Paragraph("  "));
            PdfPTable tableau = new PdfPTable(8);
            tableau.setWidthPercentage(100);
            PdfPCell cell ;
            cell = new PdfPCell(new Phrase("Diametre", FontFactory.getFont("Comic Sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            tableau.addCell((PdfPCell) cell);

            cell = new PdfPCell(new Phrase("Hauteur", FontFactory.getFont("Comic Sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            tableau.addCell((PdfPCell) cell);

            cell = new PdfPCell(new Phrase("Largeur", FontFactory.getFont("Comic Sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            tableau.addCell((PdfPCell) cell);

            cell = new PdfPCell(new Phrase("CoteSurPlat", FontFactory.getFont("Comic Sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            tableau.addCell((PdfPCell) cell);

            cell = new PdfPCell(new Phrase("epaisseur", FontFactory.getFont("Comic Sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            tableau.addCell((PdfPCell) cell);

            cell = new PdfPCell(new Phrase("Nom", FontFactory.getFont("Comic Sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            tableau.addCell((PdfPCell) cell);

            cell = new PdfPCell(new Phrase("Longueur", FontFactory.getFont("Comic Sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            tableau.addCell((PdfPCell) cell);

            cell = new PdfPCell(new Phrase("Nom Matériaux", FontFactory.getFont("Comic Sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            tableau.addCell((PdfPCell) cell);

            while (resultatRequette.next()){
                cell = new PdfPCell(new Phrase(String.valueOf(resultatRequette.getFloat("diametre")), FontFactory.getFont("Comic Sans MS",12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableau.addCell((PdfPCell) cell);

                cell = new PdfPCell(new Phrase(String.valueOf(resultatRequette.getFloat("hauteur")), FontFactory.getFont("Comic Sans MS",12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableau.addCell((PdfPCell) cell);

                cell = new PdfPCell(new Phrase(String.valueOf(resultatRequette.getFloat("largeur")), FontFactory.getFont("Comic Sans MS",12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableau.addCell((PdfPCell) cell);

                cell = new PdfPCell(new Phrase(String.valueOf(resultatRequette.getFloat("coteSurPlat")), FontFactory.getFont("Comic Sans MS",12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableau.addCell((PdfPCell) cell);

                cell = new PdfPCell(new Phrase(String.valueOf(resultatRequette.getFloat("epaisseur")), FontFactory.getFont("Comic Sans MS",12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableau.addCell((PdfPCell) cell);

                cell = new PdfPCell(new Phrase(resultatRequette.getString("nom"), FontFactory.getFont("Comic Sans MS",12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableau.addCell((PdfPCell) cell);

                cell = new PdfPCell(new Phrase(String.valueOf(resultatRequette.getFloat("longueur")), FontFactory.getFont("Comic Sans MS",12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableau.addCell((PdfPCell) cell);

                cell = new PdfPCell(new Phrase(resultatRequette.getString("nomMat"), FontFactory.getFont("Comic Sans MS",12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableau.addCell((PdfPCell) cell);
            }
            doc.add(tableau);
            doc.close();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
