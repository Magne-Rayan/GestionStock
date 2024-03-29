module com.gestionstock.gestionstock {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires itextpdf;

    exports com.gestionstock.gestionstock.sql;
    opens com.gestionstock.gestionstock.sql to javafx.fxml;
    exports com.gestionstock.gestionstock.appli.pageacceuil;
    opens com.gestionstock.gestionstock.appli.pageacceuil to javafx.fxml;
    exports com.gestionstock.gestionstock.appli.pageadmin;
    opens com.gestionstock.gestionstock.appli.pageadmin to javafx.fxml;
    exports com.gestionstock.gestionstock.appli.pagemecano;
    opens com.gestionstock.gestionstock.appli.pagemecano to javafx.fxml;
    exports com.gestionstock.gestionstock.entity;
    opens com.gestionstock.gestionstock.entity to javafx.fxml;
    exports com.gestionstock.gestionstock.vues;
    opens com.gestionstock.gestionstock.vues to javafx.fxml;
    exports com.gestionstock.gestionstock;
    opens com.gestionstock.gestionstock to javafx.fxml;
}