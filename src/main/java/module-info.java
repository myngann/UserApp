module com.mycompany.userapp {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.userapp to javafx.fxml;
    exports com.mycompany.userapp;
}
