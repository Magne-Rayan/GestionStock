package com.gestionstock.gestionstock.appli.pagemecano;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class BonDebit   {
    @FXML
    private ComboBox<?> classe;

    @FXML
    private ComboBox<?> dimention;

    @FXML
    private ComboBox<?> filiere;

    @FXML
    private ComboBox<?> forme;

    @FXML
    private ImageView imageSysteme;

    @FXML
    private ImageView logo;

    @FXML
    private ComboBox<?> nom;

    @FXML
    private ComboBox<?> piece;

    @FXML
    private TextField quantité;

    @FXML
    private Button retourArriere;

    @FXML
    private TextField stockFinal;

    @FXML
    private TextField stockInitial;

    @FXML
    private ComboBox<?> systeme;

    @FXML
    private TextField totalDebiter;

    @FXML
    private Button valider;

    @FXML
    private Button vider;

    @FXML
    private ImageView visuelPiece;

}
