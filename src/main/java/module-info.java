module com.gestionstock.gestionstock {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.gestionstock.gestionstock to javafx.fxml;
    exports com.gestionstock.gestionstock;
    exports com.gestionstock.gestionstock.sql;
    opens com.gestionstock.gestionstock.sql to javafx.fxml;
}