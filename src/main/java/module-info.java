module com.example.uitest {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires ojdbc6;
    requires java.desktop;


    opens com.example.uitest to javafx.fxml;
    exports com.example.uitest;
}