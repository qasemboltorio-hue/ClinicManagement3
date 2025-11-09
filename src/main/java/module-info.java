module com.example.clinicmanagement3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;      // <-- add this line
    requires java.sql;


    opens com.example.clinicmanagement3 to javafx.fxml;
    exports com.example.clinicmanagement3;
}