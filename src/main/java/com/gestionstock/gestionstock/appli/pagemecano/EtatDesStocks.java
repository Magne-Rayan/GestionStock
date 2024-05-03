package com.gestionstock.gestionstock.appli.pagemecano;

import com.gestionstock.gestionstock.HelloApplication;
import com.gestionstock.gestionstock.sql.ConnexionBdd;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import  org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import  org.apache.poi.hssf.usermodel.HSSFRow;
import  org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EtatDesStocks {
    @FXML
    void bouttonRetour(ActionEvent event){
        if(HelloApplication.getUser().getRole()== 1 ){
            HelloApplication.changeScene("menuAdmin","Menu Admin");
        }if(HelloApplication.getUser().getRole()== 2 ){
            HelloApplication.changeScene("pageMecano", "Page Mecano");
        }if(HelloApplication.getUser().getRole()==3){
            HelloApplication.changeScene("pageCompta", "Page comptabilité");
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

            PdfWriter.getInstance(doc, new FileOutputStream("C:\\projetJava\\gestionStock\\Pdf\\EtatsDesStocks.pdf"));
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

    @FXML
    void genererExcel(ActionEvent event) {
        String filename="C:\\projetJava\\gestionStock\\Ecxels\\Etats des Stocks.xls";
        HSSFWorkbook excel = new HSSFWorkbook();
        HSSFSheet feuille = excel.createSheet("Etat des  Stocks");
        HSSFRow rowhead=   feuille.createRow((short)0);
        rowhead.createCell((short) 0).setCellValue("Diametre");
        rowhead.createCell((short) 1).setCellValue("Hauteur");
        rowhead.createCell((short) 2).setCellValue("Largeur");
        rowhead.createCell((short) 3).setCellValue("Cote Sur Plat");
        rowhead.createCell((short) 4).setCellValue("Epaisseur");
        rowhead.createCell((short) 5).setCellValue("Nom");
        rowhead.createCell((short) 6).setCellValue("Longeur");
        rowhead.createCell((short) 7).setCellValue("Nom Materiaux");

        ConnexionBdd connexionBdd = new ConnexionBdd();
        Connection connection = connexionBdd.getBdd();
        String sql = "SELECT * FROM tableau ";
        int i=1;
        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            ResultSet resultatRequette = requetePrepare.executeQuery();

            while (resultatRequette.next()){
                HSSFRow row=   feuille.createRow((short)i);
                row.createCell((short) 0).setCellValue(String.valueOf(resultatRequette.getFloat("diametre")));
                row.createCell((short) 1).setCellValue(String.valueOf(resultatRequette.getFloat("hauteur")));
                row.createCell((short) 2).setCellValue(String.valueOf(resultatRequette.getFloat("largeur")));
                row.createCell((short) 3).setCellValue(String.valueOf(resultatRequette.getFloat("coteSurPlat")));
                row.createCell((short) 4).setCellValue(String.valueOf(resultatRequette.getFloat("epaisseur")));
                row.createCell((short) 5).setCellValue(resultatRequette.getString("nom"));
                row.createCell((short) 6).setCellValue(String.valueOf(resultatRequette.getFloat("longueur")));
                row.createCell((short) 7).setCellValue(resultatRequette.getString("nomMat"));
                i++;

            }

            FileOutputStream fileOut =  new FileOutputStream(filename);
            excel.write(fileOut);
            fileOut.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
