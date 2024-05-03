module com.mycompany.userapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    
    opens com.mycompany.userapp to javafx.fxml;
    exports com.mycompany.userapp;
}
